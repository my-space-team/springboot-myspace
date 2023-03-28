package com.kosa.springbootmyspace.service;

import com.kosa.springbootmyspace.domain.Review;
import com.kosa.springbootmyspace.domain.Score;
import com.kosa.springbootmyspace.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public Score save(Score score) {
        score.setTotal((score.getDelivery() + score.getPrice() + score.getDesign() + score.getPrice()) / 4f);
        return scoreRepository.save(score);
    }

    @Override
    public int update(Score score) {
        Optional<Score> findScore = scoreRepository.findById(score.getIdx());
        int result = 0;
        if (findScore.isPresent()) {
            Score dbScore = findScore.get();
            dbScore.setDurability(score.getDurability());
            dbScore.setPrice(score.getPrice());
            dbScore.setDesign(score.getDesign());
            dbScore.setReview(new Review().getScore().getReview());
            dbScore.setTotal((score.getDelivery() + score.getPrice() + score.getDesign() + score.getPrice()) / 4f);
            scoreRepository.save(dbScore);
            result = 1;
        }

        return result;
    }

    @Override
    public int delete(int idx) {
        Optional<Score> findScore = scoreRepository.findById(idx);
        int result = 0;
        if (findScore.isPresent()) {
            scoreRepository.delete(findScore.get());
            return 1;
        }
        return result;
    }

    @Override
    public Score findById(int idx) {
        Score score = scoreRepository.findById(idx).get();
        return score;
    }

    @Override
    public List<Score> findAll() {
        return scoreRepository.findAll();
    }
}
