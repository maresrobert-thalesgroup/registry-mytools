package ro.thales.mytools.registryapp.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@Data
public class JwtResponse {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    private final Collection<? extends GrantedAuthority> role;
}
