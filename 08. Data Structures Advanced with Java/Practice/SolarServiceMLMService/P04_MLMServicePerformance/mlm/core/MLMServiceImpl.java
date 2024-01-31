package mlm.core;

import mlm.models.Seller;

import java.util.*;
import java.util.stream.Collectors;

public class MLMServiceImpl implements MLMService {
    private final Set<Seller> sellers = new HashSet<>();
    private final Map<Seller, Integer> totalSalesMap = new HashMap<>();
    private final Map<Seller, Set<Seller>> hiresMap = new HashMap<>();
    private final Map<Seller, Seller> parentMap = new HashMap<>();
    private final Map<Seller, Integer> totalEmployeesMap = new HashMap<>();

    @Override
    public void addSeller(Seller seller) {
        if (exists(seller)) {
            throw new IllegalArgumentException("Seller already exists.");
        }
        sellers.add(seller);
        totalSalesMap.put(seller, 0);
        hiresMap.put(seller, new HashSet<>());
        totalEmployeesMap.put(seller, 0);
    }

    @Override
    public void hireSeller(Seller parent, Seller newHire) {
        if (!exists(parent) || exists(newHire)) {
            throw new IllegalArgumentException("Invalid parent or new hire.");
        }
        parent.earnings += 5;
        newHire.earnings = 0;
        sellers.add(newHire);

        Set<Seller> hires = hiresMap.computeIfAbsent(parent, k -> new HashSet<>());
        hires.add(newHire);
        hiresMap.put(newHire, new HashSet<>());
        parentMap.put(newHire, parent);

        updateTotalEmployees(parent, newHire);
    }

    @Override
    public boolean exists(Seller seller) {
        return sellers.contains(seller);
    }

    @Override
    public void fire(Seller seller) {
        if (!exists(seller)) {
            throw new IllegalArgumentException("Seller does not exist.");
        }

        Set<Seller> hires = hiresMap.getOrDefault(seller, Collections.emptySet());
        sellers.remove(seller);

        for (Seller hire : hires) {
            Set<Seller> parentHierarchy = getHierarchy(getParent(hire));
            parentHierarchy.addAll(hires);
            totalEmployeesMap.put(getParent(hire), totalEmployeesMap.getOrDefault(getParent(hire), 0) + hires.size());
        }
    }

    @Override
    public void makeSale(Seller seller, int amount) {
        seller.earnings += amount;
        Seller parent = parentMap.get(seller);
        if (parent != null) {
            distributeCommissions(seller, amount * 0.05);
        }
        totalSalesMap.put(seller, totalSalesMap.getOrDefault(seller, 0) + 1);

        while (parent != null) {
            parent.earnings -= amount * 0.05;
            parent = parentMap.get(parent);
        }
    }

    @Override
    public Collection<Seller> getByProfits() {
        return sellers.stream()
                .sorted(Comparator.comparingInt(s -> -s.earnings))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Seller> getByEmployeeCount() {
        return sellers.stream()
                .sorted(Comparator.comparingInt(s -> -getTotalEmployees(s)))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Seller> getByTotalSalesMade() {
        return sellers.stream()
                .sorted(Comparator.comparingInt(s -> -totalSalesMap.getOrDefault(s, 0)))
                .collect(Collectors.toList());
    }

    private void distributeCommissions(Seller seller, double commission) {
        Set<Seller> hierarchy = getHierarchy(seller);
        for (Seller member : hierarchy) {
            member.earnings += commission;
        }
    }

    private Set<Seller> getHierarchy(Seller seller) {
        Set<Seller> hierarchy = new HashSet<>();
        while (seller != null) {
            hierarchy.add(seller);
            seller = getParent(seller);
        }
        return hierarchy;
    }

    private Seller getParent(Seller seller) {
        return parentMap.get(seller);
    }

    private void updateTotalEmployees(Seller parent, Seller newHire) {
        Set<Seller> hierarchy = getHierarchy(parent);
        for (Seller member : hierarchy) {
            totalEmployeesMap.put(member, totalEmployeesMap.getOrDefault(member, 0) + 1);
        }
    }

    private int getTotalEmployees(Seller seller) {
        return totalEmployeesMap.getOrDefault(seller, 0);
    }
}
