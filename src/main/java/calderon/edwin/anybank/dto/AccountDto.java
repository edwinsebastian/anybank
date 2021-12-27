package calderon.edwin.anybank.dto;

import calderon.edwin.anybank.model.AccountModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Data
public class AccountDto implements IDto<AccountModel> {
    private UUID accountIban;
    private BigDecimal balance;

    public AccountDto(AccountModel accountModel){
        this.accountIban = accountModel.getId();
        this.balance = accountModel.getBalance();
    }

    @Override
    public AccountModel toModel(){
        return new AccountModel(this.balance);
    }
}
