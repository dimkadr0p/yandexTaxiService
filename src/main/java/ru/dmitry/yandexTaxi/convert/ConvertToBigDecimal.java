package ru.dmitry.yandexTaxi.convert;

import java.math.BigDecimal;

public class ConvertToBigDecimal {

    private ConvertToBigDecimal() {

    }

    public static BigDecimal[] convert(String[] strings) {
        BigDecimal[] bigDecimals = new BigDecimal[strings.length];

        for (int i = 0; i < bigDecimals.length; i++) {
            bigDecimals[i] = new BigDecimal(strings[i]);
        }

        return bigDecimals;
    }
}
