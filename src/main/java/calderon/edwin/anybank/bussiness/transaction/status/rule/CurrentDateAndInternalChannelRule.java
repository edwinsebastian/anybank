package calderon.edwin.anybank.bussiness.transaction.status.rule;

import calderon.edwin.anybank.bussiness.transaction.status.ITransactionStatusRule;
import calderon.edwin.anybank.dto.TransactionStatusResDto;
import calderon.edwin.anybank.enums.TransactionStatusEnum;
import calderon.edwin.anybank.model.TransactionModel;
import org.springframework.stereotype.Component;

@Component
public class CurrentDateAndInternalChannelRule implements ITransactionStatusRule {
    @Override
    public String getType() {
        return "currentDateAndINTERNALChannelRule";
    }

    @Override
    public TransactionStatusResDto checkTransactionStatus(TransactionModel transactionModel) {
        return getFullTransactionResDto(transactionModel, TransactionStatusEnum.PENDING);
    }
}
