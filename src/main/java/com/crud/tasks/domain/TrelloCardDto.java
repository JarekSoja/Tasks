package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloCardDto {

    private String name;
    private String pos;
    private String description;
    private String listId;
    private Badges badges;
    private int votes;
    private AttachmentsByType attachmentsByType;
    private Trello trello;
    private int board;
    private int card;
}
