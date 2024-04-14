package ru.otus.hw01.dao;

import ru.otus.hw01.config.TestFileNameProvider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public record ReaderDaoImpl(TestFileNameProvider fileNameProvider) implements ReaderDao {

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