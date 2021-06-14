package nix.alevel.finance.exeption;

public class OperationDataLayerException extends DataAccessException{
    public OperationDataLayerException(Throwable cause) {
        super(cause);
    }
    public OperationDataLayerException(String message){
        super(message);
    }
    public OperationDataLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
