package nix.alevel.finance.controller;

import jakarta.validation.Validator;
import nix.alevel.finance.exeption.OperationDataLayerException;
import nix.alevel.finance.exeption.ResourceNotFoundException;
import nix.alevel.finance.model.entity.*;
import nix.alevel.finance.service.JPAOperationService;
import nix.alevel.finance.util.ConsoleUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class JPAMode {
    private final static Logger logger = LoggerFactory.getLogger(JPAMode.class);
    private final EntityManager entityManager;
    private final Validator validator;
    private final String email;
    private final JPAOperationService service;
    public JPAMode(EntityManager en, Validator vl, String userEmail){
        entityManager = en;
        validator = vl;
        email = userEmail;
        service = new JPAOperationService(entityManager, validator );
    }
    public void run(){
        try ( BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            boolean t = true;
            while (t){
                System.out.println("Do you want to add operation? Y/N");
                switch (bufferedReader.readLine().toUpperCase(Locale.ROOT)){
                    case "Y":{
                        addOperationLogic(bufferedReader);
                        break;
                    }
                    case "N":{
                        t =false;
                        break;
                    }
                    default:{
                        System.out.println("Illegal answer. Please try again...");
                    }
                }
            }
        } catch (IOException e){
            logger.error("Error occurred with buffered reader ", e);
            System.out.println(e.getMessage());
        }
    }
    public void addOperationLogic(BufferedReader reader){
        ConsoleUtils utils = new ConsoleUtils();
        try {
            User currentUser = service.findUserByEmail(email);
            List<Account> accounts = service.findAccountsOfUser(currentUser.getId());
            utils.accountTable(accounts);
            List<Long> accountSecurityId = accounts.stream().map(Account::getId).collect(Collectors.toList());

            System.out.println("--- Start creating operation");
            System.out.println("--- Pleases, enter the id of account: ");
            long currentAccountId = utils.chooseTheOwnAccount(reader, accountSecurityId);
            Account account = accounts.stream().filter(a-> a.getId()==currentAccountId).findFirst().get();
            System.out.println("--- Choose the type of category. \n\t1.Income\n\t2.Expense");
            long choice = utils.readLong(reader);

            List<IncomeCategory> incomeCategories;
            List<ExpenseCategory> expenseCategories;
            boolean flag = true;
            if(choice==1L) {
                incomeCategories = service.getIncomeCategories();
                utils.incomeTable(incomeCategories);
            }if(choice==2L){
                flag = false;
                expenseCategories = service.getExpenseCategories();
                utils.expenseTable(expenseCategories);
            }
            System.out.println("--- Please, choose one or more categories by id and write as 1,2,3");
            String[] categories = reader.readLine().trim().replace(" ", "").split(",");
            List<Long> idOfCategories = stringToLongArray(categories);

            System.out.println("--- Enter the money transaction: ");
            long moneyCount = utils.readLong(reader);
            if(flag)
                createOperation(IncomeCategory.class, account, moneyCount, idOfCategories);
            else createOperation(ExpenseCategory.class, account, moneyCount, idOfCategories);

            logger.info("Transaction was successfully passed");
            System.out.println("\nYour transaction was successfully passed");
        }catch (ResourceNotFoundException | OperationDataLayerException| IOException e){
            logger.error("Money transaction wasn`t passed.",e);
            System.out.println(e.getMessage());
            System.out.println("Money transaction wasn`t passed.");
        }
    }

    public void createOperation(Class<? extends Category> classT, Account account, long moneyCount, List<Long> idOfCategories ) throws OperationDataLayerException {
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setTransaction(moneyCount);
        if(classT.equals(ExpenseCategory.class)) {
            Set<ExpenseCategory> categoryOfOperation=new HashSet<>();
            CollectionUtils.addAll(categoryOfOperation, service.findExpenseCategoriesByListOfIds(idOfCategories) );
            operation.setCategories(categoryOfOperation);
        }else{
            Set<IncomeCategory> categoryOfOperation=new HashSet<>();
            CollectionUtils.addAll(categoryOfOperation, service.findIncomeCategoriesByListOfIds(idOfCategories) );
            operation.setCategories(categoryOfOperation);
        }
        service.save(operation);
    }

    public List<Long> stringToLongArray(String[] arr) throws OperationDataLayerException {
        List<Long> ids = new ArrayList<>();
        try {
            for (String s : arr) ids.add(Long.parseLong(s));
            return ids;
        }catch (NumberFormatException e){
            throw new OperationDataLayerException("Illegal operations id");
        }
    }
}
