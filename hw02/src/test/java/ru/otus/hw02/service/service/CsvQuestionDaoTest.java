package ru.otus.hw02.service.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw02.config.AppProperties;
import ru.otus.hw02.config.TestFileNameProvider;
import ru.otus.hw02.dao.QuestionDao;
import ru.otus.hw02.dao.QuestionDaoCsv;
import ru.otus.hw02.dao.ReaderDao;
import ru.otus.hw02.dao.ReaderDaoImpl;

@DisplayName("Тест QuestionDaoCsv")
@Disabled
public class CsvQuestionDaoTest {


    @Test
    public void parsingFileTest() {
        TestFileNameProvider testFileNameProvider = new AppProperties("studentsTest.csv");
        ReaderDao readerDao = new ReaderDaoImpl(testFileNameProvider);
        QuestionDao questionDao = new QuestionDaoCsv(readerDao, ';', 1);
        Assertions.assertEquals(3, questionDao.findAll().size());
    }
}

