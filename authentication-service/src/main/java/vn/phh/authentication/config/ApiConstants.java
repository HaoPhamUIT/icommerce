package vn.phh.authentication.config;

/**
 * @author haopham
 */
public class ApiConstants {


    private ApiConstants() {
    }

    public static final String API_VERSION_1 = "api/v1";
    private static final String ID = "/{id}";


    public static final String CALL_CENTER_CONFIG = "/call-center";
    public static final String CALL_CENTER_CONFIG_END_POINT = CALL_CENTER_CONFIG;
    public static final String CALL_CENTER_CONFIG_BY_ID_END_POINT = CALL_CENTER_CONFIG_END_POINT + "/{id}";

    public static final String LINE_MANAGEMENT = "/line-management";
    public static final String LINE_MANAGEMENT_END_POINT = LINE_MANAGEMENT;
    public static final String LINE_MANAGEMENT_BY_ID_END_POINT = LINE_MANAGEMENT_END_POINT + "/{id}";
    public static final String LINE_MANAGEMENT_BY_CALL_CENTER_CONFIG_ID_END_POINT = LINE_MANAGEMENT_END_POINT + CALL_CENTER_CONFIG+ "/{id}";

    public static final String CHANNEL_MANAGEMENT = "/channel-management";
    public static final String CHANNEL_MANAGEMENT_END_POINT = CHANNEL_MANAGEMENT;
    public static final String CHANNEL_MANAGEMENT_BY_ID_END_POINT = CHANNEL_MANAGEMENT_END_POINT + "/{id}";
    public static final String CHANNEL_MANAGEMENT_BY_CALL_CENTER_CONFIG_ID_END_POINT = CHANNEL_MANAGEMENT_END_POINT + CALL_CENTER_CONFIG+ "/{id}";


}
