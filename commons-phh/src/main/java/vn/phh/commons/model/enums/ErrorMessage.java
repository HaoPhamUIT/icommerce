package vn.phh.commons.model.enums;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vn.phh.commons.constants.CommonConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public enum ErrorMessage {

    /*
    OTP
     */
    ERROR_MISSING_DATA_PROFILE(1001, "Missing info customer please update info in profile", new HashMap<String, String>() {{
        put(CommonConstants.VIETNAMESE_LANGUAGE, "Bạn đang thiếu thông tin cá nhân, vui lòng cập nhập thông tin cá nhân");
    }});


    private Integer code;
    private String message;
    private Map<String, String> translator;
    private static Map<Integer, ErrorMessage> mapping;
    private String[] bindingData;

    private ErrorMessage(Integer code, String message, Map<String, String> translator) {
        this.code = code;
        this.message = message;
        this.translator = translator;
    }

    public static ErrorMessage getByCode(Integer code) {
        if (mapping == null) {
            initMapping();
        }
        return mapping.get(code);
    }

    private static void initMapping() {
        mapping = new HashMap<>();
        for (ErrorMessage errorMessage : values()) {
            mapping.put(errorMessage.getCode(), errorMessage);
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage(String language) {
        String message = translator.get(language);
        if (message != null)
            return binding(message);
        else
            return binding(this.message);
    }

    public String getMessage() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requireLanguage = request.getHeader(CommonConstants.HEADER_TYPE_LANGUAGE);
        requireLanguage = requireLanguage == null ? CommonConstants.BLANK : requireLanguage;
        switch (requireLanguage) {
            case CommonConstants.VIETNAMESE_LANGUAGE:
                break;
            default:
                requireLanguage = CommonConstants.ENGLISH_LANGUAGE;
        }
        String message = translator.get(requireLanguage);
        if (message != null)
            return binding(message);
        else
            return binding(this.message);
    }

    private String binding(String message) {
        String result = String.format(message, bindingData);
        bindingData = null;
        return result;
    }

    public ErrorMessage binding(String... bindingData) {
        this.bindingData = bindingData;
        return this;
    }
}
