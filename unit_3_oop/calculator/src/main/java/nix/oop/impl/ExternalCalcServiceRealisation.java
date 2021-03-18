package nix.oop.impl;

import nix.oop.CalculatorService;

import java.math.BigDecimal;

public class ExternalCalcServiceRealisation implements CalculatorService {
    public ExternalCalcServiceRealisation(){
        System.out.println("Running version of calculator: ExternalCalcServiceRealisation\n");
    }

    public BigDecimal findMin(BigDecimal a, BigDecimal b) {
        return a.min(b);
    }

    public BigDecimal findMax(BigDecimal a, BigDecimal b) {
        return a.max(b);
    }

    public BigDecimal findAbs(BigDecimal a) {
        return a.abs();
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
}
