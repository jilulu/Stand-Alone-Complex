package config;

/**
 * Created by jamesji on 08/03/2017.
 */
public class Config {
    public static final String CINEMA_NAME = "Maidragon Book";
    public static final String CINEMA_NAME_CJK = "メイドラゴン";
    public static final String CINEMA_NAME_ANNOTATED = "<ruby>Maidragon<rp>(</rp><rt><small>" + Config.CINEMA_NAME_CJK + "</small></rt><rp>)</rp></ruby> book";
    public static final int FREE_SHIPPING_OFFSET = 99;

    public static final String LOGIN_NAME = "Login";

    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";
    // sign in page
    public static final String SIGNIN_TITLE = "Sign In";
    public static final String SIGNIN_EMAIL_LABEL = "Your email";
    public static final String SIGNIN_PASSWORD_LABEL = "Password";
    public static final String SIGNIN_REMEMBERME_CHECKBOX = "Remember me";
    public static final String SIGNIN_SIGNIN_BUTTON = "Sign in";
    public static final String SIGNIN_SIGNUP_BUTTON = "Sign up";

    // sign up page
    public static final String SIGNUP_TITLE = "Sign Up";
    public static final String SIGNUP_EMAIL_LABEL = "Email";
    public static final String SIGNUP_EMAIL_HELP = "Your email address will be used for sign in.";
    public static final String SIGNUP_USERNAME_LABEL = "Username";
    public static final String SIGNUP_USERNAME_HELP = "No space and special character is allowed.";
    public static final String SIGNUP_PASSWORD_LABEL = "Password";
    public static final String SIGNUP_LASTNAME_LABEL = "Last name";
    public static final String SIGNUP_FIRSTNAME_LABEL = "First name";
    public static final String SIGNUP_SIGNUP_BUTTON = "Sign me up!";
}
