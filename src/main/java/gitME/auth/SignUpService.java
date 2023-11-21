package gitME.auth;

import gitME.uncategorized.dto.SignUpDataDTO;
import org.springframework.stereotype.Service;

/**
 * Class SignUpService.
 * Created by 82105 on 2023-11-17.
 */
@Service
public class SignUpService {

    public void createUser(SignUpDataDTO signUpDataDTO){
        System.out.println("signUpDataDTO = " + signUpDataDTO);
    }
}
