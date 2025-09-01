package recipemanager.projekt.recipemanager.config.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {


    public static final String SECRET = "ZtxVxRL9HkoEcoLHu1oCkRoiSihFuLtKV++l1HYVHZXjJudkW7O6AxTwykZa90BnniCqK5lK1HDkJbNkwt6pS0aEvfYkTQWUdNz5tt/ZtkIG16EkScXqwXl4uO0Jbmooyev9zKybhwePWgwwOTnARUVzCE8SQTU2rVLqN587WZsyOcwWFshy2UsP7TZm4a6IMjzFGroY4lY82giK662655+x0z4rYlX7fQqFySwGFLMRiMB+Ntsoy1pEiQggJ3xvdj90A5pSn4jDJ5ZhJRyTH1BjrNIudai2JHOsey/ayE9MAlkqaTnCf8bLIuI0NOOPYKfYzisdw2gJ2PLNxoQ9bJWaLUmy6kqr4k1uxAy4GL8=";


    public void validateToken(final String token) {
        Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token);
    }



    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception e) {
            throw new RuntimeException("Invalid token: " + e.getMessage());
        }
    }

    public String extractRoleFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String role = (String) claims.get("role");
            return role;
        } catch (Exception e) {
            throw new RuntimeException("Invalid token: " + e.getMessage());
        }
    }
}