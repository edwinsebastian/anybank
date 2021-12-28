package calderon.edwin.anybank.dto;

import calderon.edwin.anybank.enums.TransactionStatusEnum;
import calderon.edwin.anybank.model.TransactionModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionStatusResDto implements IDto<TransactionModel> {
    private UUID reference;
    private TransactionStatusEnum status;
    private BigDecimal amount;
    private BigDecimal fee = null;

    public TransactionStatusResDto(UUID reference, TransactionStatusEnum status) {
        this.reference = reference;
        this.status = status;
    }

    public TransactionStatusResDto(UUID reference, TransactionStatusEnum status, BigDecimal subtract) {
        this.reference = reference;
        this.status = status;
        this.amount = subtract;
    }

    @Override
    public TransactionModel toModel() {
        return null;
    }
}
