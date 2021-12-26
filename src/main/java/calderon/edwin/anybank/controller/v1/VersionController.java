package calderon.edwin.anybank.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class VersionController {

    @GetMapping("/version")
    public String getVersion() {
        return "1.0";
    }
}
