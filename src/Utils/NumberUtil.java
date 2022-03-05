package Utils;

import java.util.Random;

public class NumberUtil {
    public static int randomInt(int min, int max) {
        try {
            if(max < min) throw new Exception("parameter 'min' cannot be bigger than 'max'");
        }
        catch(Exception e) {
            e.printStackTrace();
            return 0;
        }

        Random random = new Random();
        int interval = max - min;
        return random.nextInt(interval) + min;
    }

    public static double randomDouble(double min, double max) {
        try {
            if(max < min) throw new Exception("parameter 'min' cannot be bigger than 'max'");
        }
        catch(Exception e) {
            e.printStackTrace();
            return 0;
        }

        Random random = new Random();
        double interval = max - min;
        return random.nextDouble(interval) + min;
    }
}
