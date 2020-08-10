package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.facade.TrelloFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloFacade trelloFacade;

    @GetMapping(value = "/boards")
    public List<TrelloBoardDto> getTrelloBoards() {
//        trelloBoards
//                .stream()
//                .filter(e -> !e.getId().isEmpty() && !e.getName().isEmpty() && e.getName().contains("Kodilla"))
//                .collect(Collectors.toList());
        return trelloFacade.fetchTrelloBoards();
    }

    @PostMapping(value = "/cards")
    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }

}