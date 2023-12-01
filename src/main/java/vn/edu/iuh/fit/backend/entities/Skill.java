package vn.edu.iuh.fit.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.backend.enums.SkillType;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "skill")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", length = 20)
    private long id;
    @Column(name = "skill_type")
    private SkillType skillType;
    @Column(name = "skill_name", length = 150)
    private String skillName;
    @Column(name = "skill_desc", length = 300)
    private String skillDesc;
    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<JobSkill> jobSkills;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill skill)) return false;
        return getId() == skill.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
