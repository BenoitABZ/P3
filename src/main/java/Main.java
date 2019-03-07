package main.java;

import main.resources.ExtractProperties;

import java.util.Scanner;


public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ExtractProperties e = new ExtractProperties();


        char recommencerMenu = 'o';

        while(recommencerMenu == 'o') {

            recommencerMenu = ' ';

            e.run();

            GamePattern gp = GameLunch.selectedGameMain();

            gp.selectedGameMode();


            while (recommencerMenu != 'o' && recommencerMenu != 'n') {

                System.out.println("retour menu ? (o/n)");

                recommencerMenu = sc.next().charAt(0);
            }
        }

        System.out.println("Au revoir");
    }
}




