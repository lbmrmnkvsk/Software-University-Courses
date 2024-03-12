package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.exam.models.dto.StarSeedDTO;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarServiceImpl implements StarService {
    private final StarRepository starRepository;
    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/stars.json"));
    }

//    @Transactional
    @Override
    public String importStars() throws IOException {
        StringBuilder sb = new StringBuilder();

//        List<Star> starsList =
                Arrays.stream(gson.fromJson(readStarsFileContent(), StarSeedDTO[].class))
                .filter(dto -> {
                    boolean isValid = validator.validate(dto).isEmpty();
                    Optional<Star> found = this.starRepository.findByName(dto.getName());
                    if (found.isPresent()) {
                        isValid = false;
                    }

                    if (isValid) {
                        sb.append(String.format("Successfully imported star %s - %.2f light years",
                                dto.getName(), dto.getLightYears()));
                    } else {
                        sb.append("Invalid star");
                    }
                    sb.append(System.lineSeparator());

                    return isValid;
                }).map(dto -> {
                    Star star = modelMapper.map(dto, Star.class);
                    Constellation constellation = this.constellationRepository.findById(dto.getConstellation()).orElse(null);
                    constellation.getStars().add(star);
                    star.setConstellation(constellation);
                    this.constellationRepository.save(constellation);

                    return star;
                })
                .forEach(this.starRepository::save);

//        this.starRepository.saveAll(starsList);
        return sb.toString().trim();
    }

    @Override
    public String exportStars() {
        List<Star> stars = this.starRepository.findByStarTypeAndObserversIsNullOrderByLightYears(StarType.RED_GIANT);
        StringBuilder sb = new StringBuilder();

        for (Star star : stars) {
            sb.append(String.format("Star: %s", star.getName())).append(System.lineSeparator());
            sb.append(String.format("   *Distance: %.2f light years", star.getLightYears())).append(System.lineSeparator());
            sb.append(String.format("   **Description: %s", star.getDescription())).append(System.lineSeparator());
            sb.append(String.format("   ***Constellation: %s", star.getConstellation().getName())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
