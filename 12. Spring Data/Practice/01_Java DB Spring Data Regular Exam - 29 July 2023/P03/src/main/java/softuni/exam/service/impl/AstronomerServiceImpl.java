package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.exam.models.dto.AstronomerListSeedDTO;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.service.StarService;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AstronomerServiceImpl implements AstronomerService {
    private static String ASTRONOMERS_FILE_PATH = "src/main/resources/files/xml/astronomers.xml";
    private final AstronomerRepository astronomerRepository;
    private final StarRepository starRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;


    @Autowired
    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {
        return this.astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files.readString(Path.of(ASTRONOMERS_FILE_PATH));
    }

    @Override
//    @Transactional
    public String importAstronomers() throws IOException, JAXBException {
        Converter<String, LocalDate> toDate = new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> context) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(context.getSource(), formatter);
            }
        };
        modelMapper.addConverter(toDate);
        StringBuilder sb = new StringBuilder();
        JAXBContext context = JAXBContext.newInstance(AstronomerListSeedDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        AstronomerListSeedDTO astronomerListSeedDTO = (AstronomerListSeedDTO)
                unmarshaller.unmarshal(new File(ASTRONOMERS_FILE_PATH));


        astronomerListSeedDTO.getAstronomers().stream().filter(astronomerSeedDto -> {
            boolean isValid = validator.validate(astronomerSeedDto).isEmpty();


            Star star = this.starRepository.findById(astronomerSeedDto.getObservingStarId()).orElse(null);

            if (star == null) {
                isValid = false;
            }

            Astronomer astronomer = astronomerRepository.findByFirstNameAndLastName(astronomerSeedDto.getFirstName(),
                    astronomerSeedDto.getLastName()).orElse(null);
            if (astronomer != null) {
                isValid = false;
            }

            sb.append(isValid
                            ? String.format("Successfully imported astronomer %s %s - %.2f",
                            astronomerSeedDto.getFirstName(),
                            astronomerSeedDto.getLastName(),
                            astronomerSeedDto.getAverageObservationHours())
                            : "Invalid astronomer")
                    .append(System.lineSeparator());

            return isValid;
        })
                .map(astronomerSeedDto -> {
                    Astronomer astronomer = modelMapper.map(astronomerSeedDto, Astronomer.class);

                    Star star = this.starRepository.findById(astronomerSeedDto.getObservingStarId()).orElse(null);
                    star.getObservers().add(astronomer);
                    starRepository.save(star);

                    astronomer.setObservingStar(star);
                    return astronomer;
                })
                .forEach(astronomerRepository::save);

        return sb.toString().trim();
    }
}
