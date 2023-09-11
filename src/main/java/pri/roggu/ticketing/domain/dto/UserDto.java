package pri.roggu.ticketing.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {

    private Long userIdx;

    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPwd;
    @NotBlank(message = "사용자명을 입력해주세요.")
    private String userName;
    @Pattern(regexp = "^010-?([0-9]{4})-?([0-9]{4})$", message = "휴대폰 형식을 확인해주세요.")
    private String hp;
}