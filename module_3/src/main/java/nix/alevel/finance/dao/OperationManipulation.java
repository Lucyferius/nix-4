package nix.alevel.finance.dao;

import nix.alevel.finance.exeption.OperationDataLayerException;
import nix.alevel.finance.model.entity.Category;
import nix.alevel.finance.model.entity.Operation;

import java.time.Instant;
import java.util.List;

public interface OperationManipulation {
    <T extends Category> void save (Operation<T> operation) throws OperationDataLayerException;
    List<Operation> findAllOperationsOfAccount(Long accountId) throws OperationDataLayerException;
}
