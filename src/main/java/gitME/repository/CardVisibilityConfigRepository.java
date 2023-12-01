package gitME.repository;

import gitME.entity.CardVisibilityConfig;
import gitME.entity.CodeStack;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * File: CardVisibilityConfigRepository
 * Author: making
 * Date: 2023-11-29
 * Desc: 카드 가시성 레포지토리
 */
public interface CardVisibilityConfigRepository extends JpaRepository<CardVisibilityConfig, Integer> {
}
