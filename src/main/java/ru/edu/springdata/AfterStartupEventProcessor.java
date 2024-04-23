package ru.edu.springdata;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AfterStartupEventProcessor {

    private final JdbcTemplate jdbcTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        jdbcTemplate.execute("CREATE TABLE book(id SERIAL, name VARCHAR(255), category VARCHAR(255), language VARCHAR(255));");
        jdbcTemplate.execute("insert into book (name, category, language) values ('Капитал', 'Философия', 'Русский');");
        jdbcTemplate.execute("insert into book (name, category, language) values ('Молот ведьм', 'Религия', 'Английсткий');");
    }
}
