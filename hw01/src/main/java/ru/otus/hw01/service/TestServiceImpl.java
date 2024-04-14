package ru.otus.hw01.service;

import lombok.AllArgsConstructor;
import ru.otus.hw01.dao.QuestionDao;
import ru.otus.hw01.domain.Question;

@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        for (Question q : questionDao.findAll()) {
            ioService.printLine(q.text());
            for (int i = 0; i < q.answers().size(); i++) {
                ioService.printFormattedLine("%d. %s", i + 1, q.answers().get(i).text());
            }
            ioService.printLine("");
        }
    }
}
