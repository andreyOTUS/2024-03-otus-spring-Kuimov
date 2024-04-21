package ru.otus.hw02.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestResult {
    private final Student student;

    private final List<QuestionsAnswers> answeredQuestionsAnswers;

    private int rightAnswersCount;

    public TestResult(Student student) {
        this.student = student;
        this.answeredQuestionsAnswers = new ArrayList<>();
    }

    public void applyAnswer(QuestionsAnswers questionsAnswers, boolean isRightAnswer) {
        answeredQuestionsAnswers.add(questionsAnswers);
        if (isRightAnswer) {
            rightAnswersCount++;
        }
    }
}
