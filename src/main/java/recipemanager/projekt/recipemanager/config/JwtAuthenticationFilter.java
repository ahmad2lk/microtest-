package recipemanager.projekt.recipemanager.config;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import recipemanager.projekt.recipemanager.client.UserFeignClient;

import java.io.IOException;
import java.util.Collections;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final UserFeignClient userFeignClient;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("Authorization");

       TokenValidationResponse authResponse = userFeignClient.validateAccessToken(jwtToken);

        if (authResponse != null && authResponse.isValid()) {
            String role = authResponse.getRole();

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    authResponse, null, Collections.singleton(new SimpleGrantedAuthority(role))
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}