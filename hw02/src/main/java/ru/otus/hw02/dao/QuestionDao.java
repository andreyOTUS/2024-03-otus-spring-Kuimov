package ru.otus.hw02.dao;

import ru.otus.hw02.domain.QuestionsAnswers;

import java.util.List;

public interface QuestionDao {
    List<QuestionsAnswers> findAll();
}

