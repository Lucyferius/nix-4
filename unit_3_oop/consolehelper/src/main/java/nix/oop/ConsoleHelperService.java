package nix.oop;

import java.io.IOException;
import java.math.BigDecimal;

public interface ConsoleHelperService {
    void writeMessage(String message);
    void writeResult(BigDecimal message);
    String readString() throws IOException;
    int readInteger() throws IOException;
    BigDecimal readDecimal() throws IOException;
}
