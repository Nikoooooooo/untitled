package spring3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring3.system.Container;
import spring3.user.Student;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac=new AnnotationConfigApplicationContext(Config.class);
        Container c= (Container) ac.getBean("container");

        c.add(new Student("张三",1.7,75));
        c.add(new Student("李四",1.5,85));
        c.add(new Student("王五",0.5,80));
        c.add(new Student("李六",1.8,890));
        System.out.println(c.getMax());
        System.out.println(c.getMin());
        System.out.println(c.getAvg());
        System.out.println(c.size());
    }
}
