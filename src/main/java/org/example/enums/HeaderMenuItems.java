package org.example.enums;

public enum HeaderMenuItems {

    LOGIN_PAGE("/login"),

    CART_PAGE("/view_cart"),
    PRODUCTS_PAGE("/products"),
    CONTACT_US_PAGE("/contact_us"),
    API_TESTING_PAGE("/api_list"),
    SIGN_UP_PAGE("/api_list");


    private String href;

     HeaderMenuItems(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }
}
