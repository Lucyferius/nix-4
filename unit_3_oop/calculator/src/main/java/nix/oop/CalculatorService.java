package nix.oop;

import java.math.BigDecimal;

public interface CalculatorService {

    BigDecimal sum(BigDecimal a, BigDecimal b);
    BigDecimal subtraction(BigDecimal a, BigDecimal b);
    BigDecimal multiplication (BigDecimal a, BigDecimal b);
    BigDecimal division (BigDecimal a, BigDecimal b);
    BigDecimal findMin(BigDecimal a, BigDecimal b);
    BigDecimal findMax(BigDecimal a, BigDecimal b);
    BigDecimal findAbs(BigDecimal a);
}
