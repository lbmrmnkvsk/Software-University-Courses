import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

public class PersonCollectionImpl implements PersonCollection {
    private HashMap<String, Person> emailsToPersons;
    private HashMap<String, TreeSet<Person>> domainsToPeople;
    private HashMap<String, TreeSet<Person>> namestownsToPeople;
    private TreeMap<Integer, TreeSet<Person>> agesToPeople;
    private HashMap<String, TreeMap<Integer, TreeSet<Person>>> townsToAgesToPeople;

    public PersonCollectionImpl() {
        this.emailsToPersons = new HashMap<>();
        this.domainsToPeople = new HashMap<>();
        this.namestownsToPeople = new HashMap<>();
        this.agesToPeople = new TreeMap<>();
        this.townsToAgesToPeople = new HashMap<>();
    }

    @Override
    public boolean add(String email, String name, int age, String town) {
        if (emailsToPersons.containsKey(email)) {
            return false;
        }
        Person person = new Person(email, name, age, town);

        this.emailsToPersons.put(person.getEmail(), person);

        String domain = getDomain(email);
        this.domainsToPeople.putIfAbsent(domain, new TreeSet<>());
        Set<Person> set1 = this.domainsToPeople.get(domain);
        set1.add(person);

        String nametown = name + "_" + town;
        this.namestownsToPeople.putIfAbsent(nametown, new TreeSet<>());
        Set<Person> set2 = this.namestownsToPeople.get(nametown);
        set2.add(person);

        this.agesToPeople.putIfAbsent(age, new TreeSet<>());
        Set<Person> set3 = this.agesToPeople.get(age);
        set3.add(person);

        this.townsToAgesToPeople.putIfAbsent(town, new TreeMap<>());
        TreeMap<Integer, TreeSet<Person>> agesToPeople2 = this.townsToAgesToPeople.get(town);
        agesToPeople2.putIfAbsent(age, new TreeSet<>());
        Set<Person> set4 = agesToPeople2.get(age);
        set4.add(person);

        return true;
    }

    private String getDomain(String email) {
        return email.substring(email.lastIndexOf('@') + 1);
    }

    @Override
    public int getCount() {
        return this.emailsToPersons.size();
    }

    @Override
    public boolean delete(String email) {
        Person person = this.emailsToPersons.remove(email);
        if (person == null) {
            return false;
        }

        String domain = getDomain(email);
        Set<Person> set1 = this.domainsToPeople.get(domain);
        set1.remove(person);
        if (set1.isEmpty()) {
            this.domainsToPeople.remove(domain);
        }

        String nametown = person.getName() + "_" + person.getTown();
        Set<Person> set2 = this.namestownsToPeople.get(nametown);
        set2.remove(person);
        if (set2.isEmpty()) {
            this.namestownsToPeople.remove(nametown);
        }

        int age = person.getAge();
        Set<Person> set3 = this.agesToPeople.get(age);
        set3.remove(person);
        if (set3.isEmpty()) {
            this.agesToPeople.remove(age);
        }

        String town = person.getTown();
        TreeMap<Integer, TreeSet<Person>> agesToPeople2 = this.townsToAgesToPeople.get(town);
        Set<Person> set4 = agesToPeople2.get(age);
        set4.remove(person);
        if (set4.isEmpty()) {
            agesToPeople2.remove(age);
        }
        if (agesToPeople2.isEmpty()) {
            this.townsToAgesToPeople.remove(town);
        }

        return true;
    }

    @Override
    public Person find(String email) {
        return this.emailsToPersons.get(email);
    }

    @Override
    public Iterable<Person> findAll(String emailDomain) {
        return this.domainsToPeople.get(emailDomain);
    }

    @Override
    public Iterable<Person> findAll(String name, String town) {
        String nametown = name + "_" + town;
        return this.namestownsToPeople.get(nametown);
    }

    @Override
    public Iterable<Person> findAll(int startAge, int endAge) {
        List<Person> result = new ArrayList<>();
        this.agesToPeople.entrySet().stream().filter(e -> e.getKey() >= startAge && e.getKey() <= endAge)
                .map(Map.Entry::getValue).forEach(set -> result.addAll(set));

        return result;
    }

    @Override
    public Iterable<Person> findAll(int startAge, int endAge, String town) {
        TreeMap<Integer, TreeSet<Person>> agesToPeople2 = this.townsToAgesToPeople.get(town);
        List<Person> result = new ArrayList<>();

        agesToPeople2.entrySet().stream().filter(e -> e.getKey() >= startAge && e.getKey() <= endAge)
                .map(e -> e.getValue()).forEach(set -> result.addAll(set));

        return result;
    }
}
