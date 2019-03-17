package main.java;

import java.util.*;


public class MasterMind extends GamePattern {



    private boolean islistfiled (int [] sol , int max, int size) {

        for (int i = 0; i<size; i++){

            if(sol[i] != max) return true;
        }

        return false;
    }

    private int [] reaffect(int [] sol, int max, int size) {

        for (int i = size -1; i>0; i--){

            if (sol[i] == max + 1) {

                sol[i] = 0;

                sol [i-1] ++;
            }
        }

        return sol;
    }

    private int [] copyTab (int [] tab){

        int [] cpy = new int[tab.length];

        for (int i= 0; i<tabLength; i++) {

            cpy[i] = tab[i];
        }

        return cpy;
    }

    private int [] arbitrage (int[] combinationSelection, int [] SecretCombination) {

        int wellplaced = 0;

        int missplaced = 0;

        int [] arbitrage = new int [2];

       int [] cpy1 = Arrays.copyOf(combinationSelection, tabLength);

       int [] cpy2 = Arrays.copyOf(SecretCombination, tabLength);



        for (int i = 0; i < tabLength; i++) {

            if (cpy1[i] == SecretCombination[i]) {

                cpy1[i] = -1;

                cpy2[i] = -2;

                wellplaced++;
            }
        }

        arbitrage [0] = wellplaced;

        for (int i = 0; i < tabLength; i++) {

            for (int j = 0; j < tabLength; j++) {

                if (cpy1[j] == cpy2[i]) {

                    cpy1[j] = -1;

                    missplaced++;

                    break;
                }
            }
        }

        arbitrage [1] = missplaced;

        return arbitrage;

    }

    private int  sumArbitrage (int[] arbitrage) {


        int sumArb = arbitrage [0] + arbitrage [1];

        return sumArb;
    }


    @Override

    public void selectedChallenger() {

        maxAttempt = main.resources.ExtractProperties.getMaxAttempt();


        for (int i = 0; i < tabLength; i++) {

            secretCombinationComputer[i] = random.nextInt(max - 1);

            if (modeConfig == 1) {

                System.out.println("Solution :");

                System.out.println(Arrays.toString(secretCombinationComputer));
            }

        }


        while (maxAttempt >= 0 && !Arrays.equals(playerSelection, secretCombinationComputer)) {

            System.out.println("Chercher la combinaison secrète de l'ordinateur composée de " + tabLength + " chiffres " +  "(" + maxAttempt + " tentatives)");

            String input = sc.next();

            parseInput(input, playerSelection);

            int [] arbitrage = arbitrage(playerSelection, secretCombinationComputer);

            System.out.println("BP = " + arbitrage [0]+ " MP = " + arbitrage[1]);

            maxAttempt--;

            }

        if (Arrays.equals(playerSelection, secretCombinationComputer)) {

            System.out.println("Bravo : vous avez découvert la combinaison secrète de l'ordinateur");

        } else{

            System.out.println("Perdu : vous n'etes pas parvenu à découvrir la combinaison secrète de l'ordinateur");

            System.out.println("solution : " + Arrays.toString(secretCombinationComputer));




        }
    }

    @Override

    public void selectedDefender() {

        maxAttempt = main.resources.ExtractProperties.getMaxAttempt();


        System.out.println("Tapez une combinaison secrète composée de " + tabLength + " chiffres " + "(" + maxAttempt + " tentatives)");

        String input = sc.next();

        parseInput(input, secretCombinationPlayer);

        System.out.println("Merci de patienter...");


        List<int[]> lst = new ArrayList<>();



        int[] sol = new int[tabLength];

        lst.add(copyTab(sol));

        int[] firstCombinationComputer = new int[tabLength];





        while (islistfiled(sol, max, tabLength)) {

            sol[tabLength - 1]++;

            if (sol[tabLength - 1] == max + 1) {

                sol = reaffect(sol, max, tabLength);
            }

            lst.add(copyTab(sol));
        }



        for (int i = 0; i < tabLength; i++) {

            if (i <= tabLength / 2) firstCombinationComputer[i] = 0;

            else {

                firstCombinationComputer[i] = 1;
            }
        }



        for(int i=0; i<tabLength; i++) {

            computerSelection [i] = firstCombinationComputer [i];

            }



        for (int m = 0; m < maxAttempt; m++) {

            if (m > 0) {

                for (int i = 0; i < tabLength; i++) {

                    computerSelection[i] = lst.get(0)[i];

                }
            }


            System.out.println(Arrays.toString(computerSelection));



            int [] arbitrage = arbitrage(computerSelection, secretCombinationPlayer);

            System.out.println("BP = " + arbitrage [0]+ " MP = " + arbitrage[1]);

            int sum = sumArbitrage(arbitrage);

                List<int[]> lstTampon = new ArrayList<>();

                for (int i = 1; i < lst.size(); i++) {

                    if (sumArbitrage(arbitrage(lst.get(i), computerSelection)) == sum) {

                        lstTampon.add(lst.get(i));
                    }
                }

                lst = lstTampon;

                if (Arrays.equals(computerSelection, secretCombinationPlayer)){

                System.out.println("Perdu : l'ordinateur a découvert votre combinaison secrète");


                return ;
            }
            }

        System.out.println("Bravo : l'ordinateur n'est pas parvenu à découvrir votre combinaison secrète");
        }





    @Override

    public void selectedDual() {

        maxAttempt = main.resources.ExtractProperties.getMaxAttempt();


        for (int i = 0; i < tabLength; i++) {

            secretCombinationComputer[i] = random.nextInt(max - 1);

            if (modeConfig == 1) {

                System.out.println("Solution :");

                System.out.println(Arrays.toString(secretCombinationComputer));
            }

        }

        System.out.println("Tapez une combinaison secrète composée de " + tabLength + " chiffres " + "(" + maxAttempt + " tentatives)");

        String input = sc.next();

        parseInput(input, secretCombinationPlayer);



        List<int[]> lst = new ArrayList<>();


        int[] sol = new int[tabLength];

        lst.add(copyTab(sol));

        int[] firstCombinationComputer = new int[tabLength];


        while (islistfiled(sol, max, tabLength)) {

            sol[tabLength - 1]++;

            if (sol[tabLength - 1] == max + 1) {

                sol = reaffect(sol, max, tabLength);
            }

            lst.add(copyTab(sol));
        }



        for (int i = 0; i < tabLength; i++) {

            if (i <= tabLength / 2) firstCombinationComputer[i] = 0;

            else {

                firstCombinationComputer[i] = 1;
            }
        }



        for(int i=0; i<tabLength; i++) {

            computerSelection [i] = firstCombinationComputer [i];

        }



        for (int m = 0; m < maxAttempt + m; m++) {

            if (m == 0) {

                System.out.println("Chercher la combinaison secrète de l'ordinateur composée de " + tabLength + " chiffres " +  "(" + maxAttempt + " tentatives)");
            }

            else {

                System.out.println("A votre tour de Chercher la combinaison secrète de l'ordinateur composée de " + tabLength + " chiffres " + "(" + maxAttempt + " tentatives)");
            }

            input = sc.next();

            parseInput(input, playerSelection);

            int [] arbitrageChallenger = arbitrage(playerSelection, secretCombinationComputer);

            System.out.println("BP = " + arbitrageChallenger [0]+ " MP = " + arbitrageChallenger[1]);


            if (Arrays.equals(playerSelection, secretCombinationComputer)) {

                System.out.println("Bravo : vous avez découvert la combinaison secrète de l'ordinateur");

                return;

        }

            System.out.println("Au tour de l'ordinateur de Chercher votre combinaison secrète  composée de " + tabLength + " chiffres  " + "(" + maxAttempt + " tentatives)");


            if (m > 0) {

                for (int i = 0; i < tabLength; i++) {

                    computerSelection[i] = lst.get(0)[i];

                }
            }

            System.out.println(Arrays.toString(computerSelection));

            int [] arbitrageDefender = arbitrage(computerSelection, secretCombinationPlayer);

            System.out.println("BP = " + arbitrageDefender [0]+ " MP = " + arbitrageDefender[1]);

            int sum = sumArbitrage(arbitrageDefender);

            List<int[]> lstTampon = new ArrayList<>();

            for (int i = 1; i < lst.size(); i++) {

                if (sumArbitrage(arbitrage(lst.get(i), computerSelection)) == sum) {

                    lstTampon.add(lst.get(i));
                }
            }

            lst = lstTampon;

            if (Arrays.equals(computerSelection, secretCombinationPlayer)){

                System.out.println("Perdu : l'ordinateur a découvert votre combinaison secrète");


                return ;
            }

            maxAttempt--;
        }

        System.out.println("Perdu : vous n'etes pas parvenu à découvrir la combinaison secrète de l'ordinateur");

        System.out.println("solution : " + Arrays.toString(secretCombinationComputer));

    }

}







