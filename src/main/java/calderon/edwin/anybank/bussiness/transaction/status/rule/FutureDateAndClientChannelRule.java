package calderon.edwin.anybank.bussiness.transaction.status.rule;

import calderon.edwin.anybank.bussiness.transaction.status.ITransactionStatusRule;
import calderon.edwin.anybank.dto.TransactionStatusResDto;
import calderon.edwin.anybank.enums.TransactionStatusEnum;
import calderon.edwin.anybank.model.TransactionModel;
import org.springframework.stereotype.Component;

@Component
public class FutureDateAndClientChannelRule implements ITransactionStatusRule {
    @Override
    public String getType() {
        return "futureDateAndCLIENTChannelRule";
    }

    @Override
    public TransactionStatusResDto checkTransactionStatus(TransactionModel transactionModel) {
        return getPartialTransactionResDto(transactionModel, TransactionStatusEnum.FUTURE);
    }
}
