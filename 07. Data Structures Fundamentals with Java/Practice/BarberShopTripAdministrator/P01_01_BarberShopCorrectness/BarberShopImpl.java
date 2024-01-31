package barbershopjava;

import java.util.*;
import java.util.stream.Collectors;

public class BarberShopImpl implements BarberShop {
    private Map<Barber, List<Client>> map;
    private List<Client> clients;
    private List<Barber> barbers;

    public BarberShopImpl() {
        this.map = new LinkedHashMap<>();
        this.clients = new ArrayList<>();
        this.barbers = new ArrayList<>();
    }

    @Override
    public void addBarber(Barber b) {
        if (this.barbers.contains(b)) {
            throw new IllegalArgumentException();
        }

        this.barbers.add(b);
        this.map.put(b, new ArrayList<>());
    }

    @Override
    public void addClient(Client c) {
        if (this.clients.contains(c)) {
            throw new IllegalArgumentException();
        }

        this.clients.add(c);
    }

    @Override
    public boolean exist(Barber b) {
        return this.barbers.contains(b);
    }

    @Override
    public boolean exist(Client c) {
        return this.clients.contains(c);
    }

    @Override
    public Collection<Barber> getBarbers() {
        return this.barbers;
    }

    @Override
    public Collection<Client> getClients() {
        return this.clients;
    }

    @Override
    public void assignClient(Barber b, Client c) {
        if (!this.barbers.contains(b) || !this.clients.contains(c)) {
            throw new IllegalArgumentException();
        }

        c.barber = b;
        this.map.get(b).add(c);
    }

    @Override
    public void deleteAllClientsFrom(Barber b) {
        if (!this.barbers.contains(b)) {
            throw new IllegalArgumentException();
        }

        for (Client client : this.map.get(b)) {
            client.barber = null;
        }

        this.map.get(b).clear();
    }

    @Override
    public Collection<Client> getClientsWithNoBarber() {
        return this.clients.stream().filter(c -> c.barber == null).collect(Collectors.toList());
    }

    @Override
    public Collection<Barber> getAllBarbersSortedWithClientsCountDesc() {
        return this.barbers.stream()
                .sorted(Comparator.comparing(b -> this.map.get(b).size()).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Barber> getAllBarbersSortedWithStarsDescendingAndHaircutPriceAsc() {
        return this.barbers.stream()
                .sorted(Comparator.comparing((Barber b) -> b.stars).reversed()
                        .thenComparing((Barber b) -> b.haircutPrice))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Client> getClientsSortedByAgeDescAndBarbersStarsDesc() {
        return this.clients.stream().filter(c -> c.barber !=null)
                .sorted(Comparator.comparing((Client c) -> c.age).reversed()
                        .thenComparing((Client c) -> c.barber.stars).reversed())
                .collect(Collectors.toList());
    }
}
