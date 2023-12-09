package solar.core;

import solar.models.Inverter;
import solar.models.PVModule;

import java.util.*;
import java.util.stream.Collectors;

public class SolarServiceImpl implements SolarService {
    private Map<String, Inverter> inverters = new HashMap<>();
    private Map<String, String> arrayToInverterMap = new HashMap<>();
    private Map<PVModule, String> panelToInverterMap = new HashMap<>();
    private Map<Inverter, Set<PVModule>> inverterToPanelsMap = new HashMap<>();
    private Map<Inverter, Integer> inverterToConnectedModulesCount = new HashMap<>();

    @Override
    public void addInverter(Inverter inverter) {
        if (inverters.containsKey(inverter.id)) {
            throw new IllegalArgumentException("Inverter already exists.");
        }
        inverters.put(inverter.id, inverter);
        inverterToPanelsMap.put(inverter, new HashSet<>());
    }

    @Override
    public void addArray(Inverter inverter, String arrayId) {
        if (!inverters.containsKey(inverter.id) || arrayToInverterMap.containsValue(arrayId) ||
                inverter.maxPvArraysConnected <= arrayToInverterMap.values().stream().filter(id -> id.equals(inverter.id)).count()) {
            throw new IllegalArgumentException("Invalid Inverter or arrayId.");
        }
        arrayToInverterMap.put(arrayId, inverter.id);
    }

    @Override
    public void addPanel(Inverter inverter, String arrayId, PVModule pvModule) {
        if (!inverters.containsKey(inverter.id) || !arrayToInverterMap.getOrDefault(arrayId, "").equals(inverter.id) ||
                panelToInverterMap.containsKey(pvModule)) {
            throw new IllegalArgumentException("Invalid Inverter, arrayId, or PVModule.");
        }
        panelToInverterMap.put(pvModule, inverter.id);
        inverterToPanelsMap.computeIfAbsent(inverter, k -> new HashSet<>()).add(pvModule);

        // Update connected modules count for the inverter
        inverterToConnectedModulesCount.put(inverter, inverterToConnectedModulesCount.getOrDefault(inverter, 0) + 1);
    }

    @Override
    public boolean containsInverter(String id) {
        return inverters.containsKey(id);
    }

    @Override
    public boolean isPanelConnected(PVModule pvModule) {
        return panelToInverterMap.containsKey(pvModule);
    }

    @Override
    public Inverter getInverterByPanel(PVModule pvModule) {
        return inverters.get(panelToInverterMap.getOrDefault(pvModule, null));
    }

    @Override
    public void replaceModule(PVModule oldModule, PVModule newModule) {
        if (!panelToInverterMap.containsKey(oldModule) || panelToInverterMap.containsKey(newModule)) {
            throw new IllegalArgumentException("Invalid oldModule or newModule.");
        }

        String inverterId = panelToInverterMap.get(oldModule);

        if (inverterId == null) {
            throw new IllegalArgumentException("Old module is not in use.");
        }

        panelToInverterMap.remove(oldModule);
        panelToInverterMap.put(newModule, inverterId);
        inverterToPanelsMap.get(inverters.get(inverterId)).remove(oldModule);
        inverterToPanelsMap.get(inverters.get(inverterId)).add(newModule);
        // No need to update connected modules count as it remains the same
    }

    @Override
    public Collection<Inverter> getByProductionCapacity() {
        return inverters.values().stream()
                .sorted(Comparator.comparingInt(inverter -> inverter.maxPvArraysConnected * inverterToPanelsMap.get(inverter).stream()
                        .mapToInt((PVModule p) -> p.maxWattProduction)
                        .sum()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Inverter> getByNumberOfPVModulesConnected() {
        return inverters.values().stream()
                .sorted(Comparator.comparingInt(inverterToConnectedModulesCount::get))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<PVModule> getByWattProduction() {
        return panelToInverterMap.keySet().stream()
                .sorted(Comparator.comparing((PVModule p) -> p.maxWattProduction)
                        )
                .collect(Collectors.toList());
    }

}
