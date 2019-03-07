package main.java;

import main.resources.ExtractProperties;

import java.util.Random;

import java.util.Scanner;

import org.apache.log4j.Logger;

import org.apache.log4j.Level;


public abstract class GamePattern {

   protected  final Logger logger = Logger.getLogger(GamePattern.class);

    private final String modeChoice = "Choix du mode: (1 : Challenger 2 : Defenseur 3 : Dual)";

    Scanner sc = new Scanner(System.in);

    Random random = new Random();

    protected static String resultat = "";

    protected static int maxAttempt = ExtractProperties.getMaxAttempt();

    protected static int tabLength = ExtractProperties.getTablength();

    protected static int max = ExtractProperties.getMax();

    protected static int min = ExtractProperties.getMin();

    protected int[] secretCombinationPlayer = new int[tabLength];

    protected int [] secretCombinationComputer = new int [tabLength];

    protected int [] playerSelection = new int[tabLength];

    protected  int [] computerSelection = new int[tabLength];


    protected abstract void selectedChallenger ();

    protected abstract void selectedDefender();

    protected abstract void selectedDual();


    protected void parseInput(String input, int tab[]) {

        try {

            logger.log(Level.INFO,"Exception potentielle : dépassement du nombre maximal d'éléments par combinaison");

            for (int i = 0; i < input.length(); i++) {

                String tmp = "" + input.charAt(i);

                tab[i] = Integer.parseInt(tmp);

            }

        } catch (Exception e) {

            logger.log(Level.ERROR, "Dépassement de capacité, retapez votre combinaison");


            //System.out.println("Retapez votre combinaison");

            parseInput(sc.next(), tab);
        }
    }

    public void selectedGameMode() {

        char recommencerJeu = 'o';

        while (recommencerJeu == 'o') {

            recommencerJeu = ' ';

            System.out.println(modeChoice);

            try {

               logger.log(Level.INFO,"Les séléctions possibles : 1, 2 ou 3");

                int gameMode = sc.nextInt();

                if (gameMode == 1) selectedChallenger();

                else if (gameMode == 2) selectedDefender();

                else if (gameMode == 3) selectedDual();

                else throw new IllegalArgumentException();

            } catch (Exception e) {


                logger.log(Level.ERROR,"Des caractères différents de 1, 2 ou 3 ont été renseignés");


                selectedGameMode();
            }


            while (recommencerJeu != 'o' && recommencerJeu != 'n') {

                System.out.println("recommencer la partie ? (o/n)");

                recommencerJeu = sc.next().charAt(0);

            }

        }
    }
}
