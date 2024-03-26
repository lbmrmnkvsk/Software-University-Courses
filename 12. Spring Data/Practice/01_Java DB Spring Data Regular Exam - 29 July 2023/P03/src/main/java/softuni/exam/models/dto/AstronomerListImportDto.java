package softuni.exam.models.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "astronomers")
@XmlAccessorType(XmlAccessType.FIELD)
public class AstronomerListImportDto {
    @XmlElement(name = "astronomer")
    private List<AstronomerImportDto> astronomerDtos;

    public AstronomerListImportDto() {
    }

    public List<AstronomerImportDto> getAstronomerDtos() {
        return astronomerDtos;
    }

    public void setAstronomerDtos(List<AstronomerImportDto> astronomerDtos) {
        this.astronomerDtos = astronomerDtos;
    }
}
