package main.java;

import main.resources.ExtractProperties;

import org.apache.log4j.Level;

import org.apache.log4j.Logger;

import java.util.Scanner;


public class Main {


    public static void main(String[] args) {

        final Logger logger = Logger.getLogger(Main.class);

        Scanner sc = new Scanner(System.in);

        ExtractProperties e = new ExtractProperties();

        int mode;


        try{

            mode = Integer.parseInt(args[0]);


        }catch (ArrayIndexOutOfBoundsException ar) {

            logger.log(Level.INFO, "empty args");

            mode = ExtractProperties.getModeConfig();
        }

        System.out.println(mode);

        char recommencerMenu = 'o';

        while(recommencerMenu == 'o') {

            recommencerMenu = ' ';

            e.runDev();

            e.run();

            if( mode == 1) ExtractProperties.setModeConfig(mode);

            if (ExtractProperties.getModeConfig()==1) {

            System.out.println("Mode DEV");
            
            }

            GamePattern gp = GameLaunch.selectedGameMain();

            try {

                logger.log(Level.INFO,"Les séléctions possibles : 1 ou 2");

                gp.selectedGameMode();

            }catch (NullPointerException nu) {

                logger.log(Level.ERROR,"Des caractères différents de 1 ou 2  ont été renseignés");


                gp.selectedGameMode();
            }


            while (recommencerMenu != 'o' && recommencerMenu != 'n') {

                System.out.println("retour menu ? (o/n)");

                recommencerMenu = sc.next().charAt(0);
            }
        }

        System.out.println("Au revoir");
    }
}




