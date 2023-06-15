package softUni;

import java.util.ArrayList;
import java.util.List;

public class SoftUni {
    private int capacity;
    private List<Student> data;

    public SoftUni(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getCount() {
        return this.data.size();
    }

    public String insert(Student student) {
        if (this.data.size() >= this.capacity) {
            return "The hall is full.";
        } else {
            if (this.data.contains(student)) {
                return "Student is already in the hall.";
            } else {
                this.data.add(student);
                return String.format("Added student %s %s.", student.getFirstName(), student.getLastName());
            }
        }
    }

    public String remove(Student student) {
        if (!this.data.contains(student)) {
            return "Student not found.";
        } else {
            this.data.remove(student);
            return String.format("Removed student %s %s.", student.getFirstName(), student.getLastName());
        }
    }

    public Student getStudent(String firstName, String lastName) {
        for (Student student : this.data) {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                return student;
            }
        }
        return null;
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Hall size: %d", this.data.size())).append(System.lineSeparator());
        for (Student student : this.data) {
            sb.append(student.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
