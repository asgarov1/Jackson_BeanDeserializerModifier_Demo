package com.asgarov.jacksondemo.serializer;

import com.asgarov.jacksondemo.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NameSerializerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test() throws JsonProcessingException {
        Person person = objectMapper.readValue(
                "{\"id\":1,\"name\":\"Alfred\",\"email\":\"admin@admin<script></script>.com\"}",
                Person.class);
        assertEquals(1, person.getId());
        assertEquals("Mr/Mrs Alfred", person.getName());
        assertEquals("admin@admin.com", person.getEmail());
    }
}