package org.ngarcia.webapp.services;

import org.ngarcia.webapp.models.entities.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
   List<Curso> listar();
   List<Curso> porNombre(String nombre);
   Optional<Curso> porId(Integer id);
   void guardar(Curso curso);
   void eliminar(Integer id);
}
