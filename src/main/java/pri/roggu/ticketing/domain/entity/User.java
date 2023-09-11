package pri.roggu.ticketing.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pri.roggu.ticketing.domain.dto.UserDto;
import pri.roggu.ticketing.util.PasswordUtil;

@Entity
@Table(name = "tUser")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookingIdx")
    private Booking booking;

    private String userId;
    private String userPwd;
    private String userName;
    private String hp;

    @Builder
    public User(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.userPwd = PasswordUtil.encrypt(userDto.getUserPwd());
        this.userName = userDto.getUserName();
        this.hp = userDto.getHp();
    }


}