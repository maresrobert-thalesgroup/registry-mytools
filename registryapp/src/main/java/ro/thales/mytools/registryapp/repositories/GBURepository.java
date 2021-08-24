package ro.thales.mytools.registryapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.thales.mytools.registryapp.entities.GBU;

@Repository
public interface GBURepository extends JpaRepository<GBU, Long> {
}
