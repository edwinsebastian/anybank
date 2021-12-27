package calderon.edwin.anybank.service;

import calderon.edwin.anybank.model.AccountModel;

import java.math.BigDecimal;

public interface IAccountBalanceService {
    void validateAccountBalance(BigDecimal balance, BigDecimal transactionTotal);

    void creditAccount(AccountModel accountModel, BigDecimal transactionTotal);

    void debitAccount(AccountModel accountModel, BigDecimal transactionTotal);
}
