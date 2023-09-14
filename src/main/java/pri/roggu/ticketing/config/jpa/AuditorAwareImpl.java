package pri.roggu.ticketing.config.jpa;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(ObjectUtils.isEmpty(authentication) || !authentication.isAuthenticated()){
            return Optional.empty();
        }

        //return Optional.of(((UserDetails) authentication.getPrincipal()).getUsername());
        return Optional.of("관리자");
    }
}
