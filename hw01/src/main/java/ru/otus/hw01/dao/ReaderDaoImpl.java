package ru.otus.hw01.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw01.config.TestFileNameProvider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@AllArgsConstructor
@Data
public class ReaderDaoImpl implements ReaderDao {

    TestFileNameProvider fileNameProvider;

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