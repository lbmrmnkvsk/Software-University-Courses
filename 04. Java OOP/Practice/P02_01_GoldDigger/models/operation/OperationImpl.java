package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OperationImpl implements Operation {
    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        List<Discoverer> ableToDig = discoverers.stream().filter(Discoverer::canDig).collect(Collectors.toList());
        List<String> exhibits = (List<String>) spot.getExhibits();

        while (!ableToDig.isEmpty() && !exhibits.isEmpty()) {
            Discoverer discoverer = ableToDig.get(0);
            String exhibit = exhibits.get(0);
            exhibits.remove(exhibit);

            discoverer.dig();
            discoverer.getMuseum().getExhibits().add(exhibit);

            if (!discoverer.canDig()) {
                ableToDig.remove(discoverer);
            }
        }
    }
}
