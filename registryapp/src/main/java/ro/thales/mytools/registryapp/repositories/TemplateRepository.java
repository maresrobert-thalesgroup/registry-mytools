package ro.thales.mytools.registryapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.thales.mytools.registryapp.entities.Template;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template,Integer> {
    @Query(value = "SELECT * FROM templates t WHERE t.request_by_id = :id",
            nativeQuery = true)
    List<Template> findAllTemplatesByUserId(@Param("id") Integer id);
}
