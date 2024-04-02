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
    public static <T> BaseResponse<T> ok(){
        return new BaseResponse<T>()
                       .setStatus(HttpStatus.OK.value())
                       .setMessage("Succeffully");
    }
    public static <T>BaseResponse<T>notFound(){
        return new BaseResponse<T>().setStatus(HttpStatus.NOT_FOUND.value())
                       .setMessage("Items couldn't found");
    }
    public static <T>BaseResponse<T>badReqeust(){
        return new BaseResponse<T>().setStatus(HttpStatus.BAD_REQUEST.value())
                       .setMessage("Items couldn't found");
    }
}
