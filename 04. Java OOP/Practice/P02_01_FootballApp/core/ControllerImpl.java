package football.core;


import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private SupplementRepository supplement;
    private Collection<Field> fields;

    public ControllerImpl() {
        this.supplement = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        if (!fieldType.equals("ArtificialTurf") && !fieldType.equals("NaturalGrass")) {
            throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }

        Field field = null;
        if (fieldType.equals("ArtificialTurf")) {
            field = new ArtificialTurf(fieldName);
        } else if (fieldType.equals("NaturalGrass")) {
            field = new NaturalGrass(fieldName);
        }

        this.fields.add(field);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        if (!type.equals("Powdered") && !type.equals("Liquid")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }

        Supplement supplement1 = null;
        if (type.equals("Powdered")) {
            supplement1 = new Powdered();
        } else if (type.equals("Liquid")) {
            supplement1 = new Liquid();
        }

        this.supplement.add(supplement1);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Supplement supplement1 = this.supplement.findByType(supplementType);
        if (supplement1 == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        }

        Field field = this.fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        field.addSupplement(supplement1);
        this.supplement.remove(supplement1);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        if (!playerType.equals("Men") && !playerType.equals("Women")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

        Field field = this.fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        Player player = null;
        if (playerType.equals("Men")) {
            player = new Men(playerName, nationality, strength);
        } else if (playerType.equals("Women")) {
            player = new Women(playerName, nationality, strength);
        }

        if ((playerType.equals("Men") && field.getClass().getSimpleName().equals("NaturalGrass")) ||
                (playerType.equals("Women") && field.getClass().getSimpleName().equals("ArtificialTurf"))) {
            field.addPlayer(player);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
        } else {
            return ConstantMessages.FIELD_NOT_SUITABLE;
        }
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = this.fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        field.drag();
        return String.format(ConstantMessages.PLAYER_DRAG, field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = this.fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        int sum = 0;

        for (Player player : field.getPlayers()) {
            sum += player.getStrength();
        }

        return String.format(ConstantMessages.STRENGTH_FIELD, fieldName, sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        for (Field field : this.fields) {
            sb.append(field.getInfo()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
