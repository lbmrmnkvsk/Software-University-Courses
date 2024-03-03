package org.example.l16_automappingobjectsexercise.services;

import org.example.l16_automappingobjectsexercise.entities.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    Game findByTitle(String title);
    boolean addGame(Game game);
    boolean editGame(Long id);
    boolean deleteGame(Long id);

    Optional<Game> findById(Long id);

    List<Game> getAllGames();
}
