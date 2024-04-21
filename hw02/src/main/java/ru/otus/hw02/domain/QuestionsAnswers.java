package ru.otus.hw02.domain;

import java.util.List;

public record QuestionsAnswers(String text, List<Answer> answers) {
}
