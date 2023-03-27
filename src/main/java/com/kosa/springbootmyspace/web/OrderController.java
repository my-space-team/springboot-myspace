package com.kosa.springbootmyspace.web;

import com.kosa.springbootmyspace.domain.Order;
import com.kosa.springbootmyspace.service.CartProductService;
import com.kosa.springbootmyspace.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartProductService cartProductService;

    @PostMapping("/save")
    public ResponseEntity<Order> save(@RequestBody Order order) {
        try {
            Order saveOrder = orderService.save(order);
            if (saveOrder != null) {
                return new ResponseEntity<Order>(saveOrder, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<Integer> delete(@PathVariable int idx) {
        try {
            int result = orderService.delete(idx);
            if (result == 1) {
                return new ResponseEntity<Integer>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<Order> findById(@PathVariable int idx) {
        try {
            Order findOrder = orderService.findByID(idx);
            if (findOrder != null) {
                return new ResponseEntity<Order>(findOrder, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Order>> findAll() {
        try {
            List<Order> orderList = orderService.findAll();
            if (orderList != null) {
                return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
    }

    /**
     * member의 idx로 주문 목록을 검색하는 엔드 포인트 입니다.
     * 
     * @param idx
     * @return
     */
    @GetMapping("/member/{idx}")
    public ResponseEntity<List<Order>> findAllByMemberIdx(@PathVariable int idx) {
        try {
            List<Order> orderList = orderService.findAllByMemberIdx(idx);
            if (orderList != null) {
                return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/recently/{idx}")
    public ResponseEntity<Order> findByMemberOrderByIdxAsc(@PathVariable int idx) {
        try {
            Order findOrder = orderService.findByMemberOrderByIdxAsc(idx);
            if (findOrder != null) {
                return new ResponseEntity<Order>(findOrder, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/memberOrder/{idx}")
    public ResponseEntity<List<Order>> findMemberOrderList(@PathVariable int idx) {
        try {
            List<Order> findOrderList = orderService.findAllByMemberIdx(idx);
            findOrderList.forEach(
                    order -> cartProductService.findByCart(order.getCart().getIdx())
                            .forEach(cartProduct -> order.getCartProductList().add(cartProduct)));
            if (findOrderList != null) {
                return new ResponseEntity<>(findOrderList, HttpStatus.OK);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
    }

}
