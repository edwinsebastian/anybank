package calderon.edwin.anybank.service;

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
        ChannelEnum channel = transactionStatusReqDto.getChannel();

        if (dateCompare == 0) {
            switch (channel){
                case ATM:
                case CLIENT:
                    transactionStatusResDto = getPartialTransactionResDto(transactionModel, TransactionStatusEnum.PENDING);
                    break;
                case INTERNAL:
                    transactionStatusResDto = getFullTransactionResDto(transactionModel, TransactionStatusEnum.PENDING);
                    break;
            }
        } else if (dateCompare > 0) {
            switch (channel){
                case ATM:
                    transactionStatusResDto = getPartialTransactionResDto(transactionModel, TransactionStatusEnum.PENDING);
                    break;
                case CLIENT:
                    transactionStatusResDto = getPartialTransactionResDto(transactionModel, TransactionStatusEnum.FUTURE);
                    break;
                case INTERNAL:
                    transactionStatusResDto = getFullTransactionResDto(transactionModel, TransactionStatusEnum.FUTURE);
                    break;
            }
        } else if (dateCompare < 0) {
            switch (channel){
                case ATM:
                case CLIENT:
                    transactionStatusResDto = getPartialTransactionResDto(transactionModel, TransactionStatusEnum.SETTLED);
                    break;
                case INTERNAL:
                    transactionStatusResDto = getFullTransactionResDto(transactionModel, TransactionStatusEnum.SETTLED);
                    break;
            }
        }

        return transactionStatusResDto;
    }

    private TransactionStatusResDto getFullTransactionResDto(TransactionModel transactionModel, TransactionStatusEnum status){
        return new TransactionStatusResDto(
                transactionModel.getId(),
                status,
                transactionModel.getAmount(),
                transactionModel.getFee()
        );
    }

    private TransactionStatusResDto getPartialTransactionResDto(TransactionModel transactionModel, TransactionStatusEnum status){
        return new TransactionStatusResDto(
                transactionModel.getId(),
                status,
                transactionModel.getAmount().subtract(transactionModel.getFee().abs())
        );
    }
}
