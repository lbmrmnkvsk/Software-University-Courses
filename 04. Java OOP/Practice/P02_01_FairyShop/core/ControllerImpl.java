package fairyShop.core;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private HelperRepository helperRepository;
    private PresentRepository presentRepository;
    private Shop shop;
    private int countCraftedPresents;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        this.shop = new ShopImpl();
        this.countCraftedPresents = 0;
    }

    @Override
    public String addHelper(String type, String helperName) {
        if (!type.equals("Happy") && !type.equals("Sleepy")) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }

        Helper helper = null;
        if (type.equals("Happy")) {
            helper = new Happy(helperName);
        } else if (type.equals("Sleepy")) {
            helper = new Sleepy(helperName);
        }

        this.helperRepository.add(helper);
        return String.format(ConstantMessages.ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = this.helperRepository.findByName(helperName);
        if (helper == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }

        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presentRepository.add(present);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        Present present = this.presentRepository.findByName(presentName);

        List<Helper> helpers = this.helperRepository.getModels().stream()
                .filter(h -> h.getEnergy() > 50).collect(Collectors.toList());
        if (helpers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }

        int startCountBrokenInstruments = 0;
        for (Helper helper : helpers) {
            startCountBrokenInstruments += helper.getInstruments().stream().filter(Instrument::isBroken).count();
        }

        List<Helper> availableHelpers = this.helperRepository.getModels().stream()
                .filter(h -> h.getEnergy() > 50).collect(Collectors.toList());

        while (!present.isDone() && !availableHelpers.isEmpty()) {
            Helper helper = availableHelpers.get(0);
            this.shop.craft(present, helper);

            if (!helper.canWork() || helper.getInstruments().stream().noneMatch(i -> !i.isBroken())) {
                availableHelpers.remove(helper);
            }
        }

        int endCountBrokenInstruments = 0;
        for (Helper helper : helpers) {
            endCountBrokenInstruments += helper.getInstruments().stream().filter(Instrument::isBroken).count();
        }

        int countBrokenInstruments = endCountBrokenInstruments - startCountBrokenInstruments;

        if (present.isDone()) {
            this.countCraftedPresents++;
            return String.format("Present %s is done. " +
                    "%d instrument/s have been broken while working on it!",
                    presentName, countBrokenInstruments);
        } else {
            return String.format("Present %s is not done. " +
                            "%d instrument/s have been broken while working on it!",
                    presentName, countBrokenInstruments);
        }
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d presents are done!", this.countCraftedPresents)).append(System.lineSeparator());
        sb.append("Helpers info:").append(System.lineSeparator());

        for (Helper helper : this.helperRepository.getModels()) {
            sb.append(String.format("Name: %s", helper.getName())).append(System.lineSeparator());
            sb.append(String.format("Energy: %d", helper.getEnergy())).append(System.lineSeparator());
            sb.append(String.format("Instruments: %d not broken left",
                    helper.getInstruments().stream().filter(i -> !i.isBroken()).count()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
