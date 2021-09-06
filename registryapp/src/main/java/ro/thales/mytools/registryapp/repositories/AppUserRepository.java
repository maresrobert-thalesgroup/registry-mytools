package ro.thales.mytools.registryapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.thales.mytools.registryapp.entities.AppUser;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    @Query(value = "SELECT * FROM APP_USER WHERE TEAM_ID = :id_team AND ROLE = 'ROLE_MANAGER'", nativeQuery = true)
    Optional<AppUser> findManagerByTeam(@Param("id_team") Long id);

    @Query(value = "SELECT * FROM APP_USER WHERE TEAM_ID = :id_team", nativeQuery = true)
    Optional<List<AppUser>> getAllUsersInTeam(@Param("id_team") Long id);
}
