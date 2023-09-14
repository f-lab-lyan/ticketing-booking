package pri.roggu.ticketing.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtTokenDto {

    private String userId;
    private String accessToken;
    private String refreshToken;
    private long refreshTokenExpirationTime;

    @Builder
    public JwtTokenDto(String userId, String accessToken, String refreshToken, long refreshTokenExpirationTime) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }
}
