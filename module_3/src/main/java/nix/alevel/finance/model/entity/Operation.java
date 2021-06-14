package nix.alevel.finance.model.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "operations")
public class Operation <T extends Category> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToMany(targetEntity = Category.class)
    @JoinTable(
            name = "operations_categories",
            joinColumns = {@JoinColumn(name = "operation_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private Set<T> categories = new HashSet<T>(){};

    @Column(name = "transaction", nullable = false)
    private Long transaction;

    @Column(name = "passed_at", nullable = false)
    private Instant passedAt;

    @PrePersist
    public void onCreate() {
        if (passedAt == null) {
            passedAt = Instant.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<T> getCategories() {
        return categories;
    }

    public void setCategories(Set<T> categories) {
        this.categories = categories;
    }

    public Long getTransaction() {
        return transaction;
    }

    public void setTransaction(Long transaction) {
        this.transaction = transaction;
    }

    public Instant getPassedAt() {
        return passedAt;
    }

    public void setPassedAt(Instant passedAt) {
        this.passedAt = passedAt;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Operation{id=").append(id).append(", user=").append(account.getUser().getEmail())
                .append( ", account=").append(account.getAccountName()).append(", categories={");
        Class<?> objClass =  categories.iterator().next().getClass();
        if(objClass.equals(IncomeCategory.class)){
            for ( T c: categories) {
                stringBuilder.append(((IncomeCategory) c).getIncomeName()).append(",");
            }
        }
        if(objClass.equals(ExpenseCategory.class)){
            for ( T c: categories) {
                stringBuilder.append(((ExpenseCategory) c).getExpenseName()).append(",");
            }
        }
        stringBuilder.append("} transaction=").append(transaction).append(", passedAt=").append(passedAt).append('}');
        return stringBuilder.toString();
    }
}
