package calderon.edwin.anybank.service;

import calderon.edwin.anybank.dto.TransactionDto;
import calderon.edwin.anybank.exception.ResourceNotFoundException;
import calderon.edwin.anybank.model.AccountModel;
import calderon.edwin.anybank.model.TransactionModel;
import calderon.edwin.anybank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class TransactionService implements ICrudService<TransactionModel>{
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Override
    public TransactionModel createEntity(TransactionModel transactionModel) {
        return null;
    }

    public TransactionModel createEntity(TransactionDto transactionDto) {
        TransactionModel transactionModel = validateTransaction(transactionDto);
        return transactionRepository.save(transactionModel);
    }

    private TransactionModel validateTransaction(TransactionDto transactionDto) {
        AccountModel accountModel = accountService.getEntity(transactionDto.getAccountIban());
        TransactionModel transactionModel = TransactionDto.toTransactionModel(transactionDto, accountModel);
        BigDecimal transactionTotal = transactionDto.getAmount().subtract(transactionDto.getFee().abs());

        if(transactionTotal.compareTo(BigDecimal.ZERO) >= 0){
            accountService.creditAccount(accountModel, transactionTotal);
        }else {
            accountService.validateAccountBalance(accountModel.getBalance(), transactionTotal);
            accountService.debitAccount(accountModel, transactionTotal);
        }

        return transactionModel;
    }

    @Override
    public TransactionModel getEntity(UUID id) {
        return transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found."));
    }

    @Override
    public List<TransactionModel> getEntities() {
        logger.info("getEntities");
        return StreamSupport
                .stream(transactionRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

    @Override
    public UUID updateEntity(TransactionModel model) {
        return null;
    }

    @Override
    public UUID deleteEntity(UUID id) {
        return null;
    }
}
