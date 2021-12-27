package calderon.edwin.anybank.dto;

import calderon.edwin.anybank.enums.ChannelEnum;
import calderon.edwin.anybank.model.TransactionModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Data
public class TransactionStatusReqDto implements IDto<TransactionModel> {
    private UUID reference;
    private ChannelEnum channel = ChannelEnum.CLIENT;

    @Override
    public TransactionModel toModel() {
        return null;
    }
}
