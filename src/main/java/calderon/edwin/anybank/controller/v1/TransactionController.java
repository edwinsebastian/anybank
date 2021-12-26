package calderon.edwin.anybank.controller.v1;

import calderon.edwin.anybank.controller.ICrudController;
import calderon.edwin.anybank.dto.TransactionDto;
import calderon.edwin.anybank.model.AccountModel;
import calderon.edwin.anybank.model.TransactionModel;
import calderon.edwin.anybank.service.AccountService;
import calderon.edwin.anybank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/transaction")
public class TransactionController implements ICrudController<TransactionModel, TransactionDto> {
    private final AccountService accountService;
    private final TransactionService transactionService;

    @Override
    public ResponseEntity<TransactionDto> createResource(TransactionDto transactionDto) {
        TransactionModel transactionModel = transactionService.createEntity(TransactionDto.toTransactionModel(transactionDto, validateAccountIban(transactionDto.getAccountIban())));
        return ResponseEntity.ok(new TransactionDto(transactionModel));
    }

    private AccountModel validateAccountIban(UUID accountIban) {
        return accountService.getEntity(accountIban);
    }

    @Override
    public ResponseEntity<TransactionModel> getResource(UUID id) {
        return ResponseEntity.ok(transactionService.getEntity(id));
    }

    @Override
    public ResponseEntity<List<TransactionModel>> getResources() {
        return null;
    }

    @Override
    public ResponseEntity<TransactionDto> updateResource(UUID id, TransactionDto updatedDto) {
        return null;
    }

    @Override
    public ResponseEntity<TransactionDto> deleteResource(UUID id) {
        return null;
    }
}
