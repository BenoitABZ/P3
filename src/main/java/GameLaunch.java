package main.java;

import org.apache.log4j.Level;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class GameLaunch {

    private static final Logger logger = Logger.getLogger(GameLaunch.class);

    private final static String gameChoice = " Choix du jeu : (1 : SearchMoreLess, 2 : MasterMind)";


    public static GamePattern selectedGameMain () {

        Scanner sc = new Scanner(System.in);

        System.out.println(gameChoice);


            try {

                logger.log(Level.INFO,"Les séléctions possibles : 1 ou 2");

                int select = sc.nextInt();

                if (select == 1) {

                    System.out.println("Choix : SearchMoreless");

                    return new SearchMoreLess();
                }

                else if (select == 2) {

                    System.out.println("Choix : MasterMind");

                    return new MasterMind();
                }

                else throw new IllegalArgumentException();

            }catch(Exception e) {

                logger.log(Level.ERROR,"Des caractères différents de 1 ou 2  ont été renseignés");

                selectedGameMain();
            }

             return selectedGameMain();





        }
    }


