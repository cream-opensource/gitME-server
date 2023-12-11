package gitME.user.service;

import gitME.entity.CardWallet;
import gitME.entity.CardWalletId;
import gitME.repository.CardWalletRepository;
import gitME.user.dto.CardWalletDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardWalletService {

    private final CardWalletRepository cardWalletRepository;

    public void saveCardWallet(CardWalletDTO cardWalletDTO) {
        CardWallet cardWallet = new CardWallet();
        cardWallet.setUserIdx(cardWalletDTO.getUserIdx());
        cardWallet.setTargetUserIdx(cardWalletDTO.getTargetUserIdx());
        cardWallet.setTemplate(cardWalletDTO.getTemplate());
        cardWalletRepository.save(cardWallet);

    }

    public void deleteCardWallet(CardWalletDTO cardWalletDTO) {
        CardWalletId cardWalletId = new CardWalletId(cardWalletDTO.getUserIdx(), cardWalletDTO.getTargetUserIdx(), cardWalletDTO.getTemplate());

        Optional<CardWallet> optionalCardWallet = cardWalletRepository.findById(cardWalletId);

        if (optionalCardWallet.isPresent()) {
            cardWalletRepository.deleteById(cardWalletId);
            System.out.println("삭제가 완료되었습니다.");
        } else {
            throw new EntityNotFoundException("삭제할 항목이 없습니다.");
        }
    }


    public List<CardWallet> getCardWalletByUserIdx(int userIdx) {

        return cardWalletRepository.findByUserIdx(userIdx);
    }

}
