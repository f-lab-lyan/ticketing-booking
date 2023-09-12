package pri.roggu.ticketing.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pri.roggu.ticketing.domain.dto.ResponseDto;
import pri.roggu.ticketing.domain.dto.UserDto;
import pri.roggu.ticketing.domain.entity.User;
import pri.roggu.ticketing.exception.exceptions.UserDuplicateException;
import pri.roggu.ticketing.user.repository.UserRepository;
import pri.roggu.ticketing.util.PasswordUtil;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User " + userId + " Not Exist."));
    }

    public ResponseDto<String> signin(final UserDto userDto) {

        User user = userRepository.findByUserId(userDto.getUserId())
                .orElseThrow(NoSuchElementException::new);

        if(!PasswordUtil.matchs(userDto.getUserPwd(), user.getUserPwd())) {
            // throw new LoginException();
        }
        return null;
    }

}