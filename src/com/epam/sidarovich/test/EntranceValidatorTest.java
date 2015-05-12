package com.epam.sidarovich.test;

import com.epam.sidarovich.validator.EntranceValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ilona on 12.05.15.
 */
public class EntranceValidatorTest {
    @Test
    public void isValidEmailAddressTest(){
        EntranceValidator validator = new EntranceValidator();
        Pattern pattern =Pattern.compile(EntranceValidator.EMAIL_PATTERN);
        String email = "email@mail.ru";
        Matcher m = pattern.matcher(email);
        boolean expected=m.matches();
        boolean actual = validator.isValidEmailAddress(email);
        Assert.assertEquals(expected,actual);
    }
     @Test
     public void isValidPasswordTestLDifferentPasswords(){
         EntranceValidator validator = new EntranceValidator();
         String password = "d1234567";
         String password2="D1234567";
         boolean expected = password.equals(password2);
         boolean actual = validator.isValidPassword(password,password2);
         Assert.assertEquals(expected,actual);
     }
    @Test
    public void isValidPasswordTestNoUpperCase(){
        Pattern pattern = Pattern.compile(EntranceValidator.LETTER_UPPER_CASE);
        EntranceValidator validator = new EntranceValidator();
        String password = "d1234567";
        String password2 = "d1234567";
        boolean expected = pattern.matcher(password).find();
        boolean actual = validator.isValidPassword(password, password2);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void isValidPasswordTestLessThanEightCharacters(){
        int length = EntranceValidator.PASSWORD_LENGTH;
        EntranceValidator validator = new EntranceValidator();
        String password = "Ad12345";
        String password2="Ad12345";
        boolean expected = (password.length()==8);
        boolean actual = validator.isValidPassword(password,password2);
        Assert.assertEquals(expected,actual);
    }
}
