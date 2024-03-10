package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationSeedDTO;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;


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
public class ConstellationServiceImpl implements ConstellationService {
    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public ConstellationServiceImpl(ConstellationRepository constellationRepository) {
        this.constellationRepository = constellationRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/constellations.json"));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder sb = new StringBuilder();

        List<Constellation> constellations = Arrays.stream(
                gson.fromJson(readConstellationsFromFile(), ConstellationSeedDTO[].class))
                .filter(dto -> {
                    boolean isValid = validator.validate(dto).isEmpty();
                    Optional<Constellation> found = this.constellationRepository.findByName(dto.getName());
                    if (found.isPresent()) {
                        isValid = false;
                    }

                    if (isValid) {
                        sb.append(String.format("Successfully imported constellation %s - %s.",
                                dto.getName(), dto.getDescription())).append(System.lineSeparator());
                    } else {
                        sb.append("Invalid constellation").append(System.lineSeparator());
                    }

                    return isValid;
                }).map(dto -> this.modelMapper.map(dto, Constellation.class))
                .collect(Collectors.toList());

        this.constellationRepository.saveAll(constellations);
        return sb.toString();
    }
}
