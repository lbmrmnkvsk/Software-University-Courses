package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CompanyListImportDto;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CompanyService;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, CountryRepository countryRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xml/companies.xml"));
    }

    @Override
    public String importCompanies() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        this.xmlParser.fromFile("src/main/resources/files/xml/companies.xml", CompanyListImportDto.class)
                .getDtos().stream().filter(dto -> {
                    boolean isValid = this.validationUtil.isValid(dto);
                    if (this.companyRepository.findByName(dto.getName()).isPresent()) {
                        isValid = false;
                    }

                    if (isValid) {
                        sb.append(String.format("Successfully imported company %s - %d",
                                dto.getName(), dto.getCountryId()));
                    } else {
                        sb.append("Invalid company");
                    }
                    sb.append(System.lineSeparator());

                    return isValid;
                }).map(dto -> {
                    Optional<Country> optional = this.countryRepository.findById(dto.getCountryId());
                    Country country = null;
                    if (optional.isPresent()) {
                        country = optional.get();
                    }

                    Company company = this.modelMapper.map(dto, Company.class);
                    company.setCountry(country);
                    this.countryRepository.save(country);
                    return company;
                }).forEach(this.companyRepository::save);

        return sb.toString().trim();
    }
}
