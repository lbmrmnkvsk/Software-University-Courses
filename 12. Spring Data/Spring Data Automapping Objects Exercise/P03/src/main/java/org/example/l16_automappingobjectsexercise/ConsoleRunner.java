package org.example.l16_automappingobjectsexercise;

import org.example.l16_automappingobjectsexercise.entities.Game;
import org.example.l16_automappingobjectsexercise.entities.User;
import org.example.l16_automappingobjectsexercise.services.GameService;
import org.example.l16_automappingobjectsexercise.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;

    public ConsoleRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        User loggedUser = null;

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\|");
            String command = tokens[0];

            if (command.startsWith("RegisterUser")) {
                System.out.println(this.userService.registerUser(tokens[1], tokens[2], tokens[3], tokens[4]));
            } else if (command.startsWith("LoginUser")) {
                User user = this.userService.findByEmail(tokens[1]);
                if (user == null || !user.getPassword().equals(tokens[2])) {
                    System.out.println("Incorrect username / password");
                } else {
                    loggedUser = user;
                    System.out.printf("Successfully logged in %s%n", user.getFullName());
                }
            } else if (command.startsWith("Logout")) {
                if (loggedUser == null) {
                    System.out.println("Cannot log out. No user was logged in.");
                } else {
                    System.out.printf("User %s successfully logged out%n", loggedUser.getFullName());
                }
            } else if (command.startsWith("AddGame")) {
                Game game = new Game();
                game.setTitle(tokens[1]);
                if (this.gameService.addGame(game)) {
                    System.out.printf("Added %s%n", game.getTitle());
                }
            } else if (command.startsWith("EditGame")) {
                if (this.gameService.editGame(Long.parseLong(tokens[1]))) {
                    System.out.printf("Edited %s%n", this.gameService.findById(Long.parseLong(tokens[1])).get().getTitle());
                }
            } else if (command.startsWith("DeleteGame")) {
                Long id = Long.parseLong(tokens[1]);
                Optional<Game> game = gameService.findById(id);
                if (game.isPresent()) {
                    if (gameService.deleteGame(id)) {
                        Game game1 = game.get();
                        System.out.printf("Deleted %s%n", game1.getTitle());
                    }
                }
            } else if (command.startsWith("AllGames")) {
                List<Game> games = this.gameService.getAllGames();
                games.forEach(g -> System.out.printf("%s %.2f%n", g.getTitle(), g.getPrice()));
            } else if (command.startsWith("DetailGame")) {
                Game game = this.gameService.findByTitle(tokens[1]);
                if (game != null) {
                    System.out.println("Title: " + game.getTitle());
                    System.out.println("Price: " + game.getPrice());
                    System.out.println("Description: " + game.getDescription());
                    System.out.println("Release date: " + game.getReleaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                } else {
                    System.out.println("Game not found.");
                }
            } else if (command.startsWith("OwnedGames")) {
                if (loggedUser != null) {
                    Set<Game> games = loggedUser.getGames();
                    if (!games.isEmpty()) {
                        games.forEach(g -> System.out.println(g.getTitle()));
                    }
                }
            }
        }
    }
}
