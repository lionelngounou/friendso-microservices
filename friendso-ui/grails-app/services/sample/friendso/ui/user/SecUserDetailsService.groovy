package sample.friendso.ui.user

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class SecUserDetailsService implements UserDetailsService {

    private UserServiceClient userRestClient = new UserServiceClient()

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        def user = new UserReaderHystrixCommand("findByEmail", {
            userRestClient.findByEmail(username)
        }).execute().json 

        if (!user) {
            log.warn "User not found: $username"
            throw new UsernameNotFoundException('User not found')
        }

        def userDetails = toUserDetails(user)        
        ['id', 'email', 'firstname', 'lastname', 'aliasName'].each{
            userDetails.metaClass."$it" = user[it]
        }
        userDetails
    }

    protected UserDetails toUserDetails(user) {
        new UserDetails() {

            @Override
            Collection<? extends GrantedAuthority> getAuthorities(){null}

            @Override
            String  getPassword(){user.password}

            @Override
            String  getUsername(){user.email}

            @Override
            boolean isAccountNonExpired(){isEnabled()}

            @Override
            boolean isAccountNonLocked(){isEnabled()}

            @Override
            boolean isCredentialsNonExpired(){isEnabled()}

            @Override
            boolean isEnabled(){user.active}
        }
    }
}

class SecUserDetails {

    String id, email, aliasName, firstname, lastname
    boolean active
}