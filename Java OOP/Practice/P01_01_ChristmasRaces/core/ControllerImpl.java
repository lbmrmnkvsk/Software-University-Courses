package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.repositories.CarRepository;
import christmasRaces.repositories.DriverRepository;
import christmasRaces.repositories.RaceRepository;

public class ControllerImpl implements Controller {
    private DriverRepository driverRepository;
    private CarRepository carRepository;
    private RaceRepository raceRepository;

    public ControllerImpl(DriverRepository driverRepository, CarRepository carRepository, RaceRepository raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        return null;
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        return null;
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        return null;
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        return null;
    }

    @Override
    public String startRace(String raceName) {
        return null;
    }

    @Override
    public String createRace(String name, int laps) {
        return null;
    }
}
