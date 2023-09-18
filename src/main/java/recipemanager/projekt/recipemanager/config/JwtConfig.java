package recipemanager.projekt.recipemanager.config;



import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recipemanager.projekt.recipemanager.client.UserFeignClient;

@Configuration
@RequiredArgsConstructor
public class JwtConfig {


    private final UserFeignClient userFeignClient;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {

        return new JwtAuthenticationFilter(userFeignClient);
    }
}