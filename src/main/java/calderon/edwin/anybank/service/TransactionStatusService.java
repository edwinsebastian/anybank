package calderon.edwin.anybank.service;

import calderon.edwin.anybank.bussiness.transaction.status.TransactionRuleFactoryService;
import calderon.edwin.anybank.dto.TransactionStatusReqDto;
import calderon.edwin.anybank.dto.TransactionStatusResDto;
import calderon.edwin.anybank.enums.TransactionStatusEnum;
import calderon.edwin.anybank.model.TransactionModel;
import calderon.edwin.anybank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionStatusService implements ITransactionStatusService<TransactionStatusReqDto, TransactionStatusResDto> {
    private final TransactionRepository transactionRepository;

    @Override
    public TransactionStatusResDto status(TransactionStatusReqDto transactionStatusReqDto) {
        Optional<TransactionModel> opt = transactionRepository.findById(transactionStatusReqDto.getReference());
        TransactionStatusResDto transactionStatusResDto = null;
        if(opt.isEmpty()){
            return new TransactionStatusResDto(
                    transactionStatusReqDto.getReference(),
                    TransactionStatusEnum.INVALID
            );
        }
        TransactionModel transactionModel = opt.get();
        int dateCompare = transactionModel.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate().compareTo(LocalDate.now());

        String transactionStatusRule = "%sDateAnd%sChannelRule";
        if(dateCompare < 0) transactionStatusRule = String.format(transactionStatusRule, "before", transactionStatusReqDto.getChannel().toString());
        else if(dateCompare == 0) transactionStatusRule = String.format(transactionStatusRule, "current", transactionStatusReqDto.getChannel().toString());
        else if(dateCompare > 0) transactionStatusRule = String.format(transactionStatusRule, "future", transactionStatusReqDto.getChannel().toString());

        return TransactionRuleFactoryService.getService(transactionStatusRule).checkTransactionStatus(transactionModel);
    }
}
