package calderon.edwin.anybank.service;

import calderon.edwin.anybank.model.TransactionModel;
import calderon.edwin.anybank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class TransactionService implements ICrudService<TransactionModel>{
    private final TransactionRepository transactionRepository;

    Logger logger = LoggerFactory.getLogger(TransactionService.class);

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
        logger.info("getEntities");
        return StreamSupport
                .stream(transactionRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());
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
