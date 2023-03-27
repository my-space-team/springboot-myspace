package com.kosa.springbootmyspace.service;

import java.util.List;
import java.util.Optional;

import com.kosa.springbootmyspace.domain.Category;
import com.kosa.springbootmyspace.domain.Review;
import com.kosa.springbootmyspace.domain.Score;
import com.kosa.springbootmyspace.repository.ReviewRepository;
import com.kosa.springbootmyspace.repository.ScoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.springbootmyspace.domain.Product;
import com.kosa.springbootmyspace.repository.ProductRepository;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public int delete(int idx) {
        Optional<Product> product = productRepository.findById(idx);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return 1;
        }
        return 0;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int idx) {

        Product product=productRepository.findById(idx).get();
        log.info("product -> ", product);
        int reviewCount=reviewRepository.findByProductIdx(idx).size();
        System.out.println("reviewCount : "+reviewCount);

        float allTotal = 0;
        if (reviewCount != 0) {
            for (Review review : reviewRepository.findByProductIdx(idx)) {

                List<Score> scores = scoreRepository.findByReviewIdx(review.getIdx());

                for (Score s : scores) {
                    allTotal += s.getTotal();
                }
            }
            float allTotalAvg = allTotal / reviewCount;
            System.out.println("allTotal : " + allTotal);
            System.out.println("allTotalAvg : " + allTotalAvg);
            product.setAllTotal(allTotalAvg);
        }

        return product;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public int update(Product product) {
        productRepository.save(product);
        return 1;
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findByNameLike(String searchKeyword) {
        return productRepository.findByNameLike(searchKeyword);
    }
}
