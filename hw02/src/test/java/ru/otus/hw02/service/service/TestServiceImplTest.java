package ru.otus.hw02.service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw02.dao.QuestionDao;
import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.domain.QuestionsAnswers;
import ru.otus.hw02.domain.Student;
import ru.otus.hw02.domain.TestResult;
import ru.otus.hw02.service.IOService;
import ru.otus.hw02.service.TestServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatList;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    @Mock
    private QuestionDao dao;
    @Mock
    private IOService ioService;

    private TestServiceImpl testService;

    @BeforeEach
    void setUp() {
        testService = new TestServiceImpl(ioService, dao);
    }

    @DisplayName("Для пустого пользователя не должно быть никаких результатов")
    @Test
    void shouldNotHaveResultsForNullStudent() {
        TestResult testResult = testService.executeTestFor(new Student("1","1"));
        assertThat(testResult).isNotNull();
        assertThat(testResult.getStudent()).isNotNull();
        assertThatList(testResult.getAnsweredQuestionsAnswers()).isEmpty();
        assertThat(testResult.getRightAnswersCount()).isEqualTo(0);
    }


    @DisplayName("Для пустого пользователя не должно быть никаких результатов")
    @Test
    void shouldAskOneQuestion() {
        given(dao.findAll()).willReturn(List.of(new QuestionsAnswers("Test question",
                List.of(new Answer("HI", false)))));

        Student student = new Student("Name", "Surname");
        TestResult testResult = testService.executeTestFor(student);

        System.out.println(dao.findAll());

        assertThat(testResult).isNotNull();
        assertThat(testResult.getStudent()).isNotNull().isEqualTo(student);
        assertThatList(testResult.getAnsweredQuestionsAnswers()).isNotEmpty();
        assertThat(testResult.getAnsweredQuestionsAnswers().size()).isEqualTo(1);
        assertThat(testResult.getRightAnswersCount()).isEqualTo(1);
    }
}