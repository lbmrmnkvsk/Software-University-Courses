package christmasPastryShop.core;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.common.OutputMessages;
import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.OpenBooth;
import christmasPastryShop.entities.booths.PrivateBooth;
import christmasPastryShop.entities.cocktails.Hibernation;
import christmasPastryShop.entities.cocktails.MulledWine;
import christmasPastryShop.entities.delicacies.Gingerbread;
import christmasPastryShop.entities.delicacies.Stolen;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

public class ControllerImpl implements Controller {
    private DelicacyRepository<Delicacy> delicacyRepository;
    private CocktailRepository<Cocktail> cocktailRepository;
    private BoothRepository<Booth> boothRepository;
    private double totalIncome;

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
        this.totalIncome = 0;
    }


    @Override
    public String addDelicacy(String type, String name, double price) {
        if (this.delicacyRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST, type, name));
        }

        Delicacy delicacy = null;
        if (type.equals("Gingerbread")) {
            delicacy = new Gingerbread(name, price);
        } else if (type.equals("Stolen")) {
            delicacy = new Stolen(name, price);
        }

        this.delicacyRepository.add(delicacy);
        return String.format(OutputMessages.DELICACY_ADDED, name, type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
       if (this.cocktailRepository.getByName(name) != null) {
           throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST, type, name));
       }

       Cocktail cocktail = null;
       if (type.equals("Hibernation")) {
           cocktail = new Hibernation(name, size, brand);
       } else if (type.equals("MulledWine")) {
           cocktail = new MulledWine(name, size, brand);
       }

       this.cocktailRepository.add(cocktail);
       return String.format(OutputMessages.COCKTAIL_ADDED, name, brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
      if (this.boothRepository.getByNumber(boothNumber) != null) {
          throw new IllegalArgumentException(String.format(ExceptionMessages.BOOTH_EXIST, boothNumber));
      }

      Booth booth = null;
      if (type.equals("OpenBooth")) {
          booth = new OpenBooth(boothNumber, capacity);
      } else if (type.equals("PrivateBooth")) {
          booth = new PrivateBooth(boothNumber, capacity);
      }

      this.boothRepository.add(booth);
      return String.format(OutputMessages.BOOTH_ADDED, boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
       Booth booth = this.boothRepository.getAll().stream().filter(b -> !b.isReserved() && b.getCapacity() >= numberOfPeople)
               .findFirst().orElse(null);

       if (booth == null) {
           return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
       } else {
           booth.reserve(numberOfPeople);
           return String.format(OutputMessages.BOOTH_RESERVED, booth.getBoothNumber(), numberOfPeople);
       }
    }

    @Override
    public String leaveBooth(int boothNumber) {
        Booth booth = this.boothRepository.getByNumber(boothNumber);
        double bill = booth.getBill();
        booth.clear();
        this.totalIncome += bill;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Booth: %d", boothNumber)).append(System.lineSeparator());
        sb.append(String.format("Bill: %.2f", bill));
        return sb.toString().trim();
    }

    @Override
    public String getIncome() {
        return String.format("Income: %.2flv", this.totalIncome);
    }
}
