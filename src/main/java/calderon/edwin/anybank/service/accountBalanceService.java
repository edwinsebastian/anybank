package calderon.edwin.anybank.service;

import calderon.edwin.anybank.dto.AccountDto;
import calderon.edwin.anybank.exception.ZeroBalanceException;
import calderon.edwin.anybank.model.AccountModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class accountBalanceService implements IAccountBalanceService{
    private final ICrudService<AccountModel, AccountDto> accountService;

    @Override
    public void validateAccountBalance(BigDecimal balance, BigDecimal transactionTotal) {
        if(balance.subtract(transactionTotal.abs()).compareTo(BigDecimal.ZERO) < 0){
            throw new ZeroBalanceException("Not allowed. Account balance will be bellow 0");
        }
    }

    @Override
    public void creditAccount(AccountModel accountModel, BigDecimal transactionTotal){
        accountModel.setBalance(accountModel.getBalance().add(transactionTotal));
        accountService.updateEntity(accountModel);
    }

    @Override
    public void debitAccount(AccountModel accountModel, BigDecimal transactionTotal){
        this.validateAccountBalance(accountModel.getBalance(), transactionTotal);
        accountModel.setBalance(accountModel.getBalance().subtract(transactionTotal.abs()));
        accountService.updateEntity(accountModel);
    }

}

