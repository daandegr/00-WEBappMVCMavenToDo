package validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.UserForm;

public class UserValidator {

    private static final String USERNAME_PATTERN = "[a-zA-Z]{3,10}";
    private static final String STRAAT_PATTERN = "[a-zA-Z]{3,50}";
    private static final String PLAATS_PATTERN = "[a-zA-Z]{3,50}";
    private static final String HUISNUMMER_PATTERN = "([0-9]){1,}([a-z]){0,3}";
    List<String> errors = new ArrayList<String>();

    public List<String> validate(UserForm userForm) {

        String naam = userForm.getName();
        String straat = userForm.getStreetAddress();
        String plaats = userForm.getCity();
        String hn = userForm.getHuisNummer();
        
        if (naam == null || naam.trim().isEmpty()) {
            errors.add("User must have a name");
        } else {
            if (!validUsername(naam)) {
                errors.add("Naam: must be 3 to 10 characters A-Z");
            }
        }
        if (plaats == null || plaats.trim().isEmpty()) {
            errors.add("plaats must have a name");
        } else {
            if (!validPlaats(plaats)) {
                errors.add("Plaats: must be 3 to 50 characters A-Z");
            }
        }
        
        if (straat == null || straat.trim().isEmpty()) {
            errors.add("Straatnaam must have a name");
        } else {
            if (!validStraatnaam(straat)) {
                errors.add("Straatnaam: must be 3 to 50 characters A-Z");
            }            
        }
        
        if (hn == null || hn.trim().isEmpty()) {
            errors.add("Straatnaam must have a name");
        } else {
            if (!validHuisNummer(hn)) {
                errors.add("Huisnummer: must be a number");
            }
        }
        return errors;
    }

    public boolean validUsername(String s) {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public boolean validStraatnaam(String s) {
        Pattern pattern = Pattern.compile(STRAAT_PATTERN);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public boolean validPlaats(String s) {
        Pattern pattern = Pattern.compile(PLAATS_PATTERN);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
    
    public boolean validHuisNummer(String s) {
        Pattern pattern = Pattern.compile(HUISNUMMER_PATTERN);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
