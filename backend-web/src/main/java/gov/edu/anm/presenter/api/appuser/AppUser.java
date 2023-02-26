package gov.edu.anm.presenter.api.appuser;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.edu.anm.presenter.api.appuser.dtos.AppUserInputDto;
import gov.edu.anm.presenter.api.avaliation.Avaliation;
import gov.edu.anm.presenter.api.participation.Participation;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "APP_USERS")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private AppRole role;

    @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Participation> participations = new HashSet<>();

    @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Avaliation> avaliations = new HashSet<>();

    /* UserDetails username/password gettters and setters
        will be set up by lombok because they have same
        name in AppUser. */

    public AppUser(AppUserInputDto appUserInputDto) {
        this.username = appUserInputDto.getUsername();
        this.password = appUserInputDto.getPassword();
        this.role = appUserInputDto.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
