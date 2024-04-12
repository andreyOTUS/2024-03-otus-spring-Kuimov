package ru.otus.hw01.service.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw01.config.AppProperties;
import ru.otus.hw01.config.TestFileNameProvider;
import ru.otus.hw01.dao.QuestionDao;
import ru.otus.hw01.dao.QuestionDaoCsv;

@DisplayName("Тест QuestionDaoCsv")
public class CsvQuestionDaoTest {

    @Test
    public void parsingFileTest() {
        TestFileNameProvider testFileNameProvider = new AppProperties("studentsTest.csv");
        QuestionDao questionDao = new QuestionDaoCsv(testFileNameProvider, ';', 1);
        Assertions.assertEquals(3, questionDao.findAll().size());
    }
}

