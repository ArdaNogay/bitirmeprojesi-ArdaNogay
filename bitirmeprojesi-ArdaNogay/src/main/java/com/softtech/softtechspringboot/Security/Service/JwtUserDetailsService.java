package com.softtech.softtechspringboot.Security.Service;

import com.softtech.softtechspringboot.Entity.User;
import com.softtech.softtechspringboot.Service.EntityService.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userByUserName = userEntityService.getUserByUserName(username);

        return JwtUserDetails.create(userByUserName);
    }

    public UserDetails loadUserByUserId(Long id) {

        User user = userEntityService.getByIdWithControl(id);

        return JwtUserDetails.create(user);
    }
}
