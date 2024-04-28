package ru.otus.hw02.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.domain.QuestionsAnswers;
import ru.otus.hw02.exceptions.QuestionReadException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class CsvQuestionDaoTest {

    @Mock
    ReaderDao reader;
    private QuestionDao dao;

    @BeforeEach
    void setUp() {
        dao = new QuestionDaoCsv(reader, ';', 1);
    }

    @DisplayName("Должен выдать ошибку на пустой InputStream")
    @Test
    void shouldThrowQuestionReadExceptionException() {
        assertThrows(QuestionReadException.class, dao::findAll);
    }
    @Test
    void shouldThrowQuestionReadExceptionException2() {
        QuestionDaoCsv csvQuestionDao = new QuestionDaoCsv(reader, '-', 1);
        assertThrows(QuestionReadException.class, csvQuestionDao::findAll);
    }

    @Test
    void wellDone() {
        // prepare
        FIleProvider reader1 = new FIleProvider();
        QuestionDaoCsv csvQuestionDao = new QuestionDaoCsv(reader1,
                ';',
                1
        );

        // act
        List<QuestionsAnswers> questions = csvQuestionDao.findAll();

        // verify
        assertThat(questions).hasSize(3);
        assertThat(questions.stream().map(QuestionsAnswers::text)).containsExactly(
                "Is there life on Mars?",
                "How should resources be loaded form jar in Java?",
                "Which option is a good way to handle the exception?"
        );

        assertThat(questions.stream().map(QuestionsAnswers::answers).map(List::size)).containsExactly(
                3, 3, 4
        );

        assertThat(
                questions.stream()
                        .map(QuestionsAnswers::answers)
                        .map(answers -> answers.stream().filter(Answer::isCorrect).findFirst())
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(Answer::text)
        ).containsExactly(
                "Science doesn't know this yet",
                "ClassLoader#geResourceAsStream or ClassPathResource#getInputStream",
                "Rethrow with wrapping in business exception (for example, QuestionReadException)"
        );
    }
}