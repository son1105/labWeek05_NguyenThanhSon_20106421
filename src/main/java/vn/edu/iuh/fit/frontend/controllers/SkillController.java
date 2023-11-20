package vn.edu.iuh.fit.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.dto.SkillInformation;
import vn.edu.iuh.fit.backend.entities.*;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.ids.JobSkillId;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.backend.services.SkillService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class SkillController {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private SkillService skillService;
    @Autowired
    private JobSkillRepository jobSkillRepository;
//    @GetMapping("/listSkill")
//    public String getAll(Model model){
//        List<Skill> skills = skillRepository.findAll();
//        model.addAttribute("skills", skills);
//        return "skill/listSkill";
//    }
//
//    @GetMapping("/listSkill/companyId={companyId}")
//    public String getSkillByCompanyId(@PathVariable("companyId") long companyId, Model model){
//        Optional<Company> resultCompanyName = companyRepository.findById(companyId);
//        if(resultCompanyName.isPresent()){
//            System.out.println("OK!");
//            List<Skill> skillCompanyNeeds = skillService.findSkillByCompany(companyId);
//            Company company = resultCompanyName.get();
//            String companyName = company.getName();
//            model.addAttribute("companyName", companyName);
//            model.addAttribute("skillCompanyNeeds", skillCompanyNeeds);
//        }
//        return "skill/listSkillCompany";
//    }

    @GetMapping("/{jobId}/skills")
    public String showJobList(Model model, @PathVariable("jobId") long jobId){
        List<SkillInformation> skillInformation = skillService.getInfoSkillOfJob(jobId);
        Job job = jobRepository.findById(jobId).orElse(null);
        model.addAttribute("skills", skillInformation);
        model.addAttribute("job", job);
//        JobSkill jobSkill = jobSkillRepository.findById(new JobSkillId(job, skills));
        return "skill/listSkill";
    }

    @GetMapping("/{jobId}/skill/show-add-form")
    public ModelAndView addSkill(@PathVariable("jobId") long jobId){
        ModelAndView modelAndView = new ModelAndView();
        JobSkill jobSkill = new JobSkill();
        List<Skill> skillJobNotHave = skillRepository.notFindAllByJobId(jobId);
        Skill skill = new Skill();
        modelAndView.addObject("jobSkill", jobSkill);
        modelAndView.addObject("skills", skillJobNotHave);
        modelAndView.addObject("skillLevel", SkillLevel.values());
        modelAndView.addObject("skill", skill);
        modelAndView.addObject("jobId", jobId);
        modelAndView.setViewName("skill/addSkill");
        return modelAndView;
    }

    @PostMapping("/{jobId}/skill/addSkill")
    public String addSkill(@PathVariable("jobId") long jobId,
            @ModelAttribute("jobSkill") JobSkill jobSkill,
            @ModelAttribute("skill") Skill skill,
            BindingResult result, Model model
    ){
        Job job = jobRepository.findById(jobId).orElse(null);
        jobSkill.setSkill(skillRepository.findById(skill.getId()).orElse(null));
        jobSkill.setJob(job);
        jobSkillRepository.save(jobSkill);
        String uri;
        uri = "redirect:/"+jobId+"/skills";
        return uri;
    }

    @PostMapping("/{jobId}/{skillId}/deleteSkill")
    public String deleteSkill(@PathVariable("jobId") long jobId,
                              @PathVariable("skillId") long skillId
    ){
        System.out.println("\n\n\n\n\n\nOK");
        JobSkill jobSkill = jobSkillRepository.findById(new JobSkillId(jobId, skillId)).get();
        jobSkillRepository.delete(jobSkill);
        String uri;
        uri = "redirect:/"+jobId+"/skills";
        return uri;
    }
}
