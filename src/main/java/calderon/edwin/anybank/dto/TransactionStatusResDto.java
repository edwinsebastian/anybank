package calderon.edwin.anybank.dto;

import calderon.edwin.anybank.enums.TransactionStatusEnum;
import calderon.edwin.anybank.model.TransactionModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Data
public class TransactionStatusResDto implements IDto<TransactionModel> {
    private UUID reference;
    private TransactionStatusEnum status;
    private BigDecimal amount;
    private BigDecimal fee = null;

    public TransactionStatusResDto(UUID reference, TransactionStatusEnum status) {
        this.reference = reference;
        this.status = status;
    }

    @Override
    public TransactionModel toModel() {
        return null;
    }
}
