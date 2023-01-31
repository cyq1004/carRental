package com.yeqifu.stat.domain;

import lombok.Data;

@Data
public class BaseEntity {

    private String name;
    private Double value;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
