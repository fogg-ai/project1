//package org.itstep.service.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.itstep.service.impl.StudentServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    final CustomDetailsService customDetailsService;
//    final PasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    public CustomAuthenticationProvider(CustomDetailsService customDetailsService) {
//        this.customDetailsService = customDetailsService;
//        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getPrincipal().toString();
//        String password = authentication.getCredentials().toString();
//        UserDetails userDetails = null;
//        try {
//            userDetails = customDetailsService.loadUserByUsername(username);
//            if(!bCryptPasswordEncoder.matches(password,userDetails.getPassword())){
//                throw new BadCredentialsException("User not found");
//            }
//        } catch (Exception e) {
//            log.error("User not found.");
//            throw new BadCredentialsException("User not found", e);
//        }
//        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),userDetails.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return UsernamePasswordAuthenticationToken.class.equals(aClass);
//    }
//}
