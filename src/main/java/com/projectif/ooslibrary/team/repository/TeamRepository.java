package com.projectif.ooslibrary.team.repository;

import com.projectif.ooslibrary.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
