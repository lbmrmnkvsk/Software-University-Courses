package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryImportDto {
    @Size(min = 2, max = 30)
    @NotNull
    private String name;
    @Size(min = 2, max = 19)
    @NotNull
    private String code;
    @Size(min = 2, max = 19)
    @NotNull
    private String currency;

    public CountryImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
