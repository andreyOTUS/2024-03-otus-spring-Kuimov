package ru.otus.hw01.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.hw01.config.TestFileNameProvider;
import ru.otus.hw01.domain.Question;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        // Использовать CsvToBean
        // https://opencsv.sourceforge.net/#collection_based_bean_fields_one_to_many_mappings
        // Использовать QuestionReadException
        // Про ресурсы: https://mkyong.com/java/java-read-a-file-from-resources-folder/

        return new ArrayList<>();
    }
}