package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto listDto1 = new TrelloListDto("1Dto", "test3", false);
        TrelloListDto listDto2 = new TrelloListDto("2Dto", "test4", true);
        List<TrelloListDto> listsDto = new ArrayList<>();
        List<TrelloListDto> listsDto2 = new ArrayList<>();
        listsDto.add(listDto1);
        listsDto2.add(listDto2);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "test DtoBoard", listsDto);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "test DtoBoard2", listsDto2);
        List<TrelloBoardDto> testList = new ArrayList<>();
        testList.add(trelloBoardDto);
        testList.add(trelloBoardDto2);
        //When
        List<TrelloBoard> testResult = trelloMapper.mapToBoards(testList);
        int numberOfBoards = testResult.size();
        String nameOfTestedList = testResult.get(1).getName();
        String idOfTestedBoard = testResult.get(0).getId();
        //Then
        Assert.assertEquals(2, numberOfBoards);
        Assert.assertEquals("2", nameOfTestedList);
        Assert.assertEquals("test DtoBoard", idOfTestedBoard);
    }


    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList list1 = new TrelloList("1Dto", "test3", false);
        TrelloList list2 = new TrelloList("2Dto", "test4", true);
        List<TrelloList> lists = new ArrayList<>();
        List<TrelloList> lists2 = new ArrayList<>();
        lists.add(list1);
        lists2.add(list2);
        TrelloBoard trelloBoard = new TrelloBoard("1", "test DtoBoard", lists);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "test DtoBoard2", lists2);
        List<TrelloBoard> testList = new ArrayList<>();
        testList.add(trelloBoard);
        testList.add(trelloBoard2);
        //When
        List<TrelloBoardDto> testResult = trelloMapper.mapToBoardsDto(testList);
        System.out.println(testResult.size());
        int numberOfBoards = trelloMapper.mapToBoardsDto(testList).size();
        String nameOfTestedList = testResult.get(1).getName();
        String idOfTestedBoard = testResult.get(0).getId();
        //Then
        Assert.assertEquals(2, numberOfBoards);
        Assert.assertEquals("2", nameOfTestedList);
        Assert.assertEquals("test DtoBoard", idOfTestedBoard);
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto listDto1 = new TrelloListDto("1Dto", "test3", false);
        TrelloListDto listDto2 = new TrelloListDto("2Dto", "test4", true);
        List<TrelloListDto> listsDto1 = new ArrayList<>();
        List<TrelloListDto> listsDto2 = new ArrayList<>();
        listsDto1.add(listDto1);
        listsDto2.add(listDto2);
        //When
        List<TrelloList> testList1 = trelloMapper.mapToList(listsDto1);
        List<TrelloList> testList2 = trelloMapper.mapToList(listsDto2);
        String testField1 = testList1.get(0).getName();
        boolean testField2 = testList2.get(0).isClosed();
        //Then
        Assert.assertEquals(testField1,"test3");
        Assert.assertEquals(1, testList1.size());
        Assert.assertEquals(true, testField2);

    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList list1 = new TrelloList("1Dto", "test3", false);
        TrelloList list2 = new TrelloList("2Dto", "test4", true);
        List<TrelloList> lists1 = new ArrayList<>();
        List<TrelloList> lists2 = new ArrayList<>();
        lists1.add(list1);
        lists2.add(list2);
        //When
        List<TrelloListDto> testList1 = trelloMapper.mapToListDto(lists1);
        List<TrelloListDto> testList2 = trelloMapper.mapToListDto(lists2);
        String testField1 = testList1.get(0).getName();
        boolean testField2 = testList2.get(0).isClosed();
        //Then
        Assert.assertEquals(testField1,"test3");
        Assert.assertEquals(1, testList1.size());
        Assert.assertEquals(true, testField2);
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("test1", "2", "teeest", "44");
        //When
        TrelloCard card = trelloMapper.mapToCard(cardDto);
        String testName = card.getName();
        String testDesc = card.getDescription();
        //Then
        Assert.assertEquals("test1", testName);
        Assert.assertEquals("teeest", testDesc);
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard card = new TrelloCard("test66", "332", "teeest", "44");
        //When
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);
        String testName = cardDto.getName();
        String testDesc = cardDto.getDescription();
        //Then
        Assert.assertEquals("test1", testName);
        Assert.assertEquals("teeest", testDesc);
    }


}