package vn.phh.product.config;

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


    public static final String CART = "/cart";
    public static final String ORDER = "/order";
    public static final String CART_END_POINT = CART;
    public static final String CART_BY_ID_END_POINT = CART_END_POINT + ID;

}
