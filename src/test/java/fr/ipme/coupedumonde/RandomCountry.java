package fr.ipme.coupedumonde;

import java.util.Random;

public class RandomCountry {
    public static String getRandomCountry() {
        String[] countries = {
                "Argentina",
                "Brazil",
                "France",
                "Germany",
                "Italy",
                "Spain",
                "Belgium",
                "England",
                "Portugal",
                "Uruguay",
                "Croatia",
                "Denmark",
                "Mexico",
                "Sweden",
                "Switzerland",
                "Colombia",
                "Senegal",
                "Japan",
                "Russia",
                "Iran",
                "South Korea"
        };
        Random random = new Random();
        return countries[random.nextInt(countries.length)];
    }
}