package ru.otus.hw02.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppProperties implements TestFileNameProvider {

    private String testFileName;

    @Override
    public String getTestFileName() {
        return testFileName;
    }
}
