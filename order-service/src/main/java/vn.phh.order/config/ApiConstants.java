package vn.phh.order.config;

/**
 * @author haopham
 */
public class ApiConstants {


    private ApiConstants() {
    }

    public static final String API_VERSION_1 = "api/v1";
    private static final String ID = "/{id}";


    public static final String PRODUCT = "/product";
    public static final String PRODUCT_END_POINT = PRODUCT;
    public static final String PRODUCT_FILTER = PRODUCT + "/filter";
    public static final String PRODUCT_SEARCH = PRODUCT + "/search";
    public static final String PRODUCT_BY_ID_END_POINT = PRODUCT_END_POINT + ID;


    public static final String ORDER = "/order";
    public static final String ORDER_END_POINT = ORDER;
    public static final String ORDER_BY_ID_END_POINT = ORDER_END_POINT + ID;

}
