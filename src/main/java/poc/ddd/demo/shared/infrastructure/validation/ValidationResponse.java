package poc.ddd.demo.shared.infrastructure.validation;

import java.util.HashMap;
import java.util.List;

public final class ValidationResponse {
    private final HashMap<String, List<String>> validationErrors;

    public ValidationResponse(HashMap<String, List<String>> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Boolean hasErrors() {
        return !validationErrors.isEmpty();
    }

    public HashMap<String, List<String>> errors() {
        return validationErrors;
    }
}
