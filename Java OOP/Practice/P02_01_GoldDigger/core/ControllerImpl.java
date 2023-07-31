package goldDigger.core;

import goldDigger.common.ConstantMessages;
import goldDigger.common.ExceptionMessages;
import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.SpotRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private DiscovererRepository discovererRepository;
    private SpotRepository spotRepository;
    private int inspectedSpotsCount;
    private Operation operation;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
        this.inspectedSpotsCount = 0;
        this.operation = new OperationImpl();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        if (!kind.equals("Anthropologist") && !kind.equals("Archaeologist") && !kind.equals("Geologist")) {
            throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_INVALID_KIND);
        }

        Discoverer discoverer = null;
        if (kind.equals("Anthropologist")) {
            discoverer = new Anthropologist(discovererName);
        } else if (kind.equals("Archaeologist")) {
            discoverer = new Archaeologist(discovererName);
        } else if (kind.equals("Geologist")) {
            discoverer = new Geologist(discovererName);
        }

        this.discovererRepository.add(discoverer);
        return String.format(ConstantMessages.DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        for (String exhibit : exhibits) {
            spot.getExhibits().add(exhibit);
        }

        this.spotRepository.add(spot);
        return String.format(ConstantMessages.SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = this.discovererRepository.byName(discovererName);
        if (discoverer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DISCOVERER_DOES_NOT_EXIST, discovererName));
        }

        boolean result = this.discovererRepository.remove(discoverer);
        return String.format(ConstantMessages.DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        Spot spot = this.spotRepository.byName(spotName);
        List<Discoverer> discoverers = this.discovererRepository.getCollection()
                .stream().filter(d -> d.getEnergy() > 45).collect(Collectors.toList());
        if (discoverers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }

        int initialCountDiscoverers = this.discovererRepository.getCollection()
                .stream().filter(Discoverer::canDig).collect(Collectors.toList()).size();
        this.operation.startOperation(spot, discoverers);
        int finalCountDiscoverers = this.discovererRepository.getCollection()
                .stream().filter(Discoverer::canDig).collect(Collectors.toList()).size();
        int difference = initialCountDiscoverers - finalCountDiscoverers;

        this.inspectedSpotsCount++;
        return String.format(ConstantMessages.INSPECT_SPOT, spotName, difference);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d spots were inspected.", this.inspectedSpotsCount)).append(System.lineSeparator());
        sb.append("Information for the discoverers:").append(System.lineSeparator());
        for (Discoverer discoverer : this.discovererRepository.getCollection()) {
            sb.append(String.format("Name: %s", discoverer.getName())).append(System.lineSeparator());
            sb.append(String.format("Energy: %.0f", discoverer.getEnergy())).append(System.lineSeparator());
            if (discoverer.getMuseum().getExhibits().isEmpty()) {
                sb.append("Museum exhibits: None").append(System.lineSeparator());
            } else {
                sb.append(String.format("Museum exhibits: %s", String.join(", ", discoverer.getMuseum().getExhibits() ) ) ).append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }
}
