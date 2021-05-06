package com.mironov.image.studio.entities;

import com.mironov.image.studio.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "schedule")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Schedule extends AEntity<Long> implements Serializable {

    @Column(name = "time")
    private String time;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    private Tournament tournament;
    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    private List<Order> order;
    @Column(name = "status")
    private Status status;
}
