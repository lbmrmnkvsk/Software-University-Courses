package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.JobListImportDto;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Job;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.JobRepository;
import softuni.exam.service.JobService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CompanyRepository companyRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean areImported() {
        return this.jobRepository.count() > 0;
    }

    @Override
    public String readJobsFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xml/jobs.xml"));
    }

    @Override
    public String importJobs() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        this.xmlParser.fromFile("src/main/resources/files/xml/jobs.xml", JobListImportDto.class)
                .getDtos().stream().filter(dto -> {
                    boolean isValid = this.validationUtil.isValid(dto);

                    if (isValid) {
                        sb.append(String.format("Successfully imported job %s",
                                dto.getTitle()));
                    }else {
                        sb.append("Invalid job");
                    }
                    sb.append(System.lineSeparator());

                    return isValid;
                }).map(dto -> {
                    Optional<Company> optional = this.companyRepository.findById(dto.getCompanyId());
                    Company company = null;
                    if (optional.isPresent()) {
                        company = optional.get();
                    }

                    Job job = this.modelMapper.map(dto, Job.class);
                    job.setCompany(company);
                    this.companyRepository.save(company);

                    return job;
                }).forEach(this.jobRepository::save);

        return sb.toString().trim();
    }

    @Override
    public String getBestJobs() {
        StringBuilder sb = new StringBuilder();
        List<Job> jobs = this.jobRepository.findBySalaryGreaterThanEqualAndHoursAWeekLessThanEqualOrderBySalaryDesc(
                5000.00, 30.00);

        for (Job job : jobs) {
            sb.append(String.format("Job title %s", job.getTitle())).append(System.lineSeparator());
            sb.append(String.format("-Salary: %.2f$", job.getSalary())).append(System.lineSeparator());
            sb.append(String.format("--Hours a week: %.2fh.", job.getHoursAWeek())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
