package pri.roggu.ticketing.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import pri.roggu.ticketing.domain.dto.UserDto;
import pri.roggu.ticketing.util.PasswordUtil;

import java.util.Collection;

@Entity
@Table(name = "tUser")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.userPwd;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Builder
    public User(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.userPwd = PasswordUtil.encrypt(userDto.getUserPwd());
        this.userName = userDto.getUserName();
        this.hp = userDto.getHp();
    }


}