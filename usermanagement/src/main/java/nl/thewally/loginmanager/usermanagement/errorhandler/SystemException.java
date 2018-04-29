package nl.thewally.loginmanager.usermanagement.errorhandler;

public class SystemException extends Exception {
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMessage;


    public SystemException() {
        super();
    }

    public SystemException(ErrorCode error) {
        super(error.getErrorMessage());
        this.errorCode = error.getErrorCode();
        this.errorMessage = error.getErrorMessage();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
