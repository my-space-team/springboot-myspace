package com.kosa.springbootmyspace.web;

import com.kosa.springbootmyspace.domain.Category;
import com.kosa.springbootmyspace.domain.Product;
import com.kosa.springbootmyspace.service.CategoryService;
import com.kosa.springbootmyspace.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        try {
            Product saveProduct = productService.save(product);
            if (saveProduct != null) {
                return new ResponseEntity<Product>(saveProduct, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> update(@RequestBody Product product) {
        try {
            int result = productService.update(product);
            if (result == 1) {
                Product updateProduct = productService.findById(product.getIdx());
                return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<Integer> delete(@PathVariable int idx) {
        try {
            int result = productService.delete(idx);
            if (result == 1) {
                return new ResponseEntity<Integer>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<Product> findById(@PathVariable int idx) {
        try {
            Product findproduct = productService.findById(idx);
            if (findproduct != null) {
                return new ResponseEntity<Product>(findproduct, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }

    /**
     * category의 idx로 상품목록을 조회하는 엔드포인트
     * 
     * @param idx
     * @return
     */
    @GetMapping("/category/{idx}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable int idx) {
        try {
            Category category = categoryService.findById(idx);
            List<Product> findProductList = productService.findByCategory(category);
            if (findProductList != null) {
                return new ResponseEntity<>(findProductList, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 상품이름으로 상품목록을 조회하는 엔드포인트
     * 
     * @param searchKeyword
     * @return
     */
    @GetMapping("/search/{searchKeyword}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable String searchKeyword) {
        try {
            List<Product> findProductList = productService.findByNameLike("%" + searchKeyword + "%");
            if (findProductList != null) {
                return new ResponseEntity<List<Product>>(findProductList, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> findAll() {
        try {
            List<Product> productList = productService.findAll();
            if (productList != null) {
                return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
    }
}
