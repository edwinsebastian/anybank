package calderon.edwin.anybank.service;

import calderon.edwin.anybank.model.TransactionModel;
import calderon.edwin.anybank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TransactionService implements ICrudService<TransactionModel>{
    private final TransactionRepository transactionRepository;

    @Override
    public TransactionModel createEntity(TransactionModel model) {
        return transactionRepository.save(model);
    }

    @Override
    public TransactionModel getEntity(UUID id) {
        return transactionRepository.findById(id).orElseThrow();
    }

    @Override
    public List<TransactionModel> getEntities() {
        return null;
    }

    @Override
    public UUID updateEntity(UUID id, TransactionModel model) {
        return null;
    }

    @Override
    public UUID deleteEntity(UUID id) {
        return null;
    }
}
