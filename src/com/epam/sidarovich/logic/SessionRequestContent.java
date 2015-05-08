package com.epam.sidarovich.logic;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by ilona on 25.03.15.
 */
public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    // конструкторы
// метод извлечения информации из запроса
    public void extractValues(HttpServletRequest request) {
// реализация
    }
    // метод добавления в запрос данных для передачи в jsp
    public void insertAttributes(HttpServletRequest request) { // реализация
    }

}
