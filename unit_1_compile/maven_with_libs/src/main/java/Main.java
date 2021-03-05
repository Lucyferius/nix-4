import Other.First;
import Other.Second;
import org.apache.commons.lang3.StringUtils;

public class Main {
    public static void main(String[] args) {

        Second second = new Second();
        First first = new First();

        System.out.println("Main`s already worked.");
        System.out.println(StringUtils.upperCase("Main`s already worked."));

    }
}
