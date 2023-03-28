package com.kosa.springbootmyspace.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "SCORE_SEQ_GEN", sequenceName = "SCORE_SEQ", allocationSize = 1)
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCORE_SEQ_GEN")
    private int idx;

    @Column(nullable = false)
    private int durability;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int design;

    @Column(nullable = false)
    private int delivery;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_idx")
    @JsonIgnore
    private Review review;


    @Column
    private float total;

}
