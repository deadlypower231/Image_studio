package com.mironov.image.studio.security;

import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.api.dto.CurrentUserDto;
import com.mironov.image.studio.api.mappers.CurrentUserMapper;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;


@Service
public class SecurityService implements ISecurityService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
    private final AuthenticationManager authenticationManager;
    private final IUserDao userDao;
    private final UserDetailsService userDetailsService;

    public SecurityService(AuthenticationManager authenticationManager, IUserDao userDao, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.userDetailsService = userDetailsService;
    }

    @Override
    @Transactional
    public CurrentUserDto findLoggedInUser() {
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            User user = this.userDao.getByName(SecurityContextHolder.getContext().getAuthentication().getName());
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
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.info("Auto login {} successfully!", username);
        }
    }

    public void changeUsername(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        SecurityContextHolder.clearContext();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.info("Relogin {} successfully!", username);
        }
    }
}