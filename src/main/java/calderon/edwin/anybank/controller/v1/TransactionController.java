package calderon.edwin.anybank.controller.v1;

import calderon.edwin.anybank.controller.ICrudController;
import calderon.edwin.anybank.dto.TransactionDto;
import calderon.edwin.anybank.model.TransactionModel;
import calderon.edwin.anybank.service.ICrudEntitySearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/transaction")
public class TransactionController implements ICrudController<TransactionModel, TransactionDto> {
    private final ICrudEntitySearcherService<TransactionModel, TransactionDto> transactionService;

    @Override
    public ResponseEntity<TransactionDto> createResource(TransactionDto transactionDto) {
        return ResponseEntity.ok(new TransactionDto(transactionService.createEntity(transactionDto)));
    }

    @Override
    public ResponseEntity<TransactionModel> getResource(UUID id) {
        return ResponseEntity.ok(transactionService.getEntity(id));
    }

    @Override
    public ResponseEntity<List<TransactionModel>> getResources() {
        return ResponseEntity.ok(transactionService.getEntities());
    }

    @GetMapping("/iban/{id}")
    public ResponseEntity<List<TransactionModel>> getResources(@PathVariable UUID id,
                                                               @RequestParam(defaultValue = "date") String sortBy,
                                                               @RequestParam(defaultValue = "desc") String order
                                                               ) {
        return ResponseEntity.ok(transactionService.getEntitiesByFkey(id, sortBy, order));
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
