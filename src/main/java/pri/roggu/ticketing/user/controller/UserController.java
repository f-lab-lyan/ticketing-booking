package pri.roggu.ticketing.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pri.roggu.ticketing.domain.dto.ResponseDto;
import pri.roggu.ticketing.domain.dto.UserDto;
import pri.roggu.ticketing.user.service.UserService;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * FUNCTION :: 회원가입
     * @param userDto
     * @return
     */
    @PostMapping(value = "/signup")
    public ResponseDto<String> signup(@RequestBody @Valid final UserDto userDto) {
        return userService.signup(userDto);
    }

}