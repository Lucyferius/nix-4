package nix.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity {
    private String id;
    private Date created;
    private boolean isExist;
    public BaseEntity() {
        created = new Date();
    }
    public void setIsExist(boolean b){
        isExist = b;
    }
    public boolean getIsExist(){
        return isExist;
    }
    @Override
    public String toString() {
        return  "id= " + id + " " +
                ", created=" + created + " ";
    }
}
