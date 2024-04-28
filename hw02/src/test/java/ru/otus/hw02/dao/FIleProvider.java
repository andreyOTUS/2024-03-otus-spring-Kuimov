package ru.otus.hw02.dao;

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@RequiredArgsConstructor
public class FIleProvider implements ReaderDao {

    public InputStream inputStream() {
        return getClass().getClassLoader().getResourceAsStream("studentsTest.csv");
    }

    public InputStreamReader streamReader() {
        return new InputStreamReader(this.inputStream());
    }

    @Override
    public Reader reader() {
        return new BufferedReader(this.streamReader());
    }
}
