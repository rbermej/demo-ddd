package poc.ddd.demo.shared.infrastructure.validation.validators;

import java.io.Serializable;
import java.util.HashMap;
import java.util.regex.Pattern;

public final class IntValidator implements FieldValidator {
    @Override
    public Boolean isValid(String fieldName, HashMap<String, Serializable> fields) {
        try {
            Integer.valueOf((Integer) fields.get(fieldName));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String errorMessage(String fieldName) {
        return String.format("The field <%s> should be of type int", fieldName);
    }
}
