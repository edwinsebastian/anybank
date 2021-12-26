package calderon.edwin.anybank.dto;

import calderon.edwin.anybank.model.AccountModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Data
public class AccountDto {
    private UUID accountIban;
    private double balance;

    public AccountDto(AccountModel accountModel){
        this.accountIban = accountModel.getId();
        this.balance = accountModel.getBalance();
    }

    public static AccountModel toAccountModel(AccountDto accountDto){
        return new AccountModel();
    }
}
