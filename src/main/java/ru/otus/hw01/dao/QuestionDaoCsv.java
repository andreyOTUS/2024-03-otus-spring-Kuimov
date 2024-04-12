package ru.otus.hw01.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw01.config.TestFileNameProvider;
import ru.otus.hw01.dao.dto.QuestionDto;
import ru.otus.hw01.domain.Question;
import ru.otus.hw01.exceptions.QuestionReadException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@RequiredArgsConstructor
public class QuestionDaoCsv implements QuestionDao {

    private final TestFileNameProvider fileNameProvider;

    private final char separator;

    private final int skipLines;

    @Override
    public List<Question> findAll() {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(fileNameProvider.getTestFileName());
             var streamReader = new InputStreamReader(inputStream);
             var reader = new BufferedReader(streamReader)) {
            var questionsList = new CsvToBeanBuilder<QuestionDto>(reader)
                    .withType(QuestionDto.class)
                    .withSkipLines(skipLines)
                    .withSeparator(separator)
                    .build()
                    .parse().stream()
                    .map(QuestionDto::toDomainObject)
                    .toList();
            return questionsList;
        } catch (Exception e) {
            throw new QuestionReadException(e.getMessage(), e);
        }
    }
}

