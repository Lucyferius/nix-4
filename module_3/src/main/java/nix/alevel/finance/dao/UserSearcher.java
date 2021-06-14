package nix.alevel.finance.dao;

import nix.alevel.finance.exeption.ResourceNotFoundException;
import nix.alevel.finance.model.entity.Account;
import nix.alevel.finance.model.entity.User;

import java.util.List;

public interface UserSearcher {
    User getUserByEmail(String email) throws ResourceNotFoundException;
    List<Account> getAccountsOfUser(Long id) throws ResourceNotFoundException;
}
