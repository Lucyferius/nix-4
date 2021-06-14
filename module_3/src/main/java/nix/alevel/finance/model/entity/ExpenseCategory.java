package nix.alevel.finance.model.entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("expense")
public class ExpenseCategory extends Category {

    @Column(name = "expense_name")
    private String expenseName;

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    @Override
    public String toString() {
        return "ExpenseCategory{" +
                "expenseName='" + expenseName + '\'' +
                '}';
    }
}
