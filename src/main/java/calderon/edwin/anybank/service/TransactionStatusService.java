package calderon.edwin.anybank.service;

import calderon.edwin.anybank.bussiness.transaction.status.TransactionRuleFactoryService;
import calderon.edwin.anybank.dto.TransactionStatusReqDto;
import calderon.edwin.anybank.dto.TransactionStatusResDto;
import calderon.edwin.anybank.enums.ChannelEnum;
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
        if(opt.isEmpty()){
            return new TransactionStatusResDto(
                    transactionStatusReqDto.getReference(),
                    TransactionStatusEnum.INVALID
            );
        }
        TransactionModel transactionModel = opt.get();

        String transactionStatusRule = this.getRuleTypeByDateAndChannel(transactionModel, transactionStatusReqDto.getChannel());

        return TransactionRuleFactoryService.getService(transactionStatusRule).checkTransactionStatus(transactionModel);
    }

    private String getRuleTypeByDateAndChannel(TransactionModel transactionModel, ChannelEnum channel){
        int dateCompare = transactionModel.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate().compareTo(LocalDate.now());

        String transactionStatusRule;

        if(dateCompare < 0) transactionStatusRule = String.format(TransactionRuleFactoryService.ruleFormat, "before", channel.toString());
        else if(dateCompare == 0) transactionStatusRule = String.format(TransactionRuleFactoryService.ruleFormat, "current", channel.toString());
        else transactionStatusRule = String.format(TransactionRuleFactoryService.ruleFormat, "future", channel.toString());

        return transactionStatusRule;
    }
}
