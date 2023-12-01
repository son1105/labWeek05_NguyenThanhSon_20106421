package vn.edu.iuh.fit;

import com.neovisionaries.i18n.CountryCode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.backend.entities.*;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.repositories.*;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class LabWeek05NguyenThanhSon20106421Application {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Autowired
    private JobRepository jobRepository;
    public static void main(String[] args) {
        SpringApplication.run(LabWeek05NguyenThanhSon20106421Application.class, args);
    }

//    @Bean
//    @Transactional
    CommandLineRunner initData() {
        return args -> {
            Random random = new Random();
            String[] diaChi = {"Quang Trung,TP.HCM", "Hai Bà Trưng,Hà Nội", "Trần Phú,Đà Nẵng", "Lê Lợi,Hải Phòng", "Võ Văn Kiệt,Cần Thơ"};
            Address address = null;
            Candidate candidate = null;
            Company company = null;
            Experience experience = null;
            Job job = null;
            Skill skill = null;
            CandidateSkill candidateSkill = null;
            JobSkill jobSkill = null;
            String[] ho = {"Nguyễn", "Trần", "Lê", "Võ", "Phạm", "Phan"};
            String[] tenLot = {"Văn", "Thanh", "Tấn"};
            String[] ten = {"Sơn", "Phong", "Toàn", "Lợi", "Việt"};
            for (int i = 1; i <= 100; i++) {
                address = new Address(i, diaChi[random.nextInt(5)].split(",")[1], CountryCode.VN, random.nextLong(1000000L, 9999999L) + "", diaChi[random.nextInt(5)].split(",")[0], random.nextInt(1000) + "");
                candidate = new Candidate(i, ho[random.nextInt(6)] + " " + tenLot[random.nextInt(3)] + " " + ten[random.nextInt(5)],
                        "candidate" + i + "@gmail.com", "09" + random.nextLong(10000000L, 99999999L),
                        LocalDate.of(2002, random.nextInt(1, 13), random.nextInt(1, 29)),
                        address, null, null);
                company = new Company(i, "Company " + i, "About company " + i, address, "company" + i + "@gmail.com",
                        "012" + random.nextLong(1000000L, 9999999L), "https://www.company" + i + ".com.vn", null);
                skill = new Skill(i, i % 3 == 0 ? SkillType.UNSPECIFIC : i % 3 == 1 ? SkillType.TECHNICAL_SKILL : SkillType.SOFT_SKILL, "Skill " + i, "Skill description " + i, null);
                addressRepository.save(address);
                candidateRepository.save(candidate);
                companyRepository.save(company);
                skillRepository.save(skill);
            }
            for (int i = 1; i <= 100; i++) {
                experience = new Experience(i, LocalDate.of(random.nextInt(1990, 2000), random.nextInt(1, 13), random.nextInt(1, 29)),
                        LocalDate.of(random.nextInt(2000, 2022), random.nextInt(1, 13), random.nextInt(1, 29)),
                        i % 2 == 0 ? "employee" : "manager", "company " + random.nextInt(100), "work description",
                        candidateRepository.findById(random.nextLong(1, 101)).get());
                experienceRepository.save(experience);
            }
            for (int i = 1; i <= 100; i++) {
                job = new Job("Job " + i, companyRepository.findById(random.nextLong(1, 101)).get(), "Description job " + i);
                jobRepository.save(job);
            }

            for (int i = 1; i <= 100; i++) {
                candidate = candidateRepository.findById(random.nextLong(1,101)).get();
                skill = skillRepository.findById(random.nextLong(1,101)).get();
                candidateSkill = new CandidateSkill(candidate, skill
                        , i%5==0? SkillLevel.BEGINER:i%5==1?SkillLevel.IMTERMEDIATE:i%5==2?SkillLevel.ADVANCED:i%5==3?SkillLevel.PROFESSIONAL:SkillLevel.MASTER,
                        "More information "+i);
                candidateSkillRepository.save(candidateSkill);
                job = jobRepository.findById(random.nextLong(1,101)).get();
                skill = skillRepository.findById(random.nextLong(1,101)).get();
                jobSkill = new JobSkill(job, skill, i % 9 == 0 ? SkillLevel.BEGINER : i % 9 == 2 ? SkillLevel.IMTERMEDIATE : i % 9 == 4 ? SkillLevel.ADVANCED : i % 9 == 6 ? SkillLevel.PROFESSIONAL : SkillLevel.MASTER,
                        "More information " + 1);
                jobSkillRepository.save(jobSkill);

            }
        };
    }
}
