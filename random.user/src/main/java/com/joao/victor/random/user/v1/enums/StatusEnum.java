package com.joao.victor.random.user.v1.enums;

public enum StatusEnum {

    trash(0, "trash"),
    published(1, "published");

    private Integer cod;

    private String description;

    StatusEnum(Integer cod, String description) {
        this.cod = cod;
        this.setDescription(description);
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
