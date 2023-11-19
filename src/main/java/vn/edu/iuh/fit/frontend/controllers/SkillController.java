package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.entities.Company;
import vn.edu.iuh.fit.backend.entities.Skill;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.backend.services.SkillService;

import java.util.List;
import java.util.Optional;

@Controller
public class SkillController {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SkillService skillService;
    @GetMapping("/listSkill")
    public String getAll(Model model){
        List<Skill> skills = skillRepository.findAll();
        model.addAttribute("skills", skills);
        return "skill/listSkill";
    }

    @GetMapping("/listSkill/companyId={companyId}")
    public String getSkillByCompanyId(@PathVariable("companyId") long companyId, Model model){
        Optional<Company> resultCompanyName = companyRepository.findById(companyId);
        if(resultCompanyName.isPresent()){
            System.out.println("OK!");
            List<Skill> skillCompanyNeeds = skillService.findSkillByCompany(companyId);
            Company company = resultCompanyName.get();
            String companyName = company.getName();
            model.addAttribute("companyName", companyName);
            model.addAttribute("skillCompanyNeeds", skillCompanyNeeds);
        }
        return "skill/listSkillCompany";
    }
}
