package com.mironov.image.studio.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "description")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Description extends AEntity<Long> implements Serializable {

    @Column(name = "short_description", length = 100)
    private String shortDescription;
    @Column(name = "full_description", length = 1000)
    private String fullDescription;
    @OneToOne(mappedBy = "description", fetch = FetchType.LAZY)
    private User user;
    @OneToOne(mappedBy = "description", fetch = FetchType.LAZY)
    private Tournament tournament;

}
