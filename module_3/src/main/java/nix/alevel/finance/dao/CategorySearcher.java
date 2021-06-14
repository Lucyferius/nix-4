package nix.alevel.finance.dao;

import nix.alevel.finance.exeption.OperationDataLayerException;
import nix.alevel.finance.model.entity.ExpenseCategory;
import nix.alevel.finance.model.entity.IncomeCategory;

import java.util.List;

public interface CategorySearcher {
    List<IncomeCategory> getIncomeCategories() throws OperationDataLayerException;
    List<ExpenseCategory> getExpenseCategories() throws OperationDataLayerException;
    List<IncomeCategory> getIncomeCategoriesById(List<Long> listOfId) throws OperationDataLayerException;
    List<ExpenseCategory> getExpenseCategoriesById(List<Long> listOfId) throws OperationDataLayerException;
}
