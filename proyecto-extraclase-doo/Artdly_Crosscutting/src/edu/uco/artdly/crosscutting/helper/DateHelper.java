package edu.uco.artdly.crosscutting.helper;

import java.sql.Date;
import java.time.LocalDate;


public final class DateHelper {
    public static final LocalDate defaultDate = LocalDate.of(0, 0, 0);

    private DateHelper() {
        
    }
    
    public  static final short getNextYear() {
        return  (short) LocalDate.now().plusYears(1).getYear();
    }
    public Date getDeafultDate() { 
        return Date.valueOf(defaultDate);
    }
    public Date getNow() {
        return Date.valueOf(LocalDate.now());
    }
}
