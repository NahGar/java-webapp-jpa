package org.ngarcia.webapp.repositories;

import java.util.List;

public interface CrudRepository<T> {

   List<T> listar() throws Exception;
   T porId(Long id) throws Exception;
   List<T> porNombre(String nombre) throws Exception;
   void guardar(T t) throws Exception;
   void eliminar(Long id) throws Exception;

}
