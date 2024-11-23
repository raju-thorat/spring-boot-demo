package com.raj.demo.dsa.stack;

public class MyStack<T> {
    private Object[] dataArr;

    //This will be at index 0 initially for empty stack
    private int top = 0;

    public MyStack(int maxSize) {
        this.dataArr = (T[]) new Object[maxSize];
    }

    public boolean push(T element) {
        if (top < dataArr.length) {
            this.dataArr[top] = element;
            top++;
            return true;
        }
        return false;
    }

    public T pop() {
        if (top >= 0) {
            Object o = this.dataArr[top];
            this.dataArr[top] = null;
            top--;
            return (T) o;
        }
        return null;
    }
}
