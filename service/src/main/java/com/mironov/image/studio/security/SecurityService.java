package com.mironov.image.studio.security;

import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.api.dto.CurrentUserDto;
import com.mironov.image.studio.api.mappers.CurrentUserMapper;
import com.mironov.image.studio.api.mappers.UserCreateMapper;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.entities.User;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.util.Currency;
import java.util.Locale;

@Service
public class SecurityService implements ISecurityService {

    private final AuthenticationManager authenticationManager;
    private final IUserDao userDao;
    private final UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    public SecurityService(AuthenticationManager authenticationManager, IUserDao userDao, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.userDetailsService = userDetailsService;
    }

    @Override
    @Transactional
    public CurrentUserDto findLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            User user = this.userDao.getByName(((UserDetails) principal).getUsername());
            user.setLastActivity(OffsetDateTime.now());
            this.userDao.update(user);
            return CurrentUserMapper.mapDto(user);
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.info(String.format("Auto login %s successfully!", username));
        }
    }
}