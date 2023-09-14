package pri.roggu.ticketing.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CommonUtil {

    /**
     * FUNCTION :: Client Ip
     * @return
     */
    public static String getClientIP() {
        HttpServletRequest reqeust = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = reqeust.getHeader("X-FORWARDED-FOR");

        if(reqeust.getServerName().equals("localhost")) {
            ip = "127.0.0.1";
        }

        if (!StringUtils.hasText(ip)) {
            ip = reqeust.getRemoteAddr();
        }

        return ip;
    }
}