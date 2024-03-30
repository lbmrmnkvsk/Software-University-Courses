package softuni.exam.models.dto;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "volcanologists")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistListImportDto {
    @XmlElement(name = "volcanologist")
    private List<VolcanologistImportDto> dtos;

    public VolcanologistListImportDto() {
    }

    public List<VolcanologistImportDto> getDtos() {
        return dtos;
    }

    public void setDtos(List<VolcanologistImportDto> dtos) {
        this.dtos = dtos;
    }
}
