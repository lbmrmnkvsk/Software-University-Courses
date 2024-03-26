package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstronomerListImportDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AstronomerServiceImpl implements AstronomerService {
    private final AstronomerRepository astronomerRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final StarService starService;

    @Autowired
    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser, StarService starService) {
        this.astronomerRepository = astronomerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.starService = starService;
    }

    @Override
    public boolean areImported() {
        return this.astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xml/astronomers.xml"));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        this.xmlParser.fromFile("src/main/resources/files/xml/astronomers.xml", AstronomerListImportDto.class)
                .getAstronomerDtos().stream().filter(dto -> {
                    boolean isValid = this.validationUtil.isValid(dto);
                    if (this.astronomerRepository.findByFirstNameAndLastName(
                            dto.getFirstName(), dto.getLastName()).isPresent()) {
                        isValid = false;
                    }
                    if (this.starService.findById(dto.getObservingStarId()).isEmpty()) {
                        isValid = false;
                    }

                    if (isValid) {
                        sb.append(String.format("Successfully imported astronomer %s %s - %.2f",
                                dto.getFirstName(), dto.getLastName(), dto.getSalary()));
                    } else {
                        sb.append("Invalid astronomer");
                    }
                    sb.append(System.lineSeparator());

                    return isValid;
                }).map(dto -> {
                    Astronomer astronomer = this.modelMapper.map(dto, Astronomer.class);
                    Star star = this.starService.findById(dto.getObservingStarId()).get();
                    astronomer.setObservingStar(star);
                    star.getObservers().add(astronomer);
                    this.starService.save(star);

                    return astronomer;
                }).forEach(this.astronomerRepository::save);

        return sb.toString().trim();
    }
}
