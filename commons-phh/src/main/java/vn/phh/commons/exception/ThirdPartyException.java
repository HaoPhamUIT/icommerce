package vn.phh.commons.exception;

public class ThirdPartyException extends GenericException {


    private static final long serialVersionUID = -5266152822025849090L;

    public ThirdPartyException(Exception e) {
        super(e);
        this.status = 424;
    }

    /**
     * Instance with message
     * @param message
     */
    public ThirdPartyException(String message) {
        super(message);
        this.status = 424;
    }

    public ThirdPartyException(String code, String message) {
        super(code, message, 424);
    }

}
