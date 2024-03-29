package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "jobs")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobListImportDto {
    @XmlElement(name = "job")
    private List<JobImportDto> dtos;

    public JobListImportDto() {
    }

    public List<JobImportDto> getDtos() {
        return dtos;
    }

    public void setDtos(List<JobImportDto> dtos) {
        this.dtos = dtos;
    }
}
