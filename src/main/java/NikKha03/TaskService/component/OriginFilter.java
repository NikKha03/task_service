package NikKha03.TaskService.component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OriginFilter implements Filter {

    private static final String ALLOWED_FRONTEND_ORIGIN = "https://tracker.sharpbubbles.ru"; // Адрес frontend

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String origin = httpRequest.getHeader("Origin");

        // Проверка заголовка Origin
        if (origin != null && origin.equals(ALLOWED_FRONTEND_ORIGIN)) {
            chain.doFilter(request, response); // Если запрос с правильным Origin, пропускаем
        } else {
            response.getWriter().write("Forbidden: Invalid Origin");
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        // На время разработки/тестирования
//        chain.doFilter(request, response); // Если запрос с правильным Origin, пропускаем
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
