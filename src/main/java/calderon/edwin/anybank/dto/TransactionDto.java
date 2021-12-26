package calderon.edwin.anybank.dto;

import calderon.edwin.anybank.model.AccountModel;
import calderon.edwin.anybank.model.TransactionModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Data
public class TransactionDto {
    private UUID reference;
    private UUID accountIban;
    private Date date = new Date();
    private double amount;
    private double fee;
    private String description;

    public TransactionDto(TransactionModel transactionModel){
        this.reference = transactionModel.getId();
        this.accountIban = transactionModel.getAccountModel().getId();
        this.date = transactionModel.getDate();
        this.amount = transactionModel.getAmount();
        this.fee = transactionModel.getFee();
    }

    public static TransactionModel toTransactionModel(TransactionDto transactionDTO, AccountModel accountModel){
        return new TransactionModel(
                transactionDTO.getReference(),
                accountModel,
                transactionDTO.getDate(),
                transactionDTO.getAmount(),
                transactionDTO.getFee(),
                transactionDTO.getDescription()
        );
    }
}
