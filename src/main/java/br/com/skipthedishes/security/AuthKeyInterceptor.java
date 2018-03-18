package br.com.skipthedishes.security;

import br.com.skipthedishes.entity.CustomerEntity;
import br.com.skipthedishes.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

public class AuthKeyInterceptor extends HandlerInterceptorAdapter {

    private static final String PARAM_NAME_TOKEN = "Authorization";

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {

        if (!checkHandleType(request, response, handler)) {
            return false;
        }

        if (!checkResourceNeedsAuth(handler)) {
            return true;
        }

        String paramValue = request.getHeader(PARAM_NAME_TOKEN);

        if (paramValue != null && !paramValue.isEmpty()) {
            String base64key = paramValue.replace("Basic", "").trim();

            byte[] decodedKey = Base64.getDecoder().decode(base64key);

            String[] emailAndPass = new String(decodedKey).split(":");

            CustomerEntity customerEntity = customerRepository.findByEmailAndPassword(emailAndPass[0], emailAndPass[1]);
            if (customerEntity != null) {
                AuthKeyThreadLocal.setAuthKey(customerEntity.getId());

                return true;
            }
        }

        return false;
    }

    private boolean checkHandleType(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    private boolean checkResourceNeedsAuth(final Object handler) {
        final HandlerMethod method = (HandlerMethod) handler;

        return method.getMethod().isAnnotationPresent(AuthRequired.class)
                || method.getMethod().getDeclaringClass().isAnnotationPresent(AuthRequired.class);
    }
}