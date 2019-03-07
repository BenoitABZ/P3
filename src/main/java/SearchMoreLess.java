package main.java;

import java.util.Arrays;

import static java.util.Arrays.fill;

public class SearchMoreLess extends GamePattern {


    private int param[] = new int[tabLength];

    private int median() {

        int mediane;

        int ValAllowed[] = new int[max];

        for (int i = min; i < max; i++) {

            ValAllowed[i] = i;
        }

        mediane = ValAllowed[ValAllowed.length / 2];

        return mediane;
    }


    @Override

    public void selectedChallenger() {

        maxAttempt = main.resources.ExtractProperties.getMaxAttempt();

        for (int i = 0; i < tabLength; i++) {

            secretCombinationComputer[i] = random.nextInt(max - 1);

        }

        while (maxAttempt >= 0 && !Arrays.equals(playerSelection, secretCombinationComputer)) {

            System.out.println("Cherchez la combinaison secrète composée de " + tabLength + " chiffres en " + maxAttempt + " tentatives");


            String input = sc.next();

            parseInput(input, playerSelection);

            maxAttempt--;

            resultat = "";

            for (int i = 0; i < tabLength; i++) {

                if (playerSelection[i] < secretCombinationComputer[i]) {

                    resultat += "+";


                } else if (playerSelection[i] > secretCombinationComputer[i]) {

                    resultat += "-";

                } else {

                    resultat += "=";

                }

            }

            System.out.println(resultat);

        }

        if (Arrays.equals(playerSelection, secretCombinationComputer)) {

            System.out.println("Bravo : vous avez découvert la combinaison secrète de l'ordinateur");

        } else {

            System.out.println("Perdu : vous n'etes pas parvenu à découvrir la combinaison secrète de l'ordinateur");

        }

    }

    @Override

    public void selectedDefender() {

        maxAttempt = main.resources.ExtractProperties.getMaxAttempt();

        System.out.println("Tapez une combinaison secrète composée de " + tabLength + " chiffres " + "(" + maxAttempt + " tentatives)");

        String input = sc.next();

        parseInput(input, secretCombinationPlayer);

        fill(computerSelection, median());


        for (int j = 1; j <= maxAttempt + j; j++) {

            System.out.println(Arrays.toString(computerSelection));

            resultat = "";

            for (int i = 0; i < tabLength; i++) {

                if (secretCombinationPlayer[i] < computerSelection[i]) {

                    resultat += "-";

                    if (j == 1) {


                        param[i] = min;

                    }

                } else if (secretCombinationPlayer[i] > computerSelection[i]) {

                    resultat += "+";

                    if (j == 1) {

                        param[i] = max;

                    }
                } else {

                    resultat += "=";
                }

                if (param[i] == min && secretCombinationPlayer[i] < computerSelection[i] || param[i] == max && secretCombinationPlayer[i] > computerSelection[i]) {

                    computerSelection[i] = (median() + ((int) Math.pow(2, j) - 1) * param[i]) / (int) Math.pow(2, j);

                } else {

                    if (param[i] == max && secretCombinationPlayer[i] < computerSelection[i] || param[i] == min && secretCombinationPlayer[i] > computerSelection[i]) {

                        computerSelection[i] = (((int) Math.pow(2, j) - 1) * median() + param[i]) / (int) Math.pow(2, j);
                    }

                }

            }

            System.out.println(resultat);

            if (Arrays.equals(computerSelection, secretCombinationPlayer)) {

                System.out.println(Arrays.toString(computerSelection));

                System.out.println("Perdu : l'ordinateur a découvert votre combinaison secrète");

                break;
            }

        }

        if (!Arrays.equals(computerSelection, secretCombinationPlayer)) {


            System.out.println("Bravo : l'ordinateur n'est pas parvenu à découvrir votre combinaison secrète");

        }

        maxAttempt--;

    }


    @Override

    public void selectedDual() {

        maxAttempt = main.resources.ExtractProperties.getMaxAttempt();

        for (int i = 0; i < tabLength; i++) {

            secretCombinationComputer[i] = random.nextInt(max - 1);

        }

        System.out.println("Tapez une combinaison secrète composée de " + tabLength + " chiffres " + "(" + maxAttempt + " tentatives)");

        String input = sc.next();

        parseInput(input, secretCombinationPlayer);

        fill(computerSelection, median());

        for (int j = 1; j <= maxAttempt + j; j++) {

            if (j==1) {

                System.out.println("Chercher la combinaison secrète de l'ordinateur composée de " + tabLength + " chiffres " +  "(" + maxAttempt + " tentatives)");
            }

            else {

                System.out.println("A votre tour de Chercher la combinaison secrète de l'ordinateur composée de " + tabLength + " chiffres " + "(" + maxAttempt + " tentatives)");
            }
            input = sc.next();

            parseInput(input, playerSelection);

            resultat = "";

            for (int i = 0; i < tabLength; i++) {

                if (playerSelection[i] < secretCombinationComputer[i]) {

                    resultat += "+";


                } else if (playerSelection[i] > secretCombinationComputer[i]) {

                    resultat += "-";

                } else {

                    resultat += "=";

                }

            }

            System.out.println(resultat);

            if (Arrays.equals(playerSelection, secretCombinationComputer)) {

                System.out.println("Bravo : vous avez découvert la combinaison secrète de l'ordinateur");

                return;

            }

            System.out.println("Au tour de l'ordinateur de Chercher votre combinaison secrète  composée de " + tabLength + " chiffres  " + "(" + maxAttempt + " tentatives)");


            System.out.println(Arrays.toString(computerSelection));

            resultat = "";

            for (int i = 0; i < tabLength; i++) {

                if (secretCombinationPlayer[i] < computerSelection[i]) {

                    resultat += "-";

                    if (j == 1) {


                        param[i] = min;

                    }

                } else if (secretCombinationPlayer[i] > computerSelection[i]) {

                    resultat += "+";

                    if (j == 1) {

                        param[i] = max;

                    }
                } else {

                    resultat += "=";
                }

                if (param[i] == min && secretCombinationPlayer[i] < computerSelection[i] || param[i] == max && secretCombinationPlayer[i] > computerSelection[i]) {

                    computerSelection[i] = (median() + ((int) Math.pow(2, j) - 1) * param[i]) / (int) Math.pow(2, j);

                } else {

                    if (param[i] == max && secretCombinationPlayer[i] < computerSelection[i] || param[i] == min && secretCombinationPlayer[i] > computerSelection[i]) {

                        computerSelection[i] = (((int) Math.pow(2, j) - 1) * median() + param[i]) / (int) Math.pow(2, j);
                    }

                }

            }

            System.out.println(resultat);

            resultat = "";

            if (Arrays.equals(computerSelection, secretCombinationPlayer)) {


                System.out.println("Perdu : l'ordinateur a découvert votre combinaison secrète");

                return;
            }

            maxAttempt --;

        }

        System.out.println("Perdu : vous n'etes pas parvenu à découvrir la combinaison secrète de l'ordinateur");
    }

}






