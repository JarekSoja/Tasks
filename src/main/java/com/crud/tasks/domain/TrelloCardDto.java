package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TrelloCardDto {

    private String name;
    private String pos;
    private String description;
    private String listId;
//    private Badges badges = new Badges();
//    private int votes = 2;
//    private AttachmentsByType attachmentsByType = new AttachmentsByType();
//    private Trello trello = new Trello();
//    private int board = 0;
//    private int card = 0;
}
