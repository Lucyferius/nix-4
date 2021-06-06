package com.alevel.hibernate.exeption;

public class ResourceWasNotFoundException extends DataAccessException{
    public ResourceWasNotFoundException(Long id){
        super("The resource with " + id + " not found in database");
    }
    public ResourceWasNotFoundException(Long id, Class<?> resourceClass){
        super("The" + resourceClass.getSimpleName()+" with " + id + " not found in database");
    }
    public ResourceWasNotFoundException(String course){
        super(course);
    }
}
