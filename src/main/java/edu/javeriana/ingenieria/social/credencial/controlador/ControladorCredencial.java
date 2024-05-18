package edu.javeriana.ingenieria.social.credencial.controlador;

import edu.javeriana.ingenieria.social.credencial.dominio.Credencial;
import edu.javeriana.ingenieria.social.credencial.servicio.ServicioCredencial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credenciales")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorCredencial {
    @Autowired
    private ServicioCredencial servicio;

    @GetMapping("listar")
    public List<Credencial> obtenerCredenciales(){
        return servicio.obtenerCredenciales();
    }
    @GetMapping("obtenerCredencialesRol")
    public ResponseEntity<List<Credencial>> obtenerCredenciales(@RequestParam String rol){
        if(rol == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerCredenciales(rol).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerCredenciales(rol), HttpStatus.OK);
    }

    @GetMapping("obtener")
    public ResponseEntity<Credencial> obtenerCredencial(Integer codigo){
        if(codigo == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!servicio.credencialExiste(codigo)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerCredencial(codigo), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<Credencial> crearCredencial(@RequestBody Credencial credencial){
        if(credencial == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.credencialExistePorCodigo(credencial.getCodigo())||servicio.credencialExiste(credencial.getId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearCredencial(credencial), HttpStatus.CREATED);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> eliminarCredencial(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.borrarCredencial(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
