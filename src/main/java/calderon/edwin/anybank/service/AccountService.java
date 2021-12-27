package calderon.edwin.anybank.service;

import calderon.edwin.anybank.dto.AccountDto;
import calderon.edwin.anybank.exception.ResourceNotFoundException;
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
public class AccountService implements ICrudService<AccountModel, AccountDto>{
    private final AccountRepository accountRepository;

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Override
    public AccountModel createEntity(AccountDto dto) {
        return accountRepository.save(dto.toModel());
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
