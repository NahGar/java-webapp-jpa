package org.ngarcia.webapp.services;

import jakarta.inject.Inject;
import org.ngarcia.webapp.configs.Service;
import org.ngarcia.webapp.interceptors.TransactionalJpa;
import org.ngarcia.webapp.models.entities.Usuario;
import org.ngarcia.webapp.repositories.*;

import java.util.*;

//@ApplicationScoped
@Service
@TransactionalJpa
public class UsuarioServiceImpl implements UsuarioService {

   private UsuarioRepository repository;

   //public UsuarioServiceImpl(Connection conn) {
   //   this.repository = new UsuarioRepositoryImpl(conn);
   //}

   @Inject
   public UsuarioServiceImpl(@RepositoryJpa UsuarioRepository usuarioRepository) {
      this.repository = usuarioRepository;
   }

   @Override
   public Optional<Usuario> login(String username, String password) {

      try {
         //falla cuando no existe el usuario porque intenta hacer filter en un null
         //return Optional.ofNullable(repository.porUsername(username))
         //        .filter( u -> u.getPassword().equals(password));
         Usuario usuario = repository.porUsername(username);
         if(usuario != null && usuario.getPassword().equals(password)) {
            return Optional.of(usuario);
         }
         return Optional.empty();
      } catch (Exception e) {
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public List<Usuario> listar() {
      try {
         return repository.listar();
      }
      catch (Exception e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public Optional<Usuario> porUsername(String username) {
      try {
         return Optional.ofNullable(repository.porUsername(username));
      }
      catch (Exception e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public Optional<Usuario> porId(Long id) {
      try {
         return Optional.ofNullable(repository.porId(id));
      }
      catch (Exception e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public void guardar(Usuario u) {
      try {
         repository.guardar(u);
      } catch (Exception e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public void eliminar(Long id) {
      try {
         repository.eliminar(id);
      } catch (Exception e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }
}
