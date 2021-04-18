package com.mironov.image.studio.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.Constraint;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends AEntity<Long> implements Serializable {

    @Column(name = "username", unique = true, length = 50, nullable = false)
    private String username;
    @Column(name = "firstName", length = 50)
    private String firstName;
    @Column(name = "lastName", length = 50)
    private String lastName;
    @Column(name = "phone", unique = true, length = 11, nullable = false)
    private long phone;
    @Column(name = "email", unique = true, length = 50, nullable = false)
    private String email;

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
}
