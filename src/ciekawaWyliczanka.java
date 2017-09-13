import java.util.Scanner;

public class ciekawaWyliczanka {

    private static double howManyCiphers(long input) {
        double pow = 1;
        do {
            pow++;
        }
        while (input - Math.pow(2, pow) >= -1);
        return pow - 1;
    }

    private static double sumOfPows(double howManyCiphers) {
        double sumOfPows = 0;
        for (int i = 1; i <= howManyCiphers - 1; i++) {
            sumOfPows += Math.pow(2, i);
        }
        return sumOfPows;
    }

    private static double actualRest(double howManyCiphers, long input) {
        return input - sumOfPows(howManyCiphers);
    }

    private static double newRest(double howManyCiphers, double actualRest) {
        double lowerPow = Math.pow(2, howManyCiphers - 1);
        double temp = (Math.floor(actualRest / 2));
        return lowerPow + temp;
    }

    private static String fiveOrSix(double rest) {
        if (rest % 2 != 0) {
            return "5";
        }
        return "6";
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        long input = in.nextLong();
        double howManyCiphers = howManyCiphers(input);
        double actualRest = actualRest(howManyCiphers, input);
        String[] Output = new String[(int) howManyCiphers];
        Output[Output.length - 1] = fiveOrSix(actualRest);

        for (int i = Output.length - 2; i >= 0; i--) {
            double newRest = newRest(howManyCiphers, actualRest);
            input -= newRest;
            howManyCiphers -= 1;
            actualRest = actualRest(howManyCiphers, input);
            Output[i] = fiveOrSix(actualRest);
        }

        String outputString = "";

        for (int i = 0; i <= Output.length - 1; i++) {
            outputString = outputString.concat(Output[i]);
        }

        System.out.println(outputString);
    }
}
