package nix.alevel.finance.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import nix.alevel.finance.dao.impl.OperationDAO;
import nix.alevel.finance.exeption.OperationDataLayerException;
import nix.alevel.finance.exeption.ResourceNotFoundException;
import nix.alevel.finance.model.entity.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

public class JPAOperationService {

    private final EntityManager entityManager;
    private final Validator validator;
    private final OperationDAO operationDAO;

    public JPAOperationService(EntityManager en, Validator validator){
        this.entityManager = en;
        this.validator = validator;
        this.operationDAO = new OperationDAO(en);
    }
    public <T extends Category> void save(Operation<T> operation) throws OperationDataLayerException {
        try{
            validate(operation);
        }catch (ConstraintViolationException e) {
            throw new OperationDataLayerException("Transaction of operation could not be 0, strictly positive or negative", e);
        }
        if(operation.getCategories().isEmpty())
            throw new OperationDataLayerException("Operation should have valid categories");

        Class<?> objClass =  operation.getCategories().iterator().next().getClass();

        if(objClass.equals(ExpenseCategory.class) && operation.getTransaction()>0)
            throw new OperationDataLayerException("Expense operation should provide negative value");
        if(objClass.equals(IncomeCategory.class) && operation.getTransaction()<0)
            throw new OperationDataLayerException("Income operation should provide positive value");

        try {
            operationDAO.save(operation);
        }catch (OperationDataLayerException e){
            System.out.println(e.getMessage());
        }
    }
    public List<IncomeCategory> getIncomeCategories() throws OperationDataLayerException{
        return operationDAO.getIncomeCategories();
    }
    public List<ExpenseCategory> getExpenseCategories() throws OperationDataLayerException{
        return operationDAO.getExpenseCategories();
    }
    public User findUserByEmail(String email) throws ResourceNotFoundException {
        return operationDAO.getUserByEmail(email);
    }
    public List<Account> findAccountsOfUser(Long id) throws ResourceNotFoundException{
        return operationDAO.getAccountsOfUser(id);
    }
    public List<Operation> findAllOperationOfAccount(Long id) throws OperationDataLayerException {
        List<Operation> operations = operationDAO.findAllOperationsOfAccount(id);
        if(operations.isEmpty()) throw new OperationDataLayerException("Account has not any operstions");
        return operations;
    }
    public List<IncomeCategory> findIncomeCategoriesByListOfIds(List<Long> ids) throws OperationDataLayerException {
        return operationDAO.getIncomeCategoriesById(ids);
    }
    public List<ExpenseCategory> findExpenseCategoriesByListOfIds(List<Long> ids) throws OperationDataLayerException {
        return operationDAO.getExpenseCategoriesById(ids);
    }
    private<T extends Category> void validate(Operation<T> managedResource) {
        Set<ConstraintViolation<Operation<T>>> constraintViolations = validator.validate(managedResource);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
