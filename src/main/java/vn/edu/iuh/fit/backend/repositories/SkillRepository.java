package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.entities.JobSkill;
import vn.edu.iuh.fit.backend.entities.Skill;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query("from Skill s where s.id in (select js.skill.id FROM JobSkill js where js.job.id = :jobId)")
    List<Skill> findAllByJobId(@Param("jobId") long jobId);

    @Query("from Skill s where s.id not in (select js.skill.id FROM JobSkill js where js.job.id = :jobId)")
    List<Skill> notFindAllByJobId(@Param("jobId") long jobId);
}