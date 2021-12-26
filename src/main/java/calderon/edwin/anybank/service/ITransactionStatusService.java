package calderon.edwin.anybank.service;

interface ITransactionStatusService<ReqDto, ResDto> {
    ResDto status(ReqDto reqDto);
}
