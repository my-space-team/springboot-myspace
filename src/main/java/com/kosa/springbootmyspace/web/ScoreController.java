package com.kosa.springbootmyspace.web;

import com.kosa.springbootmyspace.domain.Score;
import com.kosa.springbootmyspace.service.ReviewService;
import com.kosa.springbootmyspace.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/save/{reviewIdx}")
    public ResponseEntity<Score> save(@RequestBody Score score, @PathVariable int reviewIdx) {
        try {
            score.setReview(reviewService.findById(reviewIdx));
            Score saveScore = scoreService.save(score);

            if (saveScore != null) {
                return new ResponseEntity<Score>(saveScore, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Score>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Score> update(@RequestBody Score score) {
        try {
            int result = scoreService.update(score);
            if (result == 1) {
                Score updateScore = scoreService.findById(score.getIdx());
                return new ResponseEntity<Score>(updateScore, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Score>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<Integer> delete(@PathVariable int idx) {
        try {
            int result = scoreService.delete(idx);
            if (result == 1) {
                return new ResponseEntity<Integer>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<Score> findById(@PathVariable int idx) {
        try {
            Score findScore = scoreService.findById(idx);
            if (findScore != null) {
                return new ResponseEntity<Score>(findScore, HttpStatus.OK);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<Score>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Score>> findAll() {
        try {
            List<Score> allScore = scoreService.findAll();
            if (allScore != null) {
                return new ResponseEntity<List<Score>>(allScore, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<List<Score>>(HttpStatus.NO_CONTENT);
    }
}
