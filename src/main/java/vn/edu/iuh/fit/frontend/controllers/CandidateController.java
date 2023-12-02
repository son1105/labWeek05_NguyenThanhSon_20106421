package vn.edu.iuh.fit.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.entities.Address;
import vn.edu.iuh.fit.backend.entities.Candidate;
import vn.edu.iuh.fit.backend.entities.CandidateSkill;
import vn.edu.iuh.fit.backend.entities.Skill;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.backend.services.CandidateService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    @GetMapping("/list")
    public String showCandidateList(Model model){
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidate/list-no-paging";
    }

    @GetMapping("/candidates")
    public String showCandidateListPaging(Model model, @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size){
        int currPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Candidate> candidatePage = candidateService.findAll(currPage-1, pageSize, "id", "asc");
        model.addAttribute("candidatePage", candidatePage);
        int totalPage = candidatePage.getTotalPages();
        if(totalPage > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidate/list";
    }

    @GetMapping("/show-add-form")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        Candidate candidate = new Candidate();
        candidate.setAddress(new Address());
        modelAndView.addObject("candidate", candidate);
        modelAndView.addObject("address", candidate.getAddress());
        modelAndView.addObject("countries", CountryCode.values());
        modelAndView.setViewName("candidate/addCandidate");
        return modelAndView;
    }
    @PostMapping("candidates/addCandidate")
    public String addCandidate(
            @ModelAttribute("candidate") Candidate candidate,
            @ModelAttribute("address") Address address,
            BindingResult result, Model model
    ){
        addressRepository.save(address);
        candidate.setAddress(address);
        candidateRepository.save(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/show-edit-form/{id}")
    public ModelAndView edit(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        Optional<Candidate> opt = candidateRepository.findById(id);
        if(opt.isPresent()){
            Candidate candidate = opt.get();
            modelAndView.addObject("candidate", candidate);
            modelAndView.addObject("address", candidate.getAddress());
            modelAndView.addObject("countries", CountryCode.values());
            modelAndView.setViewName("candidate/updateCandidate");
        }
        return modelAndView;
    }

    @PostMapping("candidates/updateCandidate")
    public String updateCandidate(
            @ModelAttribute("candidate") Candidate candidate,
            @ModelAttribute("address") Address address,
            BindingResult result, Model model
    ){
        addressRepository.save(address);
        candidate.setAddress(address);
        candidateRepository.save(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/candidates/show-form-login")
    public ModelAndView showFormLogIn(@RequestParam("note") Optional<String> note){
        ModelAndView modelAndView = new ModelAndView();
        Candidate candidate = new Candidate();
        modelAndView.addObject("note", note.orElse(null));
        modelAndView.addObject("candidate", candidate);
        modelAndView.setViewName("candidate/login");
        return modelAndView;
    }

    @GetMapping("/candidates/login")
    public String logIn(@RequestParam("id") long id, HttpSession session, Model model){
        Optional<Candidate> otp = candidateRepository.findById(id);
        if(otp.isPresent()){
            session.setAttribute("candidate", otp.get());
            return "redirect:/companies";
        }else{
            return "redirect:/candidates/show-form-login?note=Log Fail!";
        }
    }

    @GetMapping("/candidate")
    public String getInfo(Model model, HttpSession session){
        Object obj = session.getAttribute("candidate");
        if(obj != null){
            Candidate candidate = (Candidate) obj;
            List<CandidateSkill> candidateSkills = candidateSkillRepository.findAllByCandidate(candidate);
            model.addAttribute("candidate", candidate);
            model.addAttribute("candidateSkills", candidateSkills);
        }
        return "candidate/candidate";
    }
}
