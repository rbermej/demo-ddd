package poc.ddd.demo.shared.infrastructure.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/health-check")
@Api(tags="HealthCheck")
public final class HealthCheckGetController {

    @GetMapping
    public HashMap<String, String> healthCheck() {
        HashMap<String, String> status = new HashMap<>();
        status.put("status", "ok");
        return status;
    }

}
