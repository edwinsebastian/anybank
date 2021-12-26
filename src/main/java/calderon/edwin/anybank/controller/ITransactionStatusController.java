package calderon.edwin.anybank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ITransactionStatusController<ReqDto, ResDto> {

    @PostMapping
    ResponseEntity<ResDto> createResource(@RequestBody ReqDto reqDto);
}
