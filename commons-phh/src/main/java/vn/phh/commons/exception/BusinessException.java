package vn.phh.commons.exception;

import vn.phh.commons.model.enums.ErrorMessage;

public class BusinessException extends GenericException {
    private static final long serialVersionUID = 11391777061680414L;

    /**
     *
     * @param message
     * @param status
     */
    public BusinessException(String message, int status) {
        super(message, status);
    }

    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage(), errorMessage.getCode());
    }
}
