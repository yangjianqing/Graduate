package org.graduate.util;

import java.util.Random;

public class MyClass {
    public static String generateCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + 1000; // TODO: 生成1000到9999之间的4位整数
        String codeStr = String.valueOf(code);
        return codeStr;
    }
}
