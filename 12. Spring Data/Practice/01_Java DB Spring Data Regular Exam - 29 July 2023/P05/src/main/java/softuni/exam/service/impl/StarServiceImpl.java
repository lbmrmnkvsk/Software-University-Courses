package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.StarImportDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.enums.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StarServiceImpl implements StarService {
    private final StarRepository starRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final ConstellationService constellationService;

    public StarServiceImpl(StarRepository starRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, ConstellationService constellationService) {
        this.starRepository = starRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.constellationService = constellationService;
    }


    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/stars.json"));
    }

    @Override
    public String importStars() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(readStarsFileContent(), StarImportDto[].class)).filter(dto -> {
            boolean isValid = this.validationUtil.isValid(dto);
            if (this.starRepository.findByName(dto.getName()).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format("Successfully imported star %s - %.2f light years", dto.getName(), dto.getLightYears()));
            } else {
                sb.append("Invalid star");
            }
            sb.append(System.lineSeparator());

            return isValid;
        }).map(dto -> {
            Star star = new Star();
            star.setStarType(dto.getStarType());
            star.setDescription(dto.getDescription());
            star.setName(dto.getName());
            star.setLightYears(dto.getLightYears());
            Optional<Constellation> optional = this.constellationService.findById(dto.getConstellation());
            if (optional.isPresent()) {
                Constellation constellation = optional.get();
                star.setConstellation(constellation);
                constellation.getStars().add(star);
                this.constellationService.save(constellation);
            }

            return star;
        }).forEach(this.starRepository::save);

        return sb.toString().trim();
    }

    @Override
    public String exportStars() {
        StringBuilder sb = new StringBuilder();
        List<Star> stars = this.starRepository.findByStarTypeAndObserversIsEmptyOrderByLightYearsAsc(StarType.RED_GIANT);
        for (Star star : stars) {
            sb.append(String.format("Star: %s\n" +
                                    "   *Distance: %.2f light years\n" +
                                    "   **Description: %s\n" +
                                    "   ***Constellation: %s",
                            star.getName(),
                            star.getLightYears(),
                            star.getDescription(),
                            star.getConstellation().getName()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public Optional<Star> findById(Long id) {
        return this.starRepository.findById(id);
    }

    @Override
    public Star save(Star star) {
        return this.starRepository.save(star);
    }
}
