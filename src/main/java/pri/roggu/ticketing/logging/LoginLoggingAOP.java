package pri.roggu.ticketing.logging;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import pri.roggu.ticketing.domain.dto.JwtTokenDto;
import pri.roggu.ticketing.domain.dto.ResponseDto;
import pri.roggu.ticketing.domain.entity.LoginLog;
import pri.roggu.ticketing.exception.exceptions.UserLoginException;
import pri.roggu.ticketing.user.enums.LoginResult;
import pri.roggu.ticketing.user.repository.LoginLogRepository;

import static pri.roggu.ticketing.util.CommonUtil.getClientIP;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginLoggingAOP {

    private final LoginLogRepository loginLogRepository;

    @AfterReturning(value = "execution(* pri.roggu.ticketing.user.controller.UserController.signin(..))", returning = "returnData")
    private void loginSuccess(ResponseDto<JwtTokenDto> returnData) {
        saveLoginLog(returnData.getData().getUserId(), getClientIP(), LoginResult.SUCCESS);
    }

    @AfterThrowing(value = "execution(* pri.roggu.ticketing.user.controller.UserController.signin(..))", throwing = "exception")
    private void loginFail(UserLoginException exception) {
        saveLoginLog(exception.getUserId(), getClientIP(), LoginResult.FAIL);
    }

    /**
     * FUNCTION :: 로그인 로그 저장
     * @param userId
     * @param loginIp
     * @param loginResult
     */
    private void saveLoginLog(String userId, String loginIp, LoginResult loginResult) {
        loginLogRepository.save(LoginLog.builder()
                                              .userId(userId)
                                              .loginIp(loginIp)
                                              .loginResult(loginResult)
                                              .build());
    }

}
