package pri.roggu.ticketing.exception.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserLoginException extends RuntimeException{

    private final String userId;
}