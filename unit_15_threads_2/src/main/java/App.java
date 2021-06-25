import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class App {
    private static final int HORSE_COUNT = 10;
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        int choice = 0;
        System.out.println("Ten horses take part in competition.");
        System.out.println("Please, choose the number of your horse.");

        try {
            choice = readNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Hippodrome hippodrome = new Hippodrome(HORSE_COUNT);
        Hippodrome.Horse[] horses = new Hippodrome.Horse[HORSE_COUNT];
        for (int i = 0; i < HORSE_COUNT; i++) {
            horses[i] = new Hippodrome.Horse("horse " + (i+1), hippodrome);
        }

        hippodrome.startCompetition(horses);

        Map<Hippodrome.Horse, Integer> score = hippodrome.getScore();
        score.forEach((key, value) -> System.out.println(
                "{house name=" + key.getHorseName() +
                        ", place=" + value + "}"
        ));

        System.out.println("The place of your horse is " + hippodrome.getPlace(choice));

    }
    private static int readNumber() throws IOException {
        while (true){
            String input = reader.readLine();
            try {
                int id = Integer.parseInt(input);
                if(id<=0 || id>10) throw new NumberFormatException();
                return id;
            }catch (NumberFormatException e){
                System.out.println("Illegal input. Try again.");
            }
        }
    }
}
