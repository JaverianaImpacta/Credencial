package edu.javeriana.ingenieria.social.credencial.servicio;

import edu.javeriana.ingenieria.social.credencial.dominio.Credencial;
import edu.javeriana.ingenieria.social.credencial.repositorio.RepositorioCredencial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCredencial {
    @Autowired
    private RepositorioCredencial repositorio;

    public List<Credencial> obtenerCredenciales(){
        return repositorio.findAll();
    }

    public List<Credencial> obtenerCredenciales(String rol){
        return repositorio.findALlByRol(rol);
    }

    public Credencial obtenerCredencial(Integer codigo){
        return repositorio.findOneByCodigo(codigo);
    }

    public Credencial crearCredencial(Credencial credencial){
        return repositorio.save(credencial);
    }

    public Credencial borrarCredencial(Integer id){
        Credencial aux = repositorio.findById(id).orElse(null);
        if(aux == null){
            return null;
        }
        repositorio.delete(aux);
        return aux;
    }

    public boolean credencialExiste(Integer id){
        return repositorio.existsById(id);
    }

    public boolean credencialExistePorCodigo(Integer codigo){
        return repositorio.existsByCodigo(codigo);
    }
}
