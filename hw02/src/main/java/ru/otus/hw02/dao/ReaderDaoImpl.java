package ru.otus.hw02.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.otus.hw02.config.TestFileNameProvider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class ReaderDaoImpl implements ReaderDao {
    private final TestFileNameProvider fileNameProvider;

    public ReaderDaoImpl(TestFileNameProvider fileNameProvider) {
        this.fileNameProvider = fileNameProvider;
    }

    public InputStream inputStream() {
        return getClass().getClassLoader().getResourceAsStream(fileNameProvider.getTestFileName());
    }

    public InputStreamReader streamReader() {
        return new InputStreamReader(this.inputStream());
    }

    @Override
    public Reader reader() {
        return new BufferedReader(this.streamReader());
    }

}