package fr.ipme.coupedumonde.services;

import fr.ipme.coupedumonde.entities.foot.Match;
import fr.ipme.coupedumonde.entities.foot.Score;
import fr.ipme.coupedumonde.repository.MatchRepository;
import fr.ipme.coupedumonde.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MatchService extends GenericService<Match>{

    private ScoreRepository scoreRepository;


    @Autowired
    public MatchService(MatchRepository repo, ScoreRepository scoreRepository, ScoreService scoreService) {
        this.repo = repo;
        this.scoreRepository = scoreRepository;
    }

    //for testing purposes only
    public Match play(Match matchToPlay){
        if (null == matchToPlay){
            throw new RuntimeException("I CANT PLAY THAT");
        }
        return play(matchToPlay.getId(), matchToPlay);
    }

    @Transactional
    public Match play(Long matchId, Match matchToPlay){
        if (null == matchToPlay || null == matchId){
            //should actually be handled by the controller but whatever
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't play a non existing match");
        }

        if (matchToPlay.getId() != null) {                                              //if provided with a match id
            if (!matchId.equals(matchToPlay.getId())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Match ids make no sense, hello ?");
            }
            Optional<Match> maybeMatch = repo.findById(matchId);
            if (!maybeMatch.isPresent()) {                                              //if it doesnt exist
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided match not found");
            } else {
                //match already exists, let's retrieve it
                matchToPlay = maybeMatch.get();
            }
        }

        if (null == matchToPlay.getEquipeA() || null == matchToPlay.getEquipeB()) {      //if it's missing a team
            //should actually be handled by the controller but whatever
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Match can not be played without a team");
        }

        Score score = ScoreService.generateAScore();


        matchToPlay.setScore(score);                                                    //associating score to match
        scoreRepository.save(score);                                                    //saving the score

        return repo.save(matchToPlay);

    }
}
