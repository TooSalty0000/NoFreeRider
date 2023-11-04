package com.teamnine.noFreeRider.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Member implements UserDetails {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "member_id", updatable = false)
    private UUID member_id;

    @Column(name = "member_name", nullable = false, updatable = false, unique = true)
    private String member_name;

    @Column(name = "member_email", nullable = false, updatable = true, unique = true)
    private String member_email;

    @Column(name = "member_password", nullable = false)
    private String member_password;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "member_temperature")
    private short member_temperature;

    public Member(String memberName, String member_email, String member_password) {
        this.member_name = memberName;
        this.member_email = member_email;
        this.member_password = member_password;
        this.member_temperature = 36;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return member_name;
    }

    @Override
    public String getPassword() {
        return member_password;
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
