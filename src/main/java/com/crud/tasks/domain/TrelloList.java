package com.crud.tasks.domain;

public class TrelloList {

    private String id;
    private String name;
    private boolean isClosed;

    public TrelloList(String id, String name, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.isClosed = isClosed;
    }

    public TrelloList() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
