package org.example.l16_automappingobjectsexercise.services;

import org.example.l16_automappingobjectsexercise.entities.Game;
import org.example.l16_automappingobjectsexercise.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game findByTitle(String title) {
        return this.gameRepository.findByTitle(title);
    }

    @Override
    public boolean addGame(Game game) {
        this.gameRepository.save(game);
        return true;
    }

    @Override
    public boolean editGame(Long id) {
        return this.gameRepository.findById(id).isPresent();
    }

    @Override
    public boolean deleteGame(Long id) {
        Optional<Game> game = this.gameRepository.findById(id);
        if (game.isPresent()) {
            this.gameRepository.delete(game.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Game> getAllGames() {
        return this.gameRepository.findAll();
    }

    @Override
    public Optional<Game> findById(Long id) {
        return this.gameRepository.findById(id);
    }
}
