package vn.phh.commons.exception;

public class FileProcessingException extends GenericException {

    private static final long serialVersionUID = -7364837335281409576L;

    public FileProcessingException(Exception e) {
        super(e);
        this.status = 509;
    }

    /**
     * Instance with message
     * @param message
     */
    public FileProcessingException(String message) {
        super(message);
        this.status = 509;
    }

    public FileProcessingException(String code, String message) {
        super(code, message, 509);
    }

}
