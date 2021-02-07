package vn.phh.authentication.config;

/**
 * @author haopham
 */
public class ApiConstants {


    private ApiConstants() {
    }

    public static final String API_VERSION_1 = "api/v1";
    private static final String ID = "/{id}";


    public static final String USER = "/user";
    public static final String USER_LOGIN = USER+ "/login";

    public static final String PROFILE = "/profile";
    public static final String PROFILE_END_POINT = PROFILE;
    public static final String PROFILE_BY_ID_END_POINT = PROFILE_END_POINT + "/{id}";

}
