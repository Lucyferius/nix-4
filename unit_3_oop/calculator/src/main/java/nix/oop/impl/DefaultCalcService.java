package nix.oop.impl;


import nix.oop.CalculatorService;

import java.math.BigDecimal;

@Deprecated
public class DefaultCalcService implements CalculatorService {
    public DefaultCalcService() {
        System.out.println("Running version of calculator: DefaultCalculatorService\n");
    }

    public BigDecimal sum(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    public BigDecimal subtraction(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    public BigDecimal multiplication(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    public BigDecimal division(BigDecimal a, BigDecimal b) {
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
