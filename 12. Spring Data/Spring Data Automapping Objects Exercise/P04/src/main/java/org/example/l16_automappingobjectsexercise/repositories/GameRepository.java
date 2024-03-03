package org.example.l16_automappingobjectsexercise.repositories;

import org.example.l16_automappingobjectsexercise.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByTitle(String title);
}
