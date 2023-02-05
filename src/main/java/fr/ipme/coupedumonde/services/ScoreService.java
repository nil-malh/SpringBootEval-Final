package fr.ipme.coupedumonde.services;

import fr.ipme.coupedumonde.entities.foot.Score;
import fr.ipme.coupedumonde.repository.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ScoreService {

    private static Random rdm = new Random();
    private static int SCORE_MAX = 5;

    public static Score generateAScore() {
        Score score = new Score();
        score.setScoreFinalEquipeA(rdm.nextInt(SCORE_MAX));
        score.setScoreFinalEquipeB(rdm.nextInt(SCORE_MAX));
        return score;
    }
}
