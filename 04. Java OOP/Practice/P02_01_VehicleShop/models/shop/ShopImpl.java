package vehicleShop.models.shop;

import vehicleShop.models.tool.Tool;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.worker.Worker;

import java.util.Collection;
import java.util.stream.Collectors;

public class ShopImpl implements Shop {

    public ShopImpl() {
    }

    @Override
    public void make(Vehicle vehicle, Worker worker) {
        Collection<Tool> tools = worker.getTools().stream().filter(tool -> !tool.isUnfit()).collect(Collectors.toList());
        while (!vehicle.reached() && worker.canWork() && !tools.isEmpty()) {
            Tool currentTool = tools.iterator().next();
            worker.working();
            currentTool.decreasesPower();
            vehicle.making();

            if (currentTool.isUnfit()) {
                tools.remove(currentTool);
                if (tools.iterator().hasNext()) {
                    currentTool = tools.iterator().next();
                }
            }
        }
    }
}
