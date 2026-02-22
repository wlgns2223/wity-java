package im.wity.filter;

import im.wity.components.JwtProvider;
import im.wity.service.UserService;
import im.wity.utils.CookieTokenExtractor;
import im.wity.vo.AuthenticatedUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter  {

    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        CookieTokenExtractor.extract(request)
                .map(jwtProvider::parseToken)
                .flatMap(c -> userService.findByEmail(c.getSubject()))
                .map(AuthenticatedUser::new)
                .ifPresent(user -> SecurityContextHolder.getContext().setAuthentication(user));

        filterChain.doFilter(request,response);

    }
}
