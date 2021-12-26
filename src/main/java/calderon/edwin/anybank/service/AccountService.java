package calderon.edwin.anybank.service;

import calderon.edwin.anybank.exception.ResourceNotFoundException;
import calderon.edwin.anybank.exception.ZeroBalanceException;
import calderon.edwin.anybank.model.AccountModel;
import calderon.edwin.anybank.repository.AccountRepository;
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
public class AccountService implements ICrudService<AccountModel>{
    private final AccountRepository accountRepository;

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Override
    public AccountModel createEntity(AccountModel model) {
        return accountRepository.save(model);
    }

    @Override
    public AccountModel getEntity(UUID id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account IBAN not found."));
    }

    @Override
    public List<AccountModel> getEntities() {
        logger.info("getEntities");
        return StreamSupport
                .stream(accountRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

    public void validateAccountBalance(double balance, double transactionTotal) {
        if(balance - transactionTotal < 0){
            throw new ZeroBalanceException("Not allowed. Account balance will be bellow 0");
        }
    }

    public void creditAccount(AccountModel accountModel, double transactionTotal){
        logger.info("credit account");
        accountModel.setBalance(accountModel.getBalance() + transactionTotal);
        this.updateEntity(accountModel);
    }

    public void debitAccount(AccountModel accountModel, double transactionTotal){
        logger.info("debit account");
        accountModel.setBalance(accountModel.getBalance() - transactionTotal);
        this.updateEntity(accountModel);
    }

    @Override
    public UUID updateEntity(AccountModel model) {
        logger.info("updateEntity {}", model);
        return accountRepository.save(model).getId();
    }

    @Override
    public UUID deleteEntity(UUID id) {
        return null;
    }
}
