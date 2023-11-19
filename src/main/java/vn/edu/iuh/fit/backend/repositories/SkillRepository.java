package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.entities.Skill;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query(value = "FROM Skill s WHERE s.id IN (SELECT js.job.id FROM JobSkill js WHERE js.job.id IN (SELECT j.id FROM Job j WHERE j.company.id =:comId))")
    List<Skill> findSkillByCompany(@Param("comId") long company_id);
}