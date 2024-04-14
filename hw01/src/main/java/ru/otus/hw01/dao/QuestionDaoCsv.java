package ru.otus.hw01.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw01.dao.dto.QuestionDto;
import ru.otus.hw01.domain.Question;
import ru.otus.hw01.exceptions.QuestionReadException;

import java.util.List;

@RequiredArgsConstructor
public class QuestionDaoCsv implements QuestionDao {

    private final ReaderDao reader;

    private final char separator;

    private final int skipLines;

    @Override
    public List<Question> findAll() {
        try {
            var questionsList = new CsvToBeanBuilder<QuestionDto>(reader.reader())
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