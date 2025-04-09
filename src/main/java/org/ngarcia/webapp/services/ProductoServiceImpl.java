package org.ngarcia.webapp.services;

import jakarta.inject.Inject;
import org.ngarcia.webapp.configs.ProductoServicePrincipal;
import org.ngarcia.webapp.interceptors.TransactionalJpa;
import org.ngarcia.webapp.models.entities.*;
import org.ngarcia.webapp.repositories.*;

import java.util.List;
import java.util.Optional;
import org.ngarcia.webapp.configs.Service;
import org.ngarcia.webapp.interceptors.Logging;

//@ApplicationScoped
//para identificar entre esta clase y ProductoServiceImpl (ambos utilizan la misma interface)
//se puede hacer mediante Named o utilizando la clase ProductoServicePrincial (annotation)
//@Named("productoDefault")
@Service
@ProductoServicePrincipal
@TransactionalJpa
public class ProductoServiceImpl implements ProductoService {

   @Inject
   @RepositoryJpa
   private ProductoRepositoryJpaImpl repository;
   //private CrudRepository<Producto> repository;

   @Inject
   @RepositoryJpa
   private CrudRepository<Categoria> repositoryCategoria;

   //public ProductoServiceJdbcImpl(Connection conn) {
   //   this.repositoryJdbc = new ProductoRepositoryJdbcImpl(conn);
   //   this.repositoryCategoriaJdbc = new CategoriaRepositoryImpl(conn);
   //}

   @Override
   @Logging
   public List<Producto> listar() {
      try {
         return repository.listar();
      }
      catch (Exception e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public Optional<Producto> findOneByName(String name) {
      try {
         return repository.porNombre(name).stream().findFirst();
      }
      catch (Exception e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public List<Producto> findAllByName(String name) {
      return List.of();
   }

   @Override
   public Optional<Producto> findById(Long id) {
      try {
         return Optional.ofNullable(repository.porId(id));
      }
      catch (Exception e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public void guardar(Producto producto) {
      try {
         repository.guardar(producto);
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

   @Override
   public List<Categoria> listarCategoria() {
      try {
         return repositoryCategoria.listar();
      } catch (Exception e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public Optional<Categoria> findByIdCategoria(Long id) {
      try {
         return Optional.ofNullable(repositoryCategoria.porId(id));
      }
      catch (Exception e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public Optional<Producto> findBySku(String sku) {
      try {
         return Optional.ofNullable(repository.porSku(sku));
      }
      catch (Exception e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }
}
