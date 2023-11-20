package vn.edu.iuh.fit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.dto.SkillInformation;
import vn.edu.iuh.fit.backend.entities.JobSkill;
import vn.edu.iuh.fit.backend.entities.Skill;
import vn.edu.iuh.fit.backend.ids.JobSkillId;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    public List<SkillInformation> getInfoSkillOfJob(long jobId){
        List<Skill> skills = skillRepository.findAllByJobId(jobId);
        List<SkillInformation> skillInformation = new ArrayList<>();
        for (Skill skill : skills){
            JobSkill jobSkill = jobSkillRepository.findById(new JobSkillId(jobId, skill.getId())).get();
            skillInformation.add(new SkillInformation(skill, jobSkill.getSkillLevel(), jobSkill.getMoreInfo()));
        }
        return skillInformation;
    }
}
