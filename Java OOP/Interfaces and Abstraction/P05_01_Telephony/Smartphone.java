package P05_01_Telephony;

import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();

        for (String url : urls) {
            boolean containsDigit = false;
            for (char element : url.toCharArray()) {
                if (Character.isDigit(element)) {
                    containsDigit = true;
                }
            }

            if (containsDigit) {
                sb.append("Invalid URL!").append(System.lineSeparator());
            } else {
                sb.append(String.format("Browsing: %s!", url)).append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();

        for (String number : numbers) {
            boolean containsLetter = false;
            for (char element : number.toCharArray()) {
                if (Character.isLetter(element)) {
                    containsLetter = true;
                }
            }

            if (containsLetter) {
                sb.append("Invalid number!").append(System.lineSeparator());
            } else {
                sb.append(String.format("Calling... %s", number)).append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
