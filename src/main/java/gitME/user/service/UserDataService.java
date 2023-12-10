package gitME.user.service;

import gitME.entity.ExternalLink;
import gitME.entity.Skill;
import gitME.entity.User;
import gitME.repository.ExternalLinkRepository;
import gitME.repository.SkillRepository;
import gitME.repository.UserRepository;
import gitME.user.dto.UserDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * File: UserDataService
 * Author: making
 * Date: 2023-12-01
 * Desc:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserDataService {

    private final UserRepository userRepository;
    private final ExternalLinkRepository externalLinkRepository;
    private final SkillRepository skillRepository;

    @Transactional
    public void updateUserData(UserDataDTO userDataDTO) {
        try {

            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = dateFormat.format(now);

            User user = new User();
            user.setIdx(userDataDTO.getUserIdx());
            user.setKakaoId(userDataDTO.getKakaoId());
            user.setName(userDataDTO.getName());
            user.setBirthDate(userDataDTO.getBirthDate());
            user.setEmail(userDataDTO.getEmail());
            user.setPhone(userDataDTO.getPhone());
            user.setIntroduction(userDataDTO.getIntroduction());
            user.setUpdateDate(dateString);
            userRepository.save(user);

            externalLinkRepository.deleteAllByUserIdx(userDataDTO.getUserIdx());

            Map<String, String> externalLinks = userDataDTO.getExternalLink();
            for (Map.Entry<String, String> entry : externalLinks.entrySet()) {

                ExternalLink externalLink = new ExternalLink();
                externalLink.setUserIdx(userDataDTO.getUserIdx());
                externalLink.setUrl(entry.getValue());
                externalLink.setDescription(entry.getKey());
                externalLinkRepository.save(externalLink);
            }

            skillRepository.deleteAllByUserIdx(userDataDTO.getUserIdx());

            Map<String, String> skillMap = userDataDTO.getSkill();
            if (!skillMap.isEmpty()) {
                String language = skillMap.keySet().stream().findFirst().get();
                String detail = skillMap.values().stream().findFirst().get();

                Skill skill = new Skill();
                skill.setUserIdx(user.getIdx());
                skill.setLanguage(language);
                skill.setDetail(detail);
                skill.setProficiency(userDataDTO.getSkillProficiency());
                skillRepository.save(skill);
            }



        } catch (Exception e) {
            log.error("", e);
            throw e;
        }
    }
}
