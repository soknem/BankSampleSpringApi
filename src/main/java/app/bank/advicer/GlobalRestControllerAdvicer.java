package app.bank.advicer;

import app.bank.utils.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalRestControllerAdvicer {

    @ExceptionHandler(NoSuchElementException.class)
    public BaseResponse<?> handleNoSuchElementException(NoSuchElementException ex){
        return BaseResponse.notFound().setMetaData(ex.getMessage());
    }
}

