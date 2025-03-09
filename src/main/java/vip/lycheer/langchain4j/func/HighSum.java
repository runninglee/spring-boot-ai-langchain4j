package vip.lycheer.langchain4j.func;

import dev.langchain4j.agent.tool.Tool;

public class HighSum {

    @Tool("计算两数之和")
    public int sum(int a, int b) {
        System.out.println(a);
        System.out.println(b);
        return a + b;
    }

    @Tool("计算两数之差")
    public int sub(int a, int b) {
        return a - b;
    }
}

