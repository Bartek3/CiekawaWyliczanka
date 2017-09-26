import java.util.Scanner;

public class ciekawaWyliczanka {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int input = input(in); //Pobranie liczby mieszczącej się w zakresie 1 - 1 000 000 000
        double howManyCiphers = howManyCiphers(input); //Sprawdzenie, z ilu cyfer będzie się składać liczba wyjściowa
        double actualRest = actualRest(howManyCiphers, input); //Ustalenie pozycji liczby w wierszu drzewa matematycznego
        String[] Output = new String[(int) howManyCiphers]; //Zadeklarowanie tablicy zawierającej cyfry liczby wyjściowej
        Output[Output.length - 1] = fiveOrSix(actualRest); //Ustalenie ostatniej cyfry liczby wyjściowej

        //Ustalenie pozostałych cyfr liczby wyjściowej
        for (int i = Output.length - 2; i >= 0; i--) {
            double newRest = newRest(howManyCiphers, actualRest);
            input -= newRest;
            howManyCiphers -= 1;
            actualRest = actualRest(howManyCiphers, input);
            Output[i] = fiveOrSix(actualRest);
        }

        String outputString = ""; //Zadeklarowanie Stringa zawierającego liczbę wyjściową

        //Połączenie elementów tablicy zawierającej cyfry liczby wyjściowej w jedno słowo, czyli outputString
        for (int i = 0; i <= Output.length - 1; i++) {
            outputString = outputString.concat(Output[i]);
        }

        System.out.println(outputString); //Podanie użytkownikowi liczby wyjściowej
    }


    //Metoda pobierająca liczbę wejściową w założonym zakresie (1 - 1 000 000 000)
    private static int input(Scanner in) {
        if (!in.hasNextInt()) {
            System.out.println("Input is not a valid number from range 1 - 1 000 000 000!");
            System.exit(0);
        }
        int input = in.nextInt();

        if (input > 1000000000 || input < 1) {
            System.out.println("Input is not a valid number from range 1 - 1 000 000 000!");
            System.exit(0);
        }

        return input;
    }

    //Metoda sprawdzająca, z ilu cyfr będzie się składać liczba wyjściowa
    private static double howManyCiphers(int input) {
        double pow = 1;
        do {
            pow++;
        }
        while (input - Math.pow(2, pow) >= -1);
        return pow - 1;
    }

    //Metoda określająca początkowy wiersz liczby w drzewie matematycznym
    private static double sumOfPows(double howManyCiphers) {
        double sumOfPows = 0;
        for (int i = 1; i <= howManyCiphers - 1; i++) {
            sumOfPows += Math.pow(2, i);
        }
        return sumOfPows;
    }

    //Metoda ustalająca pozycję liczby wejściowej na aktualnym szczeblu drzewa matematycznego
    private static double actualRest(double howManyCiphers, int input) {
        return input - sumOfPows(howManyCiphers);
    }

    //Metoda ustalająca pozycję liczby wejściowej na niższym szczeblu drzewa matematycznego
    private static double newRest(double howManyCiphers, double actualRest) {
        double lowerPow = Math.pow(2, howManyCiphers - 1);
        double temp = (Math.floor(actualRest / 2));
        return lowerPow + temp;
    }

    /*Ustalenie, czy ostatnia cyfra odpowiedniej liczby ciekawej to 5, czy 6.
        (Jeżeli liczba wejściowa jest parzysta to 6, jeżeli jest nieparzysta to 5)*/
    private static String fiveOrSix(double rest) {
        if (rest % 2 != 0) {
            return "5";
        }
        return "6";
    }
}
