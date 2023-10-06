import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BoardImpl implements Board {
    private List<Card> cards;

    public BoardImpl() {
        this.cards = new ArrayList<>();
    }

    @Override
    public void draw(Card card) {
        if (this.contains(card.getName())) {
            throw new IllegalArgumentException();
        }

        this.cards.add(card);
    }

    @Override
    public Boolean contains(String name) {
        Card card = this.cards.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
        return card != null;
    }

    @Override
    public int count() {
        return this.cards.size();
    }

    @Override
    public void play(String attackerCardName, String attackedCardName) {
        Card attacker = this.cards.stream().filter(c -> c.getName().equals(attackerCardName)).findFirst().orElse(null);
        Card attacked = this.cards.stream().filter(c -> c.getName().equals(attackedCardName)).findFirst().orElse(null);
        if (attacked == null || attacker == null) {
            throw new IllegalArgumentException();
        }

        if (attacker.getLevel() != attacked.getLevel()) {
            throw new IllegalArgumentException();
        }

        if (attacked.getHealth() > 0) {
            attacked.setHealth(attacked.getHealth() - attacker.getDamage());
            if (attacked.getHealth() <= 0) {
                attacker.setScore(attacker.getScore() + attacked.getLevel());
            }
        }
    }

    @Override
    public void remove(String name) {
        Card card = this.cards.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
        if (card == null) {
            throw new IllegalArgumentException();
        }

        this.cards.remove(card);
    }

    @Override
    public void removeDeath() {
        this.cards.removeIf(c -> c.getHealth() <= 0);
    }

    @Override
    public Iterable<Card> getBestInRange(int start, int end) {
        return this.cards.stream().filter(c -> c.getScore() >= start && c.getScore() <= end)
                .sorted(Comparator.comparing(Card::getLevel).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Card> listCardsByPrefix(String prefix) {
        return this.cards.stream().filter(c -> c.getName().startsWith(prefix))
                .sorted(Comparator.comparing((Card c) -> new StringBuilder(c.getName()).reverse())
                        .thenComparing(Card::getLevel))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Card> searchByLevel(int level) {
        return this.cards.stream().filter(c -> c.getLevel() == level)
                .sorted(Comparator.comparing(Card::getScore).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void heal(int health) {
        Card card = this.cards.stream().min(Comparator.comparing(Card::getHealth)).orElse(null);
        if (card == null) {
            throw new IllegalArgumentException();
        }

        card.setHealth(card.getHealth() + health);
    }
}
