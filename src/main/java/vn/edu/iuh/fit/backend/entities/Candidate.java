package vn.edu.iuh.fit.backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "candidate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "can_id", length = 20)
    private long id;
    private String fullName;
    private String email;
    @Column(length = 15)
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    private Address address;
    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CandidateSkill> candidateSkills;
    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Experience> experiences;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate candidate)) return false;
        return getId() == candidate.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
