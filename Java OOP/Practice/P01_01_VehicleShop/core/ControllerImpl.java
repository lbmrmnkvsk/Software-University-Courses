package vehicleShop.core;

import vehicleShop.common.ConstantMessages;
import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.shop.Shop;
import vehicleShop.models.shop.ShopImpl;
import vehicleShop.models.tool.Tool;
import vehicleShop.models.tool.ToolImpl;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.vehicle.VehicleImpl;
import vehicleShop.models.worker.FirstShift;
import vehicleShop.models.worker.SecondShift;
import vehicleShop.models.worker.Worker;
import vehicleShop.repositories.VehicleRepository;
import vehicleShop.repositories.WorkerRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private VehicleRepository vehicleRepository;
    private WorkerRepository workerRepository;
    private int countMadeVehicles;

    public ControllerImpl() {
        this.vehicleRepository = new VehicleRepository();
        this.workerRepository = new WorkerRepository();
        this.countMadeVehicles = 0;
    }

    @Override
    public String addWorker(String type, String workerName) {
        Worker worker = null;
        if (type.equals("FirstShift")) {
            worker = new FirstShift(workerName);
        } else if (type.equals("SecondShift")) {
            worker = new SecondShift(workerName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.WORKER_TYPE_DOESNT_EXIST);
        }

        this.workerRepository.add(worker);
        return String.format(ConstantMessages.ADDED_WORKER, type, workerName);
    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        Vehicle vehicle = new VehicleImpl(vehicleName, strengthRequired);
        this.vehicleRepository.add(vehicle);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_VEHICLE, vehicleName);
    }

    @Override
    public String addToolToWorker(String workerName, int power) {
        Worker worker = this.workerRepository.findByName(workerName);
        if (worker == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }
        Tool tool = new ToolImpl(power);
        worker.addTool(tool);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOOL_TO_WORKER, power, workerName);
    }

    @Override
    public String makingVehicle(String vehicleName) {
        Collection<Worker> workers = this.workerRepository.getWorkers().stream()
                .filter(w -> w.getStrength() > 70).collect(Collectors.toList());
        if (workers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_WORKER_READY);
        }

        Vehicle vehicle = this.vehicleRepository.findByName(vehicleName);
        Shop shop = new ShopImpl();
        int startBrokenTools = 0;
        for (Worker worker : workers) {
            startBrokenTools += worker.getTools().stream()
                    .filter(t -> t.isUnfit()).count();
        }
        int endBrokenTools = 0;
        Collection<Worker> workers2 = new ArrayList<>();

        while (!vehicle.reached() && !workers.isEmpty()) {
            Worker worker = workers.iterator().next();
            shop.make(vehicle, worker);

            if (!worker.canWork() || worker.getTools().stream().filter(t -> !t.isUnfit()).count() == 0) {
                workers.remove(worker);
                workers2.add(worker);
            }
        }
        for (Worker worker : workers) {
            endBrokenTools += worker.getTools().stream()
                    .filter(t -> t.isUnfit()).count();
        }
        for (Worker worker : workers2) {
            endBrokenTools += worker.getTools().stream()
                    .filter(t -> t.isUnfit()).count();
        }

        int countBrokenTools = endBrokenTools - startBrokenTools;

        String result = "";
        if (vehicle.reached()) {
            this.countMadeVehicles++;
            result = "done";
        } else {
            result = "not done";
        }

        return String.format("Vehicle %s is %s. %d tool/s have been unfit while working on it!",
                vehicleName, result, countBrokenTools);
    }

    @Override
    public String statistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d vehicles are ready!", this.countMadeVehicles)).append(System.lineSeparator());
        sb.append("Info for workers:").append(System.lineSeparator());
        workerRepository.getWorkers().forEach(worker -> {
            sb.append(worker.toString()).append(System.lineSeparator());
        });
        return sb.toString().trim();
    }
}
