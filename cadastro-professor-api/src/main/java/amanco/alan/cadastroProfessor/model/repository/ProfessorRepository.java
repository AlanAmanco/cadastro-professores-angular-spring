package amanco.alan.cadastroProfessor.model.repository;

import amanco.alan.cadastroProfessor.model.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor,Integer> {

    @Query("SELECT p FROM Professor p WHERE p.documentosEntregues = true")
    List<Professor> findAllDocumentosEntregues();

}
