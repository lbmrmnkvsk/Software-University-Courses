package magicGame.repositories.interfaces;

import magicGame.common.ExceptionMessages;
import magicGame.models.magicians.Magician;

import java.util.ArrayList;
import java.util.Collection;

public class MagicianRepositoryImpl implements MagicianRepository{
    private Collection<Magician> data;

    public MagicianRepositoryImpl() {
        this.data = new ArrayList<>();
    }

    @Override
    public Collection<Magician> getData() {
        return this.data;
    }

    @Override
    public void addMagician(Magician model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_MAGICIAN_REPOSITORY);
        }
        this.data.add(model);
    }

    @Override
    public boolean removeMagician(Magician model) {
        return this.data.remove(model);
    }

    @Override
    public Magician findByUsername(String name) {
        for (Magician currentMagician : this.data) {
            if (currentMagician.getUsername().equals(name)) {
               return currentMagician;
            }
        }
        return null;
    }
}
