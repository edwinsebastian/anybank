package calderon.edwin.anybank.service;

import calderon.edwin.anybank.dto.TransactionStatusReqDto;
import calderon.edwin.anybank.dto.TransactionStatusResDto;
import calderon.edwin.anybank.enums.TransactionStatusEnum;
import calderon.edwin.anybank.model.TransactionModel;
import calderon.edwin.anybank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionStatusService implements ITransactionStatusService<TransactionStatusReqDto, TransactionStatusResDto> {
    private final TransactionRepository transactionRepository;

    @Override
    public TransactionStatusResDto status(TransactionStatusReqDto transactionStatusReqDto) {
        Optional<TransactionModel> opt = transactionRepository.findById(transactionStatusReqDto.getReference());
        TransactionStatusResDto transactionStatusResDto;
        if(opt.isEmpty()){
            transactionStatusResDto = new TransactionStatusResDto(
                    transactionStatusReqDto.getReference(),
                    TransactionStatusEnum.INVALID
            );
        }
        TransactionModel transactionModel = transactionRepository.findById(transactionStatusReqDto.getReference()).get();

        switch (transactionStatusReqDto.getChannel()){
            case ATM:
                // TODO: atm
                break;
            case CLIENT:
                // TODO: client
                break;
            case INTERNAL:
                // TODO: internal
                break;
        }

        return transactionStatusResDto;
    }
}
