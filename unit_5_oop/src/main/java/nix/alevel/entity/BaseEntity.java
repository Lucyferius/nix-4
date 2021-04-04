package nix.alevel.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity {
    String id;
    Date created;
    public BaseEntity() {
        this.created = new Date();
    }

    @Override
    public String toString() {
        return  "id= " + id + " " +
                ", created=" + created + " ";
    }
}
