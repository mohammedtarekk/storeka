package me.security.Services;

import lombok.RequiredArgsConstructor;
import me.security.Entities.User;
import me.security.Repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = Logger.getLogger(UserDetailsServiceImpl.class.getName());
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Fetching user data...");

        List<User> user = userRepo.findUserWithRolesByUsername(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException("User: "+ username +" not found!");

        return user.get(0);
    }

}
