package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanologistListImportDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.VolcanologistService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class VolcanologistServiceImpl implements VolcanologistService {
    private final VolcanologistRepository volcanologistRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final VolcanoRepository volcanoRepository;

    public VolcanologistServiceImpl(VolcanologistRepository volcanologistRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, VolcanoRepository volcanoRepository) {
        this.volcanologistRepository = volcanologistRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.volcanoRepository = volcanoRepository;
    }

    @Override
    public boolean areImported() {
        return this.volcanologistRepository.count() > 0;
    }

    @Override
    public String readVolcanologistsFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xml/volcanologists.xml"));
    }

//    @Override
//    public String importVolcanologists() throws IOException, JAXBException {
//        StringBuilder sb = new StringBuilder();
//
//        this.xmlParser.fromFile("src/main/resources/files/xml/volcanologists.xml", VolcanologistListImportDto.class)
//                .getDtos().stream().filter(dto -> {
//                    boolean isValid = this.validationUtil.isValid(dto);
//                    if (this.volcanologistRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName()).isPresent()) {
//                        isValid = false;
//                    }
//                    if (this.volcanoRepository.findById(dto.getExploringVolcanoId()).isEmpty()) {
//                        isValid = false;
//                    }
//
//                    if (isValid) {
//                        sb.append(String.format("Successfully imported volcanologist %s %s",
//                                dto.getFirstName(), dto.getLastName()));
//                    } else {
//                        sb.append("Invalid volcanologist");
//                    }
//                    sb.append(System.lineSeparator());
//
//                    return isValid;
//                }).map(dto -> {
//                    Optional<Volcano> optional = this.volcanoRepository.findById(dto.getExploringVolcanoId());
//                    Volcano volcano = null;
//                    if (optional.isPresent()) {
//                        volcano = optional.get();
//                    }
//                    Volcanologist volcanologist = this.modelMapper.map(dto, Volcanologist.class);
//                    volcanologist.setVolcano(volcano);
//                    volcano.getVolcanologists().add(volcanologist);
//
//                    this.volcanoRepository.save(volcano);
//                    return volcanologist;
//                }).forEach(this.volcanologistRepository::save);
//
//        return sb.toString().trim();
//    }

    @Override
    public String importVolcanologists() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        this.xmlParser.fromFile("src/main/resources/files/xml/volcanologists.xml", VolcanologistListImportDto.class)
                .getDtos().stream().filter(dto -> {
                    boolean isValid = this.validationUtil.isValid(dto);
                    if (this.volcanologistRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName()).isPresent()) {
                        isValid = false;
                    }
                    if (this.volcanoRepository.findById(dto.getExploringVolcanoId()).isEmpty()) {
                        isValid = false;
                    }

                    if (isValid) {
                        sb.append(String.format("Successfully imported volcanologist %s %s",
                                dto.getFirstName(), dto.getLastName()));
                    } else {
                        sb.append("Invalid volcanologist");
                    }
                    sb.append(System.lineSeparator());

                    return isValid;
                }).map(dto -> {
                    Optional<Volcano> optional = this.volcanoRepository.findById(dto.getExploringVolcanoId());
                    Volcano volcano = null;
                    if (optional.isPresent()) {
                        volcano = optional.get();
                    }

//                    Volcanologist volcanologist = this.modelMapper.map(dto, Volcanologist.class);
                    Volcanologist volcanologist = new Volcanologist();
                    volcanologist.setFirstName(dto.getFirstName());
                    volcanologist.setLastName(dto.getLastName());
                    volcanologist.setAge(dto.getAge());
                    volcanologist.setSalary(dto.getSalary());
                    if (dto.getExploringFrom() == null || dto.getExploringFrom().equals("") ||
                            dto.getExploringFrom().equals("null") || dto.getExploringFrom().isEmpty()) {
                        volcanologist.setExploringFrom(null);
                    } else {
                        String dateString = dto.getExploringFrom();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate date = LocalDate.parse(dateString, formatter);

                        volcanologist.setExploringFrom(date);
                    }


                    volcanologist.setVolcano(volcano);
                    volcano.getVolcanologists().add(volcanologist); // CHECK THIS!

                    this.volcanoRepository.save(volcano);
                    return volcanologist;
                }).forEach(this.volcanologistRepository::save);

        return sb.toString().trim();
    }
}