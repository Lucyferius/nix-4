package nix.alevel.finance.service;

import nix.alevel.finance.exeption.OperationDataLayerException;
import nix.alevel.finance.exeption.ResourceNotFoundException;
import nix.alevel.finance.model.entity.Account;
import nix.alevel.finance.model.entity.User;
import nix.alevel.finance.model.entity.dto.OperationExport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCOperationService {
    private final Connection connection;

    public JDBCOperationService(Connection connection) {
        this.connection = connection;
    }

    public List<OperationExport> findOperationsInPeriod(Long accountId, Timestamp from, Timestamp to) throws OperationDataLayerException {
        try (PreparedStatement getValue = connection.prepareStatement(
                "SELECT o.id, o.passed_at, o.transaction, public.categories.expense_name, public.categories.income_name " +
                        " FROM public.operations o " +
                        " INNER JOIN public.operations_categories ON operations_categories.operation_id = o.id " +
                        " INNER JOIN public.categories on operations_categories.category_id = categories.id " +
                        " WHERE o.passed_at >= ? AND o.passed_at <= ? " +
                        " AND o.account_id = ? " +
                        " ORDER BY o.id ", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            getValue.setTimestamp(1, from);
            getValue.setTimestamp(2, to);
            getValue.setLong(3, accountId);
            ResultSet resultSet = getValue.executeQuery();

            List<OperationExport> operationsInPeriod = new ArrayList<>();
            while (resultSet.next()) {
                OperationExport operation = new OperationExport(resultSet.getTimestamp("passed_at").toInstant(),
                        resultSet.getLong("transaction"));
                Long id = resultSet.getLong("id");
                operation.setId(id);
                if (resultSet.getString("income_name") == null)
                    operation.getCategories().add(resultSet.getString("expense_name"));
                else operation.getCategories().add(resultSet.getString("income_name"));
                while (resultSet.next()) {
                    Long nextId = resultSet.getLong("id");
                    if (nextId.equals(id)) {
                        if (resultSet.getString("income_name") == null)
                            operation.getCategories().add(resultSet.getString("expense_name"));
                        else operation.getCategories().add(resultSet.getString("income_name"));
                    } else {
                        resultSet.previous();
                        break;
                    }
                }
                operationsInPeriod.add(operation);
            }
            if(operationsInPeriod.isEmpty()) throw new OperationDataLayerException("Nothing was found for such dates");
            return operationsInPeriod;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new OperationDataLayerException("Operations cann`t be exported", e);
        }
    }

    public Map<String, Long> getIncomesAndBalanceForPeriod(List<OperationExport> operations) {
        Map<String, Long> map = new HashMap<>();
        long income = 0, balanceForPeriod = 0;
        for (OperationExport o : operations) {
            long transaction = o.getTransaction();
            balanceForPeriod += transaction;
            if (transaction > 0)
                income += transaction;
        }
        map.put("income", income);
        map.put("balance", balanceForPeriod);
        return map;
    }
    public List<Account> getAccountsByUserEmail(String userEmail) throws ResourceNotFoundException{
        List<Account> accounts = new ArrayList<>();
        try (PreparedStatement getValue = connection.prepareStatement(
                "select a.id,a.account_name,a.balance from public.accounts a " +
                        "inner join public.users on public.users.id=a.user_id " +
                        "where public.users.email = ?")) {
            getValue.setString(1, userEmail);
            ResultSet resultSet = getValue.executeQuery();
            while (resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setAccountName(resultSet.getString("account_name"));
                account.setBalance(resultSet.getLong("balance"));
                accounts.add(account);
            }
            if(accounts.isEmpty())  throw new ResourceNotFoundException("User should have accounts");
            return accounts;
        } catch (Exception e) {
            throw new ResourceNotFoundException(userEmail, User.class);
        }
    }
}
