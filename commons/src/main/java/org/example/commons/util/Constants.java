package org.example.commons.util;

public class Constants {
    /**
     * Ref: https://www.baeldung.com/java-email-validation-regex
     */
    public static final String EMAIL_VALIDATION_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Ref: https://www.baeldung.com/java-regex-validate-phone-numbers
     */
    public static final String PHONE_NUMBER_VALIDATION_REGEX = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
}
