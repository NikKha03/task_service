package NikKha03.TaskService.component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Component
public class OriginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(OriginFilter.class);

    @Value("#{'${app-env.allowed-origins}'.split(',')}")
    private List<String> ALLOWED_ORIGIN;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String origin = httpRequest.getScheme() + "://" + httpRequest.getHeader("Host");

        if (!ALLOWED_ORIGIN.contains(origin)) {
            logger.warn("Нераспознанный origin: " + origin);
        }
        chain.doFilter(request, response); // Пропускаем запрос
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
