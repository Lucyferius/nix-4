package nix.alevel;

import java.io.IOException;
import java.math.BigDecimal;

public interface ConsoleHelperService {
    void writeMessage(String message);
    String readString() throws IOException;
    int readInteger()throws IOException;
}
