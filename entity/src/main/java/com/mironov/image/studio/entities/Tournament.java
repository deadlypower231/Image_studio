package com.mironov.image.studio.entities;

import com.mironov.image.studio.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tournament")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Tournament extends AEntity<Long> implements Serializable {
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "date", length = 21)
    private Date date;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private Status status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id", referencedColumnName = "id")
    private Description description;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tournament_user", joinColumns = @JoinColumn(name = "tournament_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users;
    @OneToMany(mappedBy = "tournament", fetch = FetchType.LAZY)
    private List<Order> order;
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedule;
}
