package edu.javeriana.ingenieria.social.credencial.repositorio;

import edu.javeriana.ingenieria.social.credencial.dominio.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioCredencial extends JpaRepository<Credencial, Integer> {
    List<Credencial> findALlByRol(String rol);

    Credencial findOneByCodigo(Integer codigo);

    boolean existsByCodigo(Integer codigo);
}
