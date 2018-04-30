package nl.thewally.loginmanager.usermanagement.errorhandler;

public enum ErrorCode {
    ERROR0001 ("0001", "User not found."),
    ERROR0002 ("0002", "User already available."),
    ERROR1001 ("1001", "Session not found."),
    ERROR1002 ("1002", "User cannot create users."),
    ERROR1003 ("1003", "User cannot create user groups."),
    ERROR1004 ("1004", "User cannot add users to user groups."),
    ERROR9999 ("9999", "System error: ");

    private String errorCode;
    private String errorMessage;

    ErrorCode(String errorcode, String errorMessage) {
        this.errorCode = errorcode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode(){
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
