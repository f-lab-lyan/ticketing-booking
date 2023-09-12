package pri.roggu.ticketing.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pri.roggu.ticketing.exception.exceptions.UserDuplicateException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * FUNCTION :: DTO 파라미터 @Valid Exception Handler
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        StringBuffer sb = new StringBuffer();

        result.getFieldErrors()
                .forEach(
                        fieldError -> {
                            sb.append("\"")
                                    .append(fieldError.getField())
                                    .append("\"")
                                    .append(" : ")
                                    .append(fieldError.getDefaultMessage());
                        }
                );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(sb.toString());
    }

    /**
     * FUNCTION :: 사용자 중복
     * @param e
     * @return
     */
    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<String> UserDuplicateException(UserDuplicateException e) {
        log.info("User ["+ e.getUserId() +"] Duplicated Id");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}