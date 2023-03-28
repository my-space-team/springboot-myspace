package com.kosa.springbootmyspace.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "REVIEW_SEQ_GEN", sequenceName = "REVIEW_SEQ", allocationSize = 1)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQ_GEN")
    private int idx;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String image;

    @OneToOne
    @JoinColumn(name = "member_idx")
    private Member member;

    @OneToOne
    @JoinColumn(name = "product_idx")
    private Product product;

    @OneToOne
    @JoinColumn(name = "order_idx")
    @JsonIgnore
    private Order order;

    @OneToOne(mappedBy = "review")
    private Score score;

}
