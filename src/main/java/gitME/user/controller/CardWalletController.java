package gitME.user.controller;

import gitME.entity.CardWallet;
import gitME.user.dto.CardWalletDTO;
import gitME.user.dto.TotalInfoDTO;
import gitME.user.service.CardWalletService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CardWalletController {

    private final CardWalletService cardWalletService;

    @PostMapping("/cardWallet")
    public ResponseEntity<String> submitCardWallet(@RequestBody CardWalletDTO cardWalletDTO) {
        try {
            cardWalletService.saveCardWallet(cardWalletDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("CardWallet post successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during CardWallet post");
        }

    }

    @GetMapping("/cardWallet/{userIdx}")
    public ResponseEntity<?> getCardWallet(@PathVariable("userIdx") int userIdx) {
        try {
            List<CardWallet> cardWallets = cardWalletService.getCardWalletByUserIdx(userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(cardWallets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during cardWallet get");
        }
    }

    @DeleteMapping("/cardWallet")
    public ResponseEntity<String> deleteCardWallet(@RequestBody CardWalletDTO cardWalletDTO) {
        try {
            cardWalletService.deleteCardWallet(cardWalletDTO);
            return ResponseEntity.status(HttpStatus.OK).body("CardWallet delete successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CardWallet not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during CardWallet delete");
        }
    }


}
