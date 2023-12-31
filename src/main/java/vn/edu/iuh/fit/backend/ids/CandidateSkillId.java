package vn.edu.iuh.fit.backend.ids;

import jakarta.persistence.Embeddable;
import vn.edu.iuh.fit.backend.entities.Skill;
import vn.edu.iuh.fit.backend.entities.Candidate;

import java.io.Serializable;
import java.util.Objects;

public class CandidateSkillId implements Serializable {
    private long candidate;
    private long skill;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandidateSkillId that)) return false;
        return Objects.equals(candidate, that.candidate) && Objects.equals(skill, that.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidate, skill);
    }
}
