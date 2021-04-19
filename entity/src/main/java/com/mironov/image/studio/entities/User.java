package com.mironov.image.studio.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends AEntity<Long> implements Serializable {

    @Column(name = "username", unique = true, length = 15, nullable = false)
    @NotBlank(message = "Не должно быть пустым")
    private String username;
    @Column(name = "firstName", length = 15)
    private String firstName;
    @Column(name = "lastName", length = 15)
    private String lastName;
    @Column(name = "phone", unique = true, length = 11, nullable = false)
    private long phone;
    @Column(name = "email", unique = true, length = 100, nullable = false)
    private String email;
    @Column(name = "password")
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "description_id", referencedColumnName = "id")
    private Description description;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_order", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private List<Order> orders;
    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    private List<Order> order;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MasterService> masterServices;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_schedule", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id", referencedColumnName = "id"))
    private List<Schedule> schedules;


    public List<Role> getRoles() {
        if (roles == null) {
            return new ArrayList<>();
        }
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles.clear();
        this.roles.addAll(roles);
    }

}
