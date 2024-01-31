package magicGame.core;

import magicGame.common.ExceptionMessages;
import magicGame.common.OutputMessages;
import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;
import magicGame.models.magics.BlackMagic;
import magicGame.models.magics.Magic;
import magicGame.models.magics.RedMagic;
import magicGame.models.region.Region;
import magicGame.models.region.RegionImpl;
import magicGame.repositories.interfaces.MagicRepositoryImpl;
import magicGame.repositories.interfaces.MagicianRepositoryImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ControllerImpl implements  Controller {
    private MagicRepositoryImpl magics;
    private MagicianRepositoryImpl magicians;
    private Region region;

    public ControllerImpl() {
        this.magics = new MagicRepositoryImpl();
        this.magicians = new MagicianRepositoryImpl();
        this.region = new RegionImpl();
    }

    @Override
    public String addMagic(String type, String name, int bulletsCount) {
        if (!type.equals("RedMagic") && !type.equals("BlackMagic")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGIC_TYPE);
        }

        Magic magic = null;
        if (type.equals("RedMagic")) {
            magic = new RedMagic(name, bulletsCount);
        } else if (type.equals("BlackMagic")) {
            magic = new BlackMagic(name, bulletsCount);
        }

        magics.addMagic(magic);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_MAGIC, name);
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        Magic magic = this.magics.findByName(magicName);
        if (magic == null) {
            throw new NullPointerException(ExceptionMessages.MAGIC_CANNOT_BE_FOUND);
        }

        if (!type.equals("Wizard") && !type.equals("BlackWidow")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_TYPE);
        }

        Magician magician = null;
        if (type.equals("Wizard")) {
            magician = new Wizard(username, health, protection, magic);
        } else if (type.equals("BlackWidow")) {
            magician = new BlackWidow(username, health, protection, magic);
        }

        this.magicians.addMagician(magician);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_MAGICIAN, username);
    }

    @Override
    public String startGame() {
        Collection<Magician> aliveMagicians = this.magicians.getData().stream().filter(m -> m.getHealth() > 0).collect(Collectors.toList());
        return this.region.start(aliveMagicians);
    }

    @Override
    public String report() {
        Collection<Magician> sortedMagicians = this.magicians.getData().stream().sorted(Comparator.comparing(Magician::getHealth)
                .thenComparing(Magician::getUsername)).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();

        for (Magician magician : sortedMagicians) {
            int health = magician.getHealth();
            if (health < 0) {
                health = 0;
            }

            int protection = magician.getProtection();
            if (protection < 0) {
                protection = 0;
            }

            sb.append(String.format("%s: %s", magician.getClass().getSimpleName(), magician.getUsername())).append(System.lineSeparator());
            sb.append(String.format("Health: %d", health)).append(System.lineSeparator());
            sb.append(String.format("Protection: %d", protection)).append(System.lineSeparator());
            sb.append(String.format("Magic: %s", magician.getMagic().getName())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
