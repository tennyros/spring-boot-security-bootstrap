package com.github.tennyros.bootstrap.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "roles_bootstrap")
@NoArgsConstructor
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, updatable = false, unique = true)
    private Long id;

    @NotBlank(message = "Role must be defined!")
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }

    @Override
    public String toString() {
        return roleName;
    }
}
