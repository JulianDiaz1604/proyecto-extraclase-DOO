package edu.uco.artdly.crosscutting.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailHelper {
    private static final String DEFAULT_MAIL = "artdlyUser@gmail.com";
    private static final Pattern VALID_MAIL = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
   
   
    public static final boolean trueMailForm(String mail) {
        Matcher mather = VALID_MAIL.matcher(mail);

        if (mather.find()) {
            return true;
        } else {
            return false;
        }
        
    }
    public static final String getDefaultMail() {
        return DEFAULT_MAIL;
    }
    public static final boolean isDefaultMail(String mail) {
        return DEFAULT_MAIL.equals(mail);
    }

}
