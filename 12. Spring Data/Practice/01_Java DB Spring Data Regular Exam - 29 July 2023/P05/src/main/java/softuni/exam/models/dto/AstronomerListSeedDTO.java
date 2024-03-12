package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "astronomers")
@XmlAccessorType(XmlAccessType.FIELD)
public class AstronomerListSeedDTO {
    @XmlElement(name = "astronomer")
    private List<AstronomerSeedDTO> astronomers;

    public AstronomerListSeedDTO() {
    }

    public AstronomerListSeedDTO(List<AstronomerSeedDTO> astronomers) {
        this.astronomers = astronomers;
    }

    public List<AstronomerSeedDTO> getAstronomers() {
        return astronomers;
    }

    public void setAstronomers(List<AstronomerSeedDTO> astronomers) {
        this.astronomers = astronomers;
    }
}
