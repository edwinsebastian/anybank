package calderon.edwin.anybank.bussiness.transaction.status;

import calderon.edwin.anybank.dto.TransactionStatusResDto;
import calderon.edwin.anybank.enums.TransactionStatusEnum;
import calderon.edwin.anybank.model.TransactionModel;

public interface ITransactionStatusRule {
    default TransactionStatusResDto getFullTransactionResDto(TransactionModel transactionModel, TransactionStatusEnum status){
        return new TransactionStatusResDto(
                transactionModel.getId(),
                status,
                transactionModel.getAmount(),
                transactionModel.getFee()
        );
    }

    default TransactionStatusResDto getPartialTransactionResDto(TransactionModel transactionModel, TransactionStatusEnum status){
        return new TransactionStatusResDto(
                transactionModel.getId(),
                status,
                transactionModel.getAmount().subtract(transactionModel.getFee().abs())
        );
    }

    String getType();

    TransactionStatusResDto checkTransactionStatus(TransactionModel transactionModel);
}
