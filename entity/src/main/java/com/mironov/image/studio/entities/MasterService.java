package com.mironov.image.studio.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "service")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MasterService extends AEntity<Long> implements Serializable {

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "masterService", fetch = FetchType.LAZY)
    private List<Order> order;

}
