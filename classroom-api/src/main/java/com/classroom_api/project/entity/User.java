package com.classroom_api.project.entity;

import com.classroom_api.project.model.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String id;

    private String userName;

    private String email;

    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Classroom> classrooms;

    @Enumerated(EnumType.STRING)
    private Role role;

    User(String userName, String password, Role role, String email){
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    User(String id,String userName, String password, Role role, String email){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public void addClassroom(Classroom classroom){
        if (classrooms == null){
            classrooms = new ArrayList<>();
        }
        classrooms.add(classroom);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    public String getActualName(){
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
