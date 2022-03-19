package com.softtech.softtechspringboot.Security.Service;

import com.softtech.softtechspringboot.Dto.UserSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Entity.User;
import com.softtech.softtechspringboot.Security.Dto.LoginRequestDto;
import com.softtech.softtechspringboot.Security.Enum.EnumJwtConstant;
import com.softtech.softtechspringboot.Security.Jwt.JwtUtils;
import com.softtech.softtechspringboot.Service.EntityService.UserEntityService;
import com.softtech.softtechspringboot.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final UserEntityService userEntityService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UserSaveAndUpdateRequestDto register(UserSaveAndUpdateRequestDto userSaveAndUpdateRequestDto) {

        UserSaveAndUpdateRequestDto requestDto = userService.save(userSaveAndUpdateRequestDto);

        return requestDto;
    }

    public String login(LoginRequestDto loginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);

        String bearer = EnumJwtConstant.BEARER.getConstant();

        return bearer + token;
    }

    public User getCurrentUser() {

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        User user = null;
        if (jwtUserDetails != null){
            user = userEntityService.getByIdWithControl(jwtUserDetails.getId());
        }
        return user;
    }

    public Long getCurrentUserId(){

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        Long jwtUserDetailsId = null;
        if (jwtUserDetails != null){
            jwtUserDetailsId = jwtUserDetails.getId();
        }
        return jwtUserDetailsId;
    }

    private JwtUserDetails getCurrentJwtUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }

}
