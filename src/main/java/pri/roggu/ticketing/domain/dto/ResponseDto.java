package pri.roggu.ticketing.domain.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseDto<T> {

    private final HttpStatus httpStatus;
    private final T data;
}