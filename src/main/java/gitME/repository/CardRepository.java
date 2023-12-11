package gitME.repository;

import gitME.entity.Card;
import gitME.entity.CardId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, CardId> {
    List<Card> findAllByUserIdx(int userIdx);
}
