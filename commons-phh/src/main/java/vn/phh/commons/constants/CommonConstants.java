package vn.phh.commons.constants;

public class CommonConstants {

    public static final double EARTH_RADIUS = 6371.01;

    public static final long SECOND = 1000;
    public static final long MINUTE = 36000;
    public static final String VIETNAM_TIME_ZONE = "Asia/Saigon";

    public static final String DONE = "DONE";
    public static final String STATUS_OK = "OK";
    public static final String STATUS_FAILED = "FAILED";
    public static final String MESSAGE_OK = "OK";
    public static final String MESSAGE_FAILED = "FAILED";
    public static final String ACTIVE = "ACTIVE";
    public static final String INACTIVE = "INACTIVE";
    public static final String NULL = "null";

    public static final String VERSION = "VERSION : v";
    public static final String XML_ESCAPE_AND_CHARACTER = "&#038;";
    public static final String API_KEY_NAME = "ApiKey ";
    public static final String BASIC_AUTH = "Basic ";

    public static final String MATCHED = "MATCHED";
    public static final String NOT_MATCHED = "NOT_MATCHED";
    public static final String CACHED = "CACHED";
    public static final String GENERATED = "GENERATED";

    public static final String MALE = "MALE";
    public static final String FEMALE = "FEMALE";

    //ENVIRONMENT
    public static final String ENV_LOCAL_DEV = "ld";
    public static final String ENV_DEV = "dev";
    public static final String ENV_QC = "qc";
    public static final String ENV_STAGING = "stag";
    public static final String ENV_PRODUCTION = "prod";

    //REQUEST AUTH
    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Mailinh ";
    public static final String JWT_TOKEN_TYPE = "Mailinh ";
    public static final String ROLE = "role";
    public static final String PRODUCT = "product";
    public static final String PERMISSIONS = "permissions";
    public static final String DEVICE_ID = "deviceId";
    public static final String PAGE_ACCESS_TOKEN = "pageAccessToken";

    public static final String CLIENT_ID = "Client-Id";
    public static final String CLIENT_SECRET = "Client-Secret";

    public static final String MISSING_PUBLIC_KEY = "public-key is missing";
    public static final String MISSING_PRIVATE_KEY = "private-key is missing";

    //SERVICE
    public static final String METHOD_SAVE = "save";
    public static final String METHOD_SEARCH = "search";
    public static final String METHOD_GET_BY_ID = "getById";
    public static final String METHOD_GET_ALL = "getAll";
    public static final String METHOD_UPDATE = "update";
    public static final String METHOD_DELETE = "delete";
    public static final String METHOD_GENERATE = "generate";
    public static final String METHOD_VALIDATE = "validate";

    //PARAM
    public static final String PARAM_ID = "id";
    public static final String PARAM_PAGE_SIZE = "pageSize";
    public static final String PARAM_PAGE_NUMBER = "pageNumber";
    public static final String PARAM_TOTAL_ELEMENTS = "totalElements";
    public static final String PARAM_TOTAL_PAGES = "totalPages";
    public static final String ASC_SORT = "ASC";
    public static final String DESC_SORT = "DESC";
    public static final String ATTRIBUTE_DEFAULT = "id";
    public static final String DEFAULT_MAX_NO_OF_ROWS = "20";
    public static final String DEFAULT_PAGE = "0";

    public static final String DELIMITER_ATTRIBUTE = ";";
    public static final String BLANK = "";
    public static final String DOT = ".";
    public static final String SLASH = "/";
    public static final String STAR = "*";
    public static final String SPACE = " ";
    public static final String MINUS = "-";
    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String OPENING_SQUARE_BRACKET = "[";
    public static final String CLOSING_SQUARE_BRACKET = "]";

    //date time format
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String DATE_TIME_FORMAT_ZULU = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_TIME_START_DATE_FORMAT = "T00:00:00.000Z";

    //Kilomet, met
    public static final String KILOMETER = "km";
    public static final String METER = "m";

    //Multi language
    public static final String HEADER_TYPE_LANGUAGE = "Accept-Language";
    public static final String VIETNAMESE_LANGUAGE = "vi";
    public static final String ENGLISH_LANGUAGE = "en";


    public static final String KAFKA_TOPIC_ORDER = "message.order";

    //redis key
    public static final String REDIS_KEY_TRIP_CANCEL_DRIVERS = "trip_cancel_";
    public static final String REDIS_KEY_TRIP_DISPATCH_DRIVERS = "trip_dispatch_";

    //error
    public static final String INVALID_LANGUAGE = "this language haven't unsupported by system yet";
    public static final String CLIENT_NOT_EXIT = "Client not exits";
    public static final String ACCESS_DENIED = "Access Denied";

    //db
    public static final String DB_PROFILE = "profile";


    private CommonConstants() {
    }
}
