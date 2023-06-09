package com.kosa.springbootmyspace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosa.springbootmyspace.domain.Member;
import com.kosa.springbootmyspace.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByMemberOrderByIdxDesc(Member member);

    List<Order> findByMemberOrderByIdxAsc(Member member);
}
