package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.entities.Candidate;
import vn.edu.iuh.fit.backend.entities.Company;
import vn.edu.iuh.fit.backend.services.CompanyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @GetMapping("/companies")
    public String showCompanyListPaging(Model model, @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size){
        int currPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Company> companyPage = companyService.findAll(currPage-1, pageSize, "id", "asc");
        model.addAttribute("companyPage", companyPage);
        int totalPage = companyPage.getTotalPages();
        if(totalPage > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "company/list";
    }
}
