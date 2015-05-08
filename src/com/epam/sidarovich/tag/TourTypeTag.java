package com.epam.sidarovich.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Locale;

@SuppressWarnings("serial")
public class TourTypeTag extends TagSupport {
        private String tourType;

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    @Override
    public int doStartTag() throws JspException {
        try {

            String path=null;
            if ("REST".equalsIgnoreCase(tourType)) {
                path="img/rest.jpg";
            }
            else if ("EXCURSION".equalsIgnoreCase(tourType)){
                path="img/excursion.jpg";
            }
            else if ("SHOPPING".equalsIgnoreCase(tourType)){
                path="img/shopping.jpg";
            }
            else{
                path="img/nichego0_0.jpg";
            }
            pageContext.getOut().write("<img src=" + path + ">");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}