package App;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        //TODO: refactor All code starting from DiaryTree.java
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        RunApp runApp = context.getBean(RunApp.class);
        runApp.run();}

}

