package hello.core;


import lombok.Getter;
import lombok.Setter;

@Getter //getter setter 자동으로 만들어줌
@Setter
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args){
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdf");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
