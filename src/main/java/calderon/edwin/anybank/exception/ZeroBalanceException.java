package calderon.edwin.anybank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ZeroBalanceException extends RuntimeException{
    public ZeroBalanceException(String message){
        super(message);
    }
}
