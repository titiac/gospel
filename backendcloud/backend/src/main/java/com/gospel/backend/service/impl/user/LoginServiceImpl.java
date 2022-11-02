package com.gospel.backend.service.impl.user;

import com.gospel.backend.pojo.User;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import com.gospel.backend.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> getToken(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(username,password);

        Authentication authenticate=authenticationManager.authenticate(authenticationToken);

        UserDetailsImpl loginUser=(UserDetailsImpl) authenticate.getPrincipal();
        User user=loginUser.getUser();


        System.out.println(passwordEncoder.encode("123456"));
        return null;
    }
}
