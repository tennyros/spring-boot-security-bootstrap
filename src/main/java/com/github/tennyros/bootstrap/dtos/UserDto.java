package com.github.tennyros.bootstrap.dtos;

import com.github.tennyros.bootstrap.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private Long id;

    @NotBlank(message = "Username must be defined!")
    @Size(min = 3, max = 18, message = "Username must be from 3 to 18 symbols long!")
    private String firstName;

    @NotBlank(message = "Username must be defined!")
    @Size(min = 3, max = 18, message = "Username must be from 3 to 18 symbols long!")
    private String lastName;

    @NotBlank(message = "Password must be defined!")
    @Size(min = 6, max = 18, message = "Password must be from 6 to 18 symbols long!")
    private String password;

    @Transient
    @NotBlank(message = "Please, confirm your password!")
    private String passwordConfirm;

    @NotBlank(message = "Email must be defined!")
    @Email(message = "Email must be valid!")
    private String email;

    @NotNull(message = "Age must be defined!")
    @Min(value = 14, message = "Age must not be less than 14!")
    @Max(value = 125, message = "Age must not be more than 125!")
    private Integer age;

    @NotEmpty(message = "Role must be selected!")
    private Set<Role> roles;

    @Transient
    private boolean admin;

    public boolean getIsAdmin() {
        return admin;
    }
}
