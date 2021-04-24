package nix.alevel.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Author extends BaseEntity{

    private String firstName;
    private String lastName;
    private List<String> bookList;

    public void setBookList(List<String> list){
        this.bookList = list;
    }

    public Author(){
        super();
        this.bookList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
