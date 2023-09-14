package pri.roggu.ticketing.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pri.roggu.ticketing.user.enums.LoginResult;

@Entity
@Table(name = "tLoginLog")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginLog extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginLogIdx;

    private String userId;
    private String loginIp;
    @Enumerated(EnumType.STRING)
    private LoginResult loginResult;

    @Builder
    public LoginLog(String userId, String loginIp, LoginResult loginResult) {
        this.userId = userId;
        this.loginIp = loginIp;
        this.loginResult = loginResult;
    }
}