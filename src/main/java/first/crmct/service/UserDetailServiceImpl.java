package first.crmct.service;

import first.crmct.model.Role;
import first.crmct.model.User;
import first.crmct.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;


    private final PasswordEncoder encoder;

//    @PostConstruct
//    public void init() {
//        System.out.println("Password: " + encoder.encode("admin"));
//    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = usersRepository.findByName(username);
        return userOptional.map(user -> new org.springframework.security.core.userdetails.User(
                        user.getName(),
                        user.getPassword(),
                        createAuthorities(user.getRoles())
                )).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private List<? extends GrantedAuthority> createAuthorities(List<Role> roles) {
        return ofNullable(roles)
                .map(rolesList -> rolesList.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());
    }

}
