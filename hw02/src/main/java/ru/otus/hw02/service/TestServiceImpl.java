package ru.otus.hw02.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw02.dao.QuestionDao;
import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.domain.Student;
import ru.otus.hw02.domain.TestResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private static final String QUESTION_FOR_ANSWER = "What is the correct answer? Enter the number: ";

    private static final String ANSWER = "Upss...Incorrect number. Try one more!";

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var questionsAnswers : questions) {
            List<Answer> answers = questionsAnswers.answers();
            ioService.printLine(questionsAnswers.text());
            int correctAnswer = 0;
            for (int i = 0; i < answers.size(); i++) {
                Answer answer = answers.get(i);
                ioService.printFormattedLine("%d. %s", i + 1, answer.text());
                if (answer.isCorrect()) {
                    correctAnswer = i + 1;
                }
            }
            int number = ioService.readIntForRangeWithPrompt(1, answers.size(), QUESTION_FOR_ANSWER, ANSWER);
            boolean isAnswerValid; // Задать вопрос, получить ответ
            isAnswerValid = number == correctAnswer;
            testResult.applyAnswer(questionsAnswers, isAnswerValid);
        }
        return testResult;
    }
}
