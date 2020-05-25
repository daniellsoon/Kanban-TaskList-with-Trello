package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        //Given
        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(new TrelloListDto());
        listDto.add(new TrelloListDto());
        listDto.add(new TrelloListDto());

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1","name1" , listDto);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2","name2" , listDto);

        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        boardDtoList.add(trelloBoardDto1);
        boardDtoList.add(trelloBoardDto2);

        //When
        List<TrelloBoard> mappedList = trelloMapper.mapToBoards(boardDtoList);
        TrelloBoard mappedBoard = mappedList.get(0);

        //Then
        assertEquals(mappedList.size(), boardDtoList.size());
        assertEquals(trelloBoardDto1.getId(), mappedBoard.getId());
        assertEquals(trelloBoardDto1.getName(), mappedBoard.getName());
        assertEquals(trelloBoardDto1.getLists().size(), mappedBoard.getLists().size());
    }

    @Test
    public void mapToBoardsDtoTest() {
        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("1" , "name1" , true));
        list.add(new TrelloList("2" , "name2" , true));
        list.add(new TrelloList("3" , "name3" , true));

        TrelloBoard trelloBoard1 = new TrelloBoard("1","name1" , list);
        TrelloBoard trelloBoard2 = new TrelloBoard("2","name2" , list);

        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(trelloBoard1);
        boardList.add(trelloBoard2);

        //When
        List<TrelloBoardDto> mappedList = trelloMapper.maptoBoardsDto(boardList);
        TrelloBoardDto mappedBoard = mappedList.get(0);

        //Then
        assertEquals(mappedList.size(), boardList.size());
        assertEquals(trelloBoard1.getId(), mappedBoard.getId());
        assertEquals(trelloBoard1.getName(), mappedBoard.getName());
        assertEquals(trelloBoard1.getLists().size(), mappedBoard.getLists().size());
    }

    @Test
    public void mapToListTest() {
        //Given
        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(new TrelloListDto());
        listDto.add(new TrelloListDto());
        listDto.add(new TrelloListDto());

        //When
        List<TrelloList> mappedList = trelloMapper.mapToList(listDto);

        //Then
        assertEquals(mappedList.size(), listDto.size());
    }

    @Test
    public void mapToListDtoTest() {
        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("1" , "name1" , true));
        list.add(new TrelloList("2" , "name2" , true));
        list.add(new TrelloList("3" , "name3" , true));

        //When
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(list);

        //Then
        assertEquals(mappedList.size(), list.size());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("1", "name", "url", "badges");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("1", "name", "url", "badges");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
        assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCard.getName(), trelloCardDto.getName());
        assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
    }
}
