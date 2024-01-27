package com.handicraft.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.handicraft.domain.core.BaseCreatedUpdated;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CustomUser extends BaseCreatedUpdated {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "email", nullable = false, unique = true, length = 50)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "greeting", nullable = false, length = 100)
    private String greeting;

    @JsonIgnore
    @Column(name = "isActivated")
    private boolean isActivated;

    @ManyToMany
    @JoinTable(
            name = "userAuthority",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "authorityName", referencedColumnName ="authorityName")}
    )

    private Set<Authority> authorities;

}
