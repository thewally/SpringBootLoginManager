package nl.thewally.loginmanager.usermanagement.errorhandler;

import java.util.HashMap;
import java.util.Map;

public class FunctionalErrorHandler {
    private String errorcode;
    private String errortext;

    public FunctionalErrorHandler(String errorcode, String errortext) {
        this.errorcode=errorcode;
        this.errortext=errortext;
    }

    public Map get() {
        Map<String, String> error = new HashMap<>();
        error.put("error found", errorcode+": "+errortext);
        return error;
    }
}
