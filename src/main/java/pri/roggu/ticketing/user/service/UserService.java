package pri.roggu.ticketing.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pri.roggu.ticketing.domain.dto.ResponseDto;
import pri.roggu.ticketing.domain.dto.UserDto;
import pri.roggu.ticketing.domain.entity.User;
import pri.roggu.ticketing.exception.exceptions.UserDuplicateException;
import pri.roggu.ticketing.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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

}