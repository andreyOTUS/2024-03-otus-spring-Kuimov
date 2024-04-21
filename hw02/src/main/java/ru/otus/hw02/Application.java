package ru.otus.hw02;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw02.service.TestRunnerService;

public class Application {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext("ru.otus.hw02");
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();

    }
}