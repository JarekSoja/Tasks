package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloMapper trelloMapper;

    private static final String SUBJECT = "Tasks: New Trello Card";

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCard trelloCard) {
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,"New card" + card.getName() + " has been created on your Trello account.")));
        return newCard;
    }


}
