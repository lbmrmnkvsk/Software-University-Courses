package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PersonImportDto;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Person;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonRepository;
import softuni.exam.service.PersonService;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final Gson gson;
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, Gson gson, CountryRepository countryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.personRepository = personRepository;
        this.gson = gson;
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.personRepository.count() > 0;
    }

    @Override
    public String readPeopleFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/people.json"));
    }

    @Override
    public String importPeople() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(this.gson.fromJson(readPeopleFromFile(), PersonImportDto[].class)).filter(dto -> {
            boolean isValid = this.validationUtil.isValid(dto);
            if (this.personRepository.findByFirstName(dto.getFirstName()).isPresent() ||
            this.personRepository.findByEmail(dto.getEmail()).isPresent() ||
            this.personRepository.findByPhone(dto.getPhone()).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format("Successfully imported person %s %s",
                        dto.getFirstName(), dto.getLastName()));
            } else {
                sb.append("Invalid person");
            }
            sb.append(System.lineSeparator());

            return isValid;
        }).map(dto -> {
            Optional<Country> optional = this.countryRepository.findById(Long.parseLong(dto.getCountry()));
            Country country = null;
            if (optional.isPresent()) {
                country = optional.get();
            }

            Person person = this.modelMapper.map(dto, Person.class);
            person.setCountry(country);
            this.countryRepository.save(country);
            return person;
        }).forEach(this.personRepository::save);

        return sb.toString().trim();
    }
}
