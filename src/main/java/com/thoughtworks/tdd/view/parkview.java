package com.thoughtworks.tdd.view;

import java.io.IOException;
import java.util.Scanner;

public class parkview {

    public int showBegin() throws IOException {
        System.out.println("1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进行的操作：");
        Scanner in= new Scanner(System.in);
        return Integer.parseInt(in.next());
    }
}
