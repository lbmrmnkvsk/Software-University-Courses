package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordListDto {
    @XmlElement(name = "borrowing_record")
    private List<BorrowingRecordDto> dtos;

    public BorrowingRecordListDto() {
    }

    public List<BorrowingRecordDto> getDtos() {
        return dtos;
    }

    public void setDtos(List<BorrowingRecordDto> dtos) {
        this.dtos = dtos;
    }
}
