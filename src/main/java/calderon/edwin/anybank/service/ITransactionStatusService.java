package calderon.edwin.anybank.service;

public interface ITransactionStatusService<ReqDto, ResDto> {
    ResDto status(ReqDto reqDto);
}
