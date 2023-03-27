package com.kosa.springbootmyspace.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "ORDER2")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "ORDER2_SEQ_GEN", sequenceName = "ORDER2_SEQ", allocationSize = 1)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER2_SEQ_GEN")
    private int idx;

    @Column
    private String payment;

    @Column
    private int price;

    @Column
    private int delivery_price;

    @Column
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regdate;

    @Column
    private String delivery_name;

    @Column
    private String address_name;

    @Column
    private String address;

    @Column
    private String delivery_request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    @OneToOne
    @JoinColumn(name = "cart_idx")
    private Cart cart;

    @Transient
    private List<CartProduct> cartProductList = new ArrayList<CartProduct>();

}
