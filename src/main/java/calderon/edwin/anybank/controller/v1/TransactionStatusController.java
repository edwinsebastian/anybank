package calderon.edwin.anybank.controller.v1;

import calderon.edwin.anybank.controller.ITransactionStatusController;
import calderon.edwin.anybank.dto.TransactionStatusReqDto;
import calderon.edwin.anybank.dto.TransactionStatusResDto;
import calderon.edwin.anybank.service.ITransactionStatusService;
import calderon.edwin.anybank.service.TransactionStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/transaction/status")
public class TransactionStatusController implements ITransactionStatusController<TransactionStatusReqDto, TransactionStatusResDto> {
//    private final TransactionStatusService transactionStatusService;
    private final ITransactionStatusService<TransactionStatusReqDto, TransactionStatusResDto> transactionStatusService;

    @Override
    public ResponseEntity<TransactionStatusResDto> createResource(TransactionStatusReqDto reqDto) {
        return ResponseEntity.ok(transactionStatusService.status(reqDto));
    }
}
