package org.graduate.common.utils;

import java.util.Random;

public class NumberGenerator {
    public static String generateCode(String prefix, int length) {
        StringBuilder code = new StringBuilder(prefix);

        Random random = new Random();
        while (code.length() < length) {
            int randomDigit = random.nextInt(10);
            code.append(randomDigit);
        }

        return code.toString();
    }

    public static void main(String[] args) {
        String generatedCode = NumberGenerator.generateCode("TG", 5);
        System.out.println(generatedCode); // 输出类似 "TG236" 的编号
    }
}

