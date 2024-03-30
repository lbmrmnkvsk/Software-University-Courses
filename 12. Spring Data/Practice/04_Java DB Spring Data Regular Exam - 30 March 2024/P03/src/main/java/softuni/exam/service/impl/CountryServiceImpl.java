package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/countries.json"));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(this.gson.fromJson(readCountriesFromFile(), CountryImportDto[].class)).filter(dto -> {
            boolean isValid = this.validationUtil.isValid(dto);
            if (this.countryRepository.findByName(dto.getName()).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format("Successfully imported country %s - %s",
                        dto.getName(), dto.getCapital()));
            } else {
                sb.append("Invalid country");
            }
            sb.append(System.lineSeparator());

            return isValid;
        }).map(dto -> this.modelMapper.map(dto, Country.class))
                .forEach(this.countryRepository::save);

        return sb.toString().trim();
    }
}
