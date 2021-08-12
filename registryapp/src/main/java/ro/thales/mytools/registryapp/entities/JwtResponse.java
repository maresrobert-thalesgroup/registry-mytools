package ro.thales.mytools.registryapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponse {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
}
