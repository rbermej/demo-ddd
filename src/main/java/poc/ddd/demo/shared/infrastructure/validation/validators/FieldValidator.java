package poc.ddd.demo.shared.infrastructure.validation.validators;

import java.io.Serializable;
import java.util.HashMap;

public interface FieldValidator {
    Boolean isValid(String fieldName, HashMap<String, Serializable> fields);

    String errorMessage(String fieldName);
}
