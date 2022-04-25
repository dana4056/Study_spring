package hello.core.singleton;

public class SingletonService {

    //자기 자신을 내부에 private, static으로 가지고 있음(클래스 레벨에 올라가 딱 하나만 존재함)
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance; //SingletonService 객체의 참조를 꺼낼 수 있는건 얘밖에 없음
    }

    private SingletonService(){
        //생성자를 private으로 선언해서 다른 인스턴스가 생성되는거 방지지
   }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
