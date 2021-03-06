package main.resources;

import java.io.InputStream;

import java.util.Properties;

public class ExtractProperties {



    private static int tablength;


    private static int modeConfig = 0;

    private static int max;

    private static int maxAttempt;

    private static int  min;

    public static int getTablength() {
        return tablength;
    }

    public static int getMax() {
        return max;
    }

    public static int getMaxAttempt() {
        return maxAttempt;
    }

    public static int getMin() {
        return min;
    }

    public static int getModeConfig() { return modeConfig;}

    public static void setModeConfig(int modeConfig) { ExtractProperties.modeConfig = modeConfig;}


    public void run () {



        try {

            Properties prop = new Properties();

            InputStream inputStream;

            String file = "main/resources/config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(file);

            if (inputStream != null) {

                prop.load(inputStream);
            }


            tablength = Integer.parseInt(prop.getProperty("TAB_DIM"));

            maxAttempt = Integer.parseInt(prop.getProperty("MAX_ATTEMPT"));

            max = Integer.parseInt(prop.getProperty("MAX_VAL"));

            min = Integer.parseInt(prop.getProperty("MIN_VAL"));

            System.out.println("valeur max d'un élément de la combinaison " +  " = " + prop.getProperty("MAX_VAL"));

            System.out.println("valeur min d'un élément de la combinaison " +  " = " + prop.getProperty("MIN_VAL"));

            System.out.println("nombre de tentative maximal " +  " = " + prop.getProperty("MAX_ATTEMPT"));

            System.out.println("nombre d'éléments d'une combinaison " +  " = " + prop.getProperty("TAB_DIM"));


        }catch (Exception e){}

    }

    public void runDev(){
        try {

            Properties prop = new Properties();

            InputStream inputStream;

            String file = "main/resources/config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(file);

            if (inputStream != null) {

                prop.load(inputStream);
            }

            modeConfig = Integer.parseInt(prop.getProperty("MODE_CONFIG"));

        }catch (Exception e){}

    }

}



