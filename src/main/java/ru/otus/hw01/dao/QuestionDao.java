package ru.otus.hw01.dao;

import ru.otus.hw01.domain.Question;
import ru.otus.hw01.exceptions.QuestionReadException;

import java.util.List;

public interface QuestionDao {
//    List<Question> findAll() throws QuestionReadException;
List<Question> findAll();
}

