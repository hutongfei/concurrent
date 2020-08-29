package com.my.api;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.nio.CharBuffer;

public class PipeDemo {

    public static void main(String[] args) {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();

        try {
            out.connect(in);
            out.write("this is Main Thread Hello,Word!");
            new Thread(new PipeThread(in)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class PipeThread implements Runnable{

    PipedReader reader;

    public PipeThread(PipedReader reader) {
        this.reader = reader;
    }
    @SneakyThrows
    @Override
    public void run() {
        int i = 0;
        try {
            while ((i = reader.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (Exception e) {

        }
    }
}
