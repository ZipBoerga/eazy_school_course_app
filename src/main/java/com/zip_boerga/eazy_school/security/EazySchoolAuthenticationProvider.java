package com.zip_boerga.eazy_school.security;

import com.zip_boerga.eazy_school.model.Role;
import com.zip_boerga.eazy_school.model.User;
import com.zip_boerga.eazy_school.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EazySchoolAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;

    @Autowired
    public EazySchoolAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.findByEmail(email);

        if (user != null && user.getUserId() > 0 && password.equals(user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user.getName(), null,
                    getGrantedAuthorities(user.getRole()));
        } else {
            throw new BadCredentialsException("Invalid credentials!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<GrantedAuthority> getGrantedAuthorities(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        return authorities;
    }
}
