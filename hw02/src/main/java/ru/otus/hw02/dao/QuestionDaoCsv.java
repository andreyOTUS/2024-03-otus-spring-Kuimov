package ru.otus.hw02.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import ru.otus.hw02.dao.dto.QuestionDto;
import ru.otus.hw02.domain.QuestionsAnswers;
import ru.otus.hw02.exceptions.QuestionReadException;

import java.util.List;

@Repository
@PropertySource("application.properties")
public class QuestionDaoCsv implements QuestionDao {

    private final ReaderDao reader;

    private final char separator;

    private final int skipLines;

    public QuestionDaoCsv(@Autowired ReaderDao reader,
                          @Value("${csv.separator}") char separator,
                          @Value("${csv.skipLines}") int skipLines) {

        this.reader = reader;
        this.separator = separator;
        this.skipLines = skipLines;
    }

    @Override
    public List<QuestionsAnswers> findAll() {
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