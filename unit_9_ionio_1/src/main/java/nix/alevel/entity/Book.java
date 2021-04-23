package nix.alevel.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book extends BaseEntity{
    private String name;
    private List<String> authors;

    public Book(){
        super();
        this.authors = new ArrayList<>();
    }
    public void setName(String name){
        this.name = name;
    }
}
