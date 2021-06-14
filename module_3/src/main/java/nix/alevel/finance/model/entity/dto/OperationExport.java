package nix.alevel.finance.model.entity.dto;

import nix.alevel.finance.model.entity.Category;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OperationExport{
    private Long id;
    private Instant passedAt;
    private List<String> categories = new ArrayList<>();
    private Long transaction;


    public OperationExport(Long id, Instant passedAt, List<String> categories, Long transaction) {
        this.id = id;
        this.passedAt = passedAt;
        this.categories = categories;
        this.transaction = transaction;
    }

    public OperationExport(Instant passedAt, Long transaction) {
        this.passedAt = passedAt;
        this.transaction = transaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getPassedAt() {
        return passedAt;
    }

    public void setPassedAt(Instant passedAt) {
        this.passedAt = passedAt;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Long getTransaction() {
        return transaction;
    }

    public void setTransaction(Long transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "OperationExport{" +
                "id=" + id +
                ", passedAt=" + passedAt +
                ", transaction=" + transaction +
                '}';
    }
}
