package vaccopsjava;

import javax.print.Doc;
import java.util.*;
import java.util.stream.Collectors;

public class VaccOps implements IVaccOps {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private Map<Doctor, List<Patient>> map;
    private Map<Patient, Doctor> map2;

    public VaccOps() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.map = new LinkedHashMap<>();
        this.map2 = new LinkedHashMap<>();
    }

    public void addDoctor(Doctor d) {
        if (this.doctors.stream().anyMatch(doctor -> doctor.name.equals(d.name))) {
            throw new IllegalArgumentException();
        }

        this.doctors.add(d);
        this.map.put(d, new ArrayList<>());
    }

    public void addPatient(Doctor d, Patient p) {
        if (!this.exist(d)) {
            throw new IllegalArgumentException();
        }

        this.patients.add(p);
        this.map.get(d).add(p);
        this.map2.put(p, d);
    }

    public Collection<Doctor> getDoctors() {
        return Collections.unmodifiableCollection(this.doctors);
    }

    public Collection<Patient> getPatients() {
        return Collections.unmodifiableCollection(this.patients);
    }

    public boolean exist(Doctor d) {
        return this.doctors.contains(d);
    }

    public boolean exist(Patient p) {
        return this.patients.contains(p);
    }


    public Doctor removeDoctor(String name) {
        Doctor doctor = this.doctors.stream().filter(d -> d.name.equals(name)).findFirst().orElse(null);
        if (doctor == null) {
            throw new IllegalArgumentException();
        }

        for (Patient patient : this.map.get(doctor)) {
            this.patients.remove(patient);
            this.map2.remove(patient);
        }

        this.map.remove(doctor);
        this.doctors.remove(doctor);
        return doctor;
    }

    public void changeDoctor(Doctor from, Doctor to, Patient p) {
        if (!this.exist(from) || !this.exist(to) || !this.exist(p)) {
            throw new IllegalArgumentException();
        }

        for (Patient patient : this.map.get(from)) {
            this.map2.put(patient, to);
            this.map.get(to).add(patient);
        }
        this.map.get(from).clear();
    }

    public Collection<Doctor> getDoctorsByPopularity(int populariry) {
        return this.doctors.stream().filter(d -> d.popularity == populariry).collect(Collectors.toList());
    }

    public Collection<Patient> getPatientsByTown(String town) {
        return this.patients.stream().filter(p -> p.town.equals(town)).collect(Collectors.toList());
    }

    public Collection<Patient> getPatientsInAgeRange(int lo, int hi) {
        return this.patients.stream().filter(p -> p.age >= lo && p.age <= hi).collect(Collectors.toList());
    }

    public Collection<Doctor> getDoctorsSortedByPatientsCountDescAndNameAsc() {
        return this.doctors.stream()
                .sorted(Comparator.comparing((Doctor d) -> this.map.get(d).size()).reversed()
                        .thenComparing((Doctor d) -> d.name))
                .collect(Collectors.toList());
    }

    public Collection<Patient> getPatientsSortedByDoctorsPopularityAscThenByHeightDescThenByAge() {
        return this.patients.stream()
                .sorted(Comparator.comparing((Patient p) -> this.map2.get(p).popularity)
                        .thenComparing((Patient p) -> p.height).reversed()
                        .thenComparing((Patient p) -> p.age))
                .collect(Collectors.toList());
    }

}
