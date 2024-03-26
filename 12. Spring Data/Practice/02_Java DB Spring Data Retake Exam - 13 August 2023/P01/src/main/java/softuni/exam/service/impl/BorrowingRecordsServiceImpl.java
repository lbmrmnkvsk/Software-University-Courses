package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BorrowingRecordListDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final LibraryMemberRepository libraryMemberRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xml/borrowing-records.xml"));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        this.xmlParser.fromFile("src/main/resources/files/xml/borrowing-records.xml",
                BorrowingRecordListDto.class).getDtos().stream().filter(recordDto -> {
            boolean isValid = this.validationUtil.isValid(recordDto);
            if (this.bookRepository.findByTitle(recordDto.getBookDto().getTitle()).isEmpty()) {
                isValid = false;
            }
            if (this.libraryMemberRepository.findById(recordDto.getMemberDto().getId()).isEmpty()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format("Successfully imported borrowing record %s - %s",
                        recordDto.getBookDto().getTitle(), recordDto.getBorrowDate()));
            } else {
                sb.append("Invalid borrowing record");
            }
            sb.append(System.lineSeparator());

            return isValid;
        }).map(recordDto -> {
            BorrowingRecord borrowingRecord = this.modelMapper.map(recordDto, BorrowingRecord.class);
            Book book = this.bookRepository.findByTitle(recordDto.getBookDto().getTitle()).get();
            LibraryMember libraryMember = this.libraryMemberRepository.findById(recordDto.getMemberDto().getId()).get();

            borrowingRecord.setBook(book);
            borrowingRecord.setLibraryMember(libraryMember);
            book.getBorrowingRecords().add(borrowingRecord);
            libraryMember.getBorrowingRecords().add(borrowingRecord);

            this.bookRepository.save(book);
            this.libraryMemberRepository.save(libraryMember);
            return borrowingRecord;
        }).forEach(this.borrowingRecordRepository::save);

        return sb.toString().trim();
    }

    @Override
    public String exportBorrowingRecords() {
        StringBuilder sb = new StringBuilder();
        List<BorrowingRecord> records = this.borrowingRecordRepository.getScienceFictionRecordsOrdered();
        for (BorrowingRecord record : records) {
            sb.append(String.format("Book title: %s%n" +
                    "*Book author: %s\n" +
                    "**Date borrowed: %s\n" +
                    "***Borrowed by: %s %s\n",
                    record.getBook().getTitle(),
                    record.getBook().getAuthor(),
                    record.getBorrowDate().toString(),
                    record.getLibraryMember().getFirstName(), record.getLibraryMember().getLastName()));
        }

        return sb.toString().trim();
    }
}
