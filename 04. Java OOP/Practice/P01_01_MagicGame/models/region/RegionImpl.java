package magicGame.models.region;

import magicGame.common.OutputMessages;
import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RegionImpl implements Region {
    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> wizards = magicians.stream().filter(m -> m instanceof Wizard && m.getHealth() > 0).collect(Collectors.toList());
        List<Magician> blackWidows = magicians.stream().filter(m -> m instanceof BlackWidow && m.getHealth() >0).collect(Collectors.toList());

        while (!wizards.isEmpty() && !blackWidows.isEmpty()) {
            Magician wizard = wizards.get(0);
            Magician blackWidow = blackWidows.get(0);

            blackWidow.takeDamage(wizard.getMagic().fire());

            if (!blackWidow.isAlive()) {
                blackWidows.remove(blackWidow);
            } else {
                wizard.takeDamage(blackWidow.getMagic().fire());
                if (!wizard.isAlive()) {
                    wizards.remove(wizard);
                }
            }
        }

        if (wizards.size() > blackWidows.size()) {
            return "Wizards win!";
        } else  {
            return "Black widows win!";
        }

    }
}
