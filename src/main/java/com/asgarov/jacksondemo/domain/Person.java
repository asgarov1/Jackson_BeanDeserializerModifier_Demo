package com.asgarov.jacksondemo.domain;

import com.asgarov.jacksondemo.config.NameSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Person {
    private int id;

    @JsonDeserialize(using = NameSerializer.class)
    private String name;

    private String email;
}
