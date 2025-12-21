package com.raj.demo;

// java
public class ShortVsIntMemory {
    static void main(String[] args) throws Exception {
        int i=20;
        i+=2;
        i |=101;
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
    }
}
