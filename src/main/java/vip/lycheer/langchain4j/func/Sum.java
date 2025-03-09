package vip.lycheer.langchain4j.func;

import lombok.Data;

@Data
public class Sum implements Runnable {

    private int a;

    private int b;

    @Override
    public void run() {
        System.out.println(a + b);
    }
}

