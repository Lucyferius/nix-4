package nix.oop.impl;

import nix.oop.CalculatorService;

import java.math.BigDecimal;

@Deprecated
public class NewCalcService implements CalculatorService {
    public NewCalcService() {
        System.out.println("Running version of calculator: NewCalcService\n");
    }
    public BigDecimal sum(BigDecimal a, BigDecimal b) {
        System.out.println("Add");
        return a.add(b);
    }

    public BigDecimal subtraction(BigDecimal a, BigDecimal b) {
        System.out.println("Sub");
        return a.subtract(b);
    }

    public BigDecimal multiplication(BigDecimal a, BigDecimal b) {
        System.out.println("Multiple");
        return a.multiply(b);
    }

    public BigDecimal division(BigDecimal a, BigDecimal b) {
        System.out.println("Div");
        return a.divide(b);
    }

    @Override
    public BigDecimal findMin(BigDecimal a, BigDecimal b) {
        return null;
    }

    @Override
    public BigDecimal findMax(BigDecimal a, BigDecimal b) {
        return null;
    }

    @Override
    public BigDecimal findAbs(BigDecimal a) {
        return null;
    }
}
