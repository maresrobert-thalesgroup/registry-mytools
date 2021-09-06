package ro.thales.mytools.registryapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.thales.mytools.registryapp.entities.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template,Integer> {
}
