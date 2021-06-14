package nix.alevel.finance.exeption;

public class ResourceNotFoundException extends DataAccessException{
    public ResourceNotFoundException(Long id){
        super("The resource with " + id + " not found");
    }
    public ResourceNotFoundException(String data, Class<?> resourceClass){
        super("The" + resourceClass.getSimpleName()+" with " + data + " not found");
    }
    public ResourceNotFoundException(String course){
        super(course);
    }
}
