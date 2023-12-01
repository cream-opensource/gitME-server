package gitME.user.service;

import gitME.entity.ExternalLink;
import gitME.entity.User;
import gitME.repository.ExternalLinkRepository;
import gitME.repository.UserRepository;
import gitME.user.dto.UserDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Transactional
    public void updateUserData(UserDataDTO userDataDTO) {
        try {
            User user = new User();
            user.setIdx(userDataDTO.getIdx());
            user.setKakaoId(userDataDTO.getKakaoId());
            user.setName(userDataDTO.getName());
            user.setBirthDate(userDataDTO.getBirthDate());
            user.setEmail(userDataDTO.getEmail());
            user.setPhone(userDataDTO.getPhone());
            userRepository.save(user);

            externalLinkRepository.deleteAllByUserIdx(userDataDTO.getIdx());

            Map<String, String> externalLinks = userDataDTO.getExternalLink();
            for (Map.Entry<String, String> entry : externalLinks.entrySet()) {

                ExternalLink externalLink = new ExternalLink();
                externalLink.setUserIdx(userDataDTO.getIdx());
                externalLink.setUrl(entry.getValue());
                externalLink.setDescription(entry.getKey());
                externalLinkRepository.save(externalLink);

            }
        } catch (Exception e) {
            log.error("", e);
            throw e;
        }
    }
}
