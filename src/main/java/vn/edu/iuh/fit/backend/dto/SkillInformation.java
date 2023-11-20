package vn.edu.iuh.fit.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.backend.entities.Skill;
import vn.edu.iuh.fit.backend.enums.SkillLevel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkillInformation {
    Skill skill;
    SkillLevel skillLevel;
    private String moreInfo;
}
