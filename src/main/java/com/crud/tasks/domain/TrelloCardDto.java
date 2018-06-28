package com.crud.tasks.domain;

public class TrelloCardDto {

    private String name;
    private String pos;
    private String description;
    private String listId;

    public TrelloCardDto(String name, String description, String pos, String listId) {
        this.name = name;
        this.description = description;
        this.pos = pos;
        this.listId = listId;
    }

    public TrelloCardDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return pos;
    }

    public String getDescription() {
        return description;
    }

    public String getListId() {
        return listId;
    }
}
