package calderon.edwin.anybank.dto;

import calderon.edwin.anybank.enums.ChannelEnum;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Data
public class TransactionStatusReqDto {
    private UUID reference;
    private ChannelEnum channel = ChannelEnum.CLIENT;
}
