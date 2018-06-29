package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Mock
    SimpleEmailService emailService;

    @Mock
    AdminConfig adminConfig;

    @Mock
    TrelloMapper trelloMapper;

    @Test
    public void shouldCreateNewCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1", "pos1", "desc1", "id1");
        TrelloCard trelloCard = new TrelloCard("name1", "pos1", "desc1", "id1");
        CreatedTrelloCardDto mappedTrelloCardDto = new CreatedTrelloCardDto("id2", "name2", "shortUrl");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(mappedTrelloCardDto);
        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);


        //When
       CreatedTrelloCardDto testedCard = trelloService.createTrelloCard(trelloMapper.mapToCard(trelloCardDto));

       //Then
        Assert.assertEquals("id2", testedCard.getId());
        Assert.assertEquals("name2", testedCard.getName());
    }

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "test_list2", false));

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("1", "test2", trelloListDto));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardsDto);

        //When
        List<TrelloBoardDto> testList = trelloClient.getTrelloBoards();

        //then
        Assert.assertNotNull(testList);
        Assert.assertEquals(1, testList.size());
    }
}