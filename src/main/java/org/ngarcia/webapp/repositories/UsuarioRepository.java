package org.ngarcia.webapp.repositories;

import org.ngarcia.webapp.models.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario> {

   Usuario porUsername(String username) throws Exception;

}
