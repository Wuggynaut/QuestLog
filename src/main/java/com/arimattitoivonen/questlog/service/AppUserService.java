package com.arimattitoivonen.questlog.service;

import com.arimattitoivonen.questlog.domain.AppUser;
import com.arimattitoivonen.questlog.domain.AppUserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository repository;

    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
    }

    public AppUser getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curruser = repository.findByUsername(username);
        if (curruser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        UserDetails user = new User(curruser.getUsername(), curruser.getPasswordHash(),
                AuthorityUtils.createAuthorityList("ROLE_" + curruser.getRole().name()));
        return user;
    }
}
