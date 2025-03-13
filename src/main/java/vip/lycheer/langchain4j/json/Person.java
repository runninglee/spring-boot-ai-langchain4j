package vip.lycheer.langchain4j.json;

import jdk.jfr.Description;
import lombok.Data;

@Data
public class Person {

    @Description("年龄")
    private Integer age;

    @Description("体重")
    private Integer weight;

}
