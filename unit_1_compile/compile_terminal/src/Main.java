import Other.First;
import Other.Second;
import org.apache.commons.lang3.StringUtils;

public class Main {
    public static void main(String[] args) {

        First first = new First();
        Second second = new Second();
        System.out.println("Main`s already worked.");
        System.out.println(StringUtils.upperCase("Main`s already worked."));

    }
}
