package pri.roggu.ticketing.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.roggu.ticketing.domain.dto.JwtTokenDto;
import pri.roggu.ticketing.domain.dto.ResponseDto;
import pri.roggu.ticketing.domain.dto.UserDto;
import pri.roggu.ticketing.domain.entity.User;
import pri.roggu.ticketing.exception.exceptions.UserDuplicateException;
import pri.roggu.ticketing.exception.exceptions.UserLoginException;
import pri.roggu.ticketing.user.component.JwtTokenProvider;
import pri.roggu.ticketing.user.enums.LoginResult;
import pri.roggu.ticketing.user.repository.UserRepository;
import pri.roggu.ticketing.util.PasswordUtil;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * FUNCTION :: 회원가입
     * @param userDto
     * @return
     */
    public ResponseDto<String> signup(final UserDto userDto) {
        userRepository.findByUserId(userDto.getUserId()).ifPresent(
                user -> { throw new UserDuplicateException(user.getUserId()); }
        );

        User user = User.builder()
                        .userDto(userDto)
                        .build();

        userRepository.save(user);

        return ResponseDto.<String>builder()
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * FUNCTION :: 로그인
     * @param userDto
     * @return
     */
    @Transactional(readOnly = true)
    public ResponseDto<JwtTokenDto> signin(final UserDto userDto) {

        User user = userRepository.findByUserId(userDto.getUserId())
                .orElseThrow(() -> new UserLoginException(userDto.getUserId()));

        if(!PasswordUtil.matchs(userDto.getUserPwd(), user.getUserPwd())) {
             throw new UserLoginException(userDto.getUserId());
        }

        JwtTokenDto jwtToken = jwtTokenProvider.generateToken(userDto.getUserId());

        return ResponseDto.<JwtTokenDto>builder()
                          .httpStatus(HttpStatus.OK)
                          .data(jwtToken)
                          .build();
    }
}