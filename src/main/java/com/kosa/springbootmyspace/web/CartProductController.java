package com.kosa.springbootmyspace.web;

import com.kosa.springbootmyspace.domain.CartProduct;
import com.kosa.springbootmyspace.service.CartProductService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/cartProduct")
public class CartProductController {

    @Autowired
    private CartProductService cartProductService;

    @PostMapping("/save") // 상품 담기
    public ResponseEntity<CartProduct> save(@RequestBody CartProduct cartProduct) {
        try {
            CartProduct addedProduct = cartProductService.save(cartProduct);
            if (addedProduct != null) {
                return new ResponseEntity<CartProduct>(addedProduct, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<CartProduct>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{idx}") // 상품 삭제
    public ResponseEntity<Integer> delete(@PathVariable int idx) {
        try {
            int result = cartProductService.delete(idx);
            if (result == 1) {
                return new ResponseEntity<Integer>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update") // 수량 변경
    public ResponseEntity<CartProduct> update(@RequestBody CartProduct cartProduct) {
        try {
            int result = cartProductService.update(cartProduct);
            if (result == 1) {
                CartProduct updateProduct = cartProductService.findById(cartProduct.getIdx());
                return new ResponseEntity<CartProduct>(updateProduct, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<CartProduct>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<CartProduct> findById(@PathVariable int idx) {
        try {
            CartProduct findCartProduct = cartProductService.findById(idx);
            if (findCartProduct != null) {
                return new ResponseEntity<CartProduct>(findCartProduct, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<CartProduct>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cart/{idx}")
    public ResponseEntity<List<CartProduct>> findByCart(@PathVariable int idx) {
        try {
            List<CartProduct> cartProductList = cartProductService.findByCart(idx);
            if (cartProductList != null) {
                return new ResponseEntity<List<CartProduct>>(cartProductList, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<List<CartProduct>>(HttpStatus.NO_CONTENT);
    }
}
