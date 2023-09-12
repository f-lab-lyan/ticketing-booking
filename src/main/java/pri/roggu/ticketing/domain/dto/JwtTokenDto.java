package pri.roggu.ticketing.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtTokenDto {

    private String accountId;
    private String accessToken;
    private String refreshToken;
    private long refreshTokenExpirationTime;

    @Builder
    public JwtTokenDto(String accountId, String accessToken, String refreshToken, long refreshTokenExpirationTime) {
        this.accountId = accountId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }
}
