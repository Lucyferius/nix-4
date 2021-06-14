package nix.alevel.finance.controller;

import nix.alevel.finance.exeption.OperationDataLayerException;
import nix.alevel.finance.exeption.ResourceNotFoundException;
import nix.alevel.finance.model.entity.Account;
import nix.alevel.finance.model.entity.dto.OperationExport;
import nix.alevel.finance.service.JDBCOperationService;
import nix.alevel.finance.util.CSVOperationWriter;
import nix.alevel.finance.util.ConsoleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JDBCMode {
    private final static Logger logger = LoggerFactory.getLogger(JDBCMode.class);
    private final Connection connection;
    private final JDBCOperationService service;
    private final String userEmail;
    private final String fileToExport;
    private final CSVOperationWriter writer = new CSVOperationWriter();

    public JDBCMode(Connection connection, String email, String fileToExport){
        this.connection = connection;
        this.service = new JDBCOperationService(connection);
        this.userEmail = email;
        this.fileToExport = fileToExport;
    }
    public void run(){
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            boolean t = true;
            while (t){
                System.out.println("Do you want to export report? Y/N");
                switch (bufferedReader.readLine().toUpperCase(Locale.ROOT)){
                    case "Y":{
                        exportLogic(bufferedReader);
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

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
    public void exportLogic(BufferedReader reader){
        ConsoleUtils utils = new ConsoleUtils();
        try {
            List<Account> availableAccounts = service.getAccountsByUserEmail(userEmail);
            System.out.println("USER ACCOUNTS");
            utils.accountTable(availableAccounts);
            List<Long> availableAccountsIds = new ArrayList<>();
            for (Account a: availableAccounts){
                availableAccountsIds.add(a.getId());
            }
            System.out.println("Enter the id of account: ");
            long id = utils.chooseTheOwnAccount(reader, availableAccountsIds);
            System.out.println("Please, enter the dates to make report\nDate format: dd/mm/yyyy");
            System.out.println("From date: ");
            Timestamp from = utils.readDate(reader);
            System.out.println("Check to current date? Y/N");
            Timestamp to;
            if(reader.readLine().toUpperCase(Locale.ROOT).equals("Y"))
                to = new Timestamp(System.currentTimeMillis());
            else {
                System.out.println("To date: ");
                to = utils.readDate(reader);
            }
            List<OperationExport> list = service.findOperationsInPeriod(id,from,to);
            writer.exportReport(fileToExport,list,service.getIncomesAndBalanceForPeriod(list));
            System.out.println("The report was exported. Look at file operations.csv in the root of project");
        }catch (ResourceNotFoundException | IOException | OperationDataLayerException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
