package vn.edu.iuh.fit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.entities.CandidateSkill;
import vn.edu.iuh.fit.backend.entities.Job;
import vn.edu.iuh.fit.backend.entities.JobSkill;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    public Page<Job> getAll(int pageNo, int pageSize, String sortBy, String sortDirection, long comId){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        List<Job> jobs =  jobRepository.findAllByCompany_Id(comId);
        return new PageImpl<>(jobs, pageable, jobs.size());
    }

    public Page<Job> getAllJobForCandidate(int pageNo, int pageSize, String sortBy, String sortDirection, long candidateId){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        List<CandidateSkill> candidateSkills = candidateSkillRepository.findAllByCandidate(candidateRepository.findById(candidateId).get());
        List<JobSkill> js = new ArrayList<>();
        for (CandidateSkill candidateSkill: candidateSkills){
            List<JobSkill> jobSkills = jobSkillRepository.findAllBySkillAndSkillLevel(candidateSkill.getSkill(), candidateSkill.getSkillLevel());
            if(!jobSkills.isEmpty())
                js.addAll(jobSkills);
        }
        Set<Job> jobs = new HashSet<>();
        for(JobSkill jobSkill : js)
            jobs.add(jobSkill.getJob());
        int toIndex = pageSize*(pageNo+1);
        if(toIndex > js.size())
            toIndex = js.size();
        return new PageImpl<>(jobs.stream().toList().subList(pageSize*pageNo, toIndex), pageable, js.size());
    }
}
