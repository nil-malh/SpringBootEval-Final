package fr.ipme.coupedumonde;

import fr.ipme.coupedumonde.entities.foot.Equipe;
import fr.ipme.coupedumonde.entities.foot.Groupe;
import fr.ipme.coupedumonde.entities.foot.Match;
import fr.ipme.coupedumonde.entities.foot.Score;
import fr.ipme.coupedumonde.entities.user.RoleEnum;
import fr.ipme.coupedumonde.entities.user.User;
import fr.ipme.coupedumonde.repository.*;
import fr.ipme.coupedumonde.services.MatchService;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CoupeDuMondeApplicationTests {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchService matchService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private PronosticRepository pronosticRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    private Logger logger = LoggerFactory.getLogger(CoupeDuMondeApplicationTests.class);


    @Test
    void addUserToRepoTest() {
        User m = new User("Nil", RoleEnum.ADMIN);
        userRepository.save(m);
        User m1 = new User("Jean-Cul", RoleEnum.USER);
        userRepository.save(m1);
        User m2 = new User("Macaroni", RoleEnum.USER);
        userRepository.save(m2);
        System.out.println(userRepository.findAll());
    }

    @Test
    void repo2Test() {
        //Teams creation
        Equipe france = new Equipe("France");
        Equipe angleterre = new Equipe("Angleterre");
        Equipe espagne = new Equipe("Espagne");

        france = equipeRepository.save(france);
        angleterre = equipeRepository.save(angleterre);
        espagne = equipeRepository.save(espagne);

        //Scores creation
        Score scoreFranceAngleterre = new Score();
        scoreFranceAngleterre.setScoreFinalEquipeA(3);
        scoreFranceAngleterre.setScoreFinalEquipeB(0);
        scoreFranceAngleterre = scoreRepository.save(scoreFranceAngleterre);
        Score scoreFranceEspagne = new Score();
        scoreFranceEspagne.setScoreFinalEquipeA(2);
        scoreFranceEspagne.setScoreFinalEquipeB(5);
        scoreFranceEspagne = scoreRepository.save(scoreFranceEspagne);

        //Matchs creation
        Match franceAngleterre = new Match(france, angleterre, scoreFranceAngleterre);
        Match franceEspagne = new Match(france, espagne, scoreFranceEspagne);

        franceAngleterre = matchRepository.save(franceAngleterre);
        franceEspagne = matchRepository.save(franceEspagne);

        List<Match> frenchMatchs = matchRepository.findAllByEquipeA_Id(france.getId());
        frenchMatchs.addAll(matchRepository.findAllByEquipeB_Id(france.getId()));
        dispMatchs(frenchMatchs, "France");

        List<Match> spanishMatchs = matchRepository.findAllByEquipeA(espagne);
        spanishMatchs.addAll(matchRepository.findAllByEquipeB(espagne));
        dispMatchs(spanishMatchs, "Espagne");

        matchRepository.delete(franceAngleterre);

        List<Match> newFrenchMatchs = matchRepository.findAllByEquipeId(france.getId());
        dispMatchs(newFrenchMatchs, "France");


    }

    @Test
    void playTest() {
        //Teams creation
        Equipe france = new Equipe("France");
        Equipe angleterre = new Equipe("Angleterre");

        france = equipeRepository.save(france);
        angleterre = equipeRepository.save(angleterre);



        //Matchs creation
        Match franceAngleterre = new Match(france, angleterre);

        matchRepository.save(franceAngleterre);
        List<Match> newFrenchMatchs = matchRepository.findAllByEquipeId(france.getId());
        dispMatchs(newFrenchMatchs, "France");

        franceAngleterre = matchService.play(franceAngleterre);


        newFrenchMatchs = matchRepository.findAllByEquipeId(france.getId());
        dispMatchs(newFrenchMatchs, "France");


    }


    @Test
    public void isMatchFinishedTest()
    {
        Equipe france = new Equipe("France");
        Equipe angleterre = new Equipe("Angleterre");

        france = equipeRepository.save(france);
        angleterre = equipeRepository.save(angleterre);

        Match matchNotFinished = new Match(france,angleterre, LocalDateTime.of(2023,1,1,19,0));
        logger.info("Match date = " + matchNotFinished.getMatchDate());
        logger.info("isMatchedNotFinished (should be false): " + matchNotFinished.isMatchNotFinished());
        Match matchFinished = new Match(france,angleterre, LocalDateTime.of(2024,1,1,19,0));
        logger.info("Match date = " + matchFinished.getMatchDate());
        logger.info("isMatchedNotFinished (should be true): " + matchFinished.isMatchNotFinished());
    }

    // ! Shitty rank declaration it's just for tests
public int rank = 0;

    @Test
    public void checkLeaderboard()
    {


        User m = new User("Nil", RoleEnum.ADMIN);
        m.addWonBets();
        m.addWonBets();
        m.addWonBets();
        m.addWonBets();
        m.addWonBets();
        userRepository.save(m);
        User m1 = new User("Cyril", RoleEnum.USER);
        m1.addWonBets();
        m1.addWonBets();
        m1.addWonBets();

        userRepository.save(m1);
        User m2 = new User("Macaroni", RoleEnum.USER);
        userRepository.save(m2);
        logger.info("Displaying all user ordered by wonBets (first output should be Nil)");
        userRepository.findAllOrderedByWonBets().forEach(user -> {
            rank++;
            logger.info(rank + " " + user.getName() + " wonBets : " + user.getWonBets());
        });

    }

@Test
public void checkGroupFunctionality()
{
    logger.info("Creating group, that should be fun");
    Groupe groupe = new Groupe();
    logger.info("Creating teams to insert into group.");
    Equipe gt1 = new Equipe(RandomCountry.getRandomCountry());
    Equipe gt2 = new Equipe(RandomCountry.getRandomCountry());
    Equipe gt3 = new Equipe(RandomCountry.getRandomCountry());
    Equipe gt4 = new Equipe(RandomCountry.getRandomCountry());
    equipeRepository.saveAll(Arrays.asList(gt1, gt2, gt3, gt4));


    logger.info("Teams creation done.");
    logger.info("Creating match to insert into group.");
    Match gm1 = new Match(gt1,gt2);
    Match gm2 = new Match(gt1,gt3);
    Match gm3 = new Match(gt1,gt4);
    Match gm4 = new Match(gt2,gt3);
    Match gm5 = new Match(gt2,gt3);
    Match gm6 = new Match(gt3,gt4);
    matchRepository.saveAll(Arrays.asList(gm1, gm2, gm3, gm4, gm5, gm6));

    logger.info("Matches creation done");

    logger.info("Putting matches and teams into list and set them to the group");
    groupe.setEquipes(Arrays.asList(gt1, gt2, gt3, gt4));
    groupe.setMatchs(Arrays.asList(gm1, gm2, gm3, gm4, gm5, gm6));
    logger.info("Group properties should be set");
    logger.info("Saving group into its repository this is where the fun ends");
    groupeRepository.save(groupe);

    logger.info("Setting group property to teams");
     gt1.setGroupe(groupe);
     gt2.setGroupe(groupe);
     gt3.setGroupe(groupe);
     gt4.setGroupe(groupe);
    equipeRepository.saveAll(Arrays.asList(gt1, gt2, gt3, gt4));
    logger.info("Test finished group should be created and teams should have a groupID.");
}



    private void dispMatchs(List<Match> matchs, String teamName){
        System.out.println("Matchs auxquels " + teamName + " participe : ");
        matchs.forEach(System.out::println);
    }
}
