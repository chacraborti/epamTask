package com.epam.sidarovich.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ilona on 11.05.15.
 */
public class EntranceValidator {
    public static final String EMAIL_PATTERN="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    public static final String LETTER_UPPER_CASE="[A-Z]";
    public static final String LETTER_LOWER_CASE="[a-z]";
    public static final String NUMBER="\\d";
    public static final int PASSWORD_LENGTH=8;

    /**
     * Validate email
     * @param email
     * @return
     */
    public boolean isValidEmailAddress(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher m = pattern.matcher(email);
        return m.matches();
    }

    /**
     * Validate password
     * @param pass1
     * @param pass2
     * @return
     */
    public boolean isValidPassword(String pass1, String pass2) {
        Pattern patternUpperCase = Pattern.compile(LETTER_UPPER_CASE);
        Pattern patternLowerCase = Pattern.compile(LETTER_LOWER_CASE);
        Pattern patternNumber = Pattern.compile(NUMBER);

        if (pass1 == null || pass2 == null) {
            return false;
        }

        if (pass1.isEmpty() || pass2.isEmpty()) {
            return false;
        }

        if (pass1.equals(pass2)) {

            if (pass1.length() < PASSWORD_LENGTH) {
                return false;
            }
            if (!patternUpperCase.matcher(pass1).find()) {
                return false;
            }
            if (!patternLowerCase.matcher(pass1).find()) {
                return false;
            }
            if (!patternNumber.matcher(pass1).find()) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
