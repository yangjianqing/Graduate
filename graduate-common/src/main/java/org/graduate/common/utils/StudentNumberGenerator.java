package org.graduate.common.utils;

import java.util.Random;

public class StudentNumberGenerator {
    public static String generateStudentNumber() {
        Random rand = new Random();
        int prefix = 222; // 学号前缀
        int suffix = rand.nextInt(90000) + 10000; // 生成8位随机数字
        return String.valueOf(prefix) + String.valueOf(suffix);
    }

    public static void main(String[] args) {
        String studentNumber = generateStudentNumber();
        System.out.println("Generated Student Number: " + studentNumber);
    }
}
