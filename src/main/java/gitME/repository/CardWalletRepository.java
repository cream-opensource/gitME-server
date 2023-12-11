package gitME.repository;

import gitME.entity.CardWallet;
import gitME.entity.CardWalletId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardWalletRepository extends JpaRepository<CardWallet, CardWalletId> {
    List<CardWallet> findByUserIdx(int userIdx);
}
