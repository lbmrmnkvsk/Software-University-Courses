package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanoImportDto;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Volcano;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class VolcanoServiceImpl implements VolcanoService {
    private final VolcanoRepository volcanoRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CountryRepository countryRepository;

    @Autowired
    public VolcanoServiceImpl(VolcanoRepository volcanoRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, CountryRepository countryRepository) {
        this.volcanoRepository = volcanoRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return this.volcanoRepository.count() > 0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/volcanoes.json"));
    }

    @Override
    public String importVolcanoes() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(this.gson.fromJson(readVolcanoesFileContent(), VolcanoImportDto[].class)).filter(dto -> {
            boolean isValid = this.validationUtil.isValid(dto);
            if (this.volcanoRepository.findByName(dto.getName()).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format("Successfully imported volcano %s of type %s",
                        dto.getName(), dto.getVolcanoType()));
            } else {
                sb.append("Invalid volcano");
            }
            sb.append(System.lineSeparator());

            return isValid;
        }).map(dto -> {
            Optional<Country> optional = this.countryRepository.findById(dto.getCountry());
            Country country = null;
            if (optional.isPresent()) {
                country = optional.get();
            }
            Volcano volcano = this.modelMapper.map(dto, Volcano.class);
            volcano.setCountry(country);
            country.getVolcanoes().add(volcano);

            this.countryRepository.save(country);
            return volcano;
        }).forEach(this.volcanoRepository::save);

        return sb.toString().trim();
    }

    @Override
    public String exportVolcanoes() {
        StringBuilder sb = new StringBuilder();
        List<Volcano> volcanoes = this.volcanoRepository.findVolcanoesForExport();

        for (Volcano volcano : volcanoes) {
            sb.append(String.format("Volcano: %s", volcano.getName())).append(System.lineSeparator());
            sb.append(String.format("   *Located in: %s", volcano.getCountry().getName())).append(System.lineSeparator());
            sb.append(String.format("   **Elevation: %d", volcano.getElevation())).append(System.lineSeparator());
            sb.append(String.format("   ***Last eruption on: %s", volcano.getLastEruption().toString())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}