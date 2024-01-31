package P04_01_SayHelloExtended;

public abstract class BasePerson implements Person {
    private String name;

    public BasePerson(String name) {
        this.setName(name);
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
