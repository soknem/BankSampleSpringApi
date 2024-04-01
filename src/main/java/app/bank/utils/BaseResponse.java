package app.bank.utils;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Accessors(chain = true)
@Data
public class BaseResponse <T>{
    private T payLoad;
    private String message;
    private Object metaData;
    private int status;

    public static <T> BaseResponse<T> createSuccess(){
        return new BaseResponse<T>().setStatus(HttpStatus.CREATED.value()).setMessage("CREATE SUCCESSFULLY ");
    }
}
