package org.ngarcia.webapp.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.ngarcia.webapp.configs.RepositoryCDI;
import org.ngarcia.webapp.models.entities.Producto;

import java.util.List;

@RepositoryJpa
@RepositoryCDI
public class ProductoRepositoryJpaImpl implements CrudRepository<Producto> {

    //está defindo en ProducerResources
    @Inject
    private EntityManager em;

    @Override
    public List<Producto> listar() throws Exception {
        String sql = "from Producto";
        return em.createQuery(sql, Producto.class).getResultList();
    }

    @Override
    public Producto porId(Long id) throws Exception {
        return em.find(Producto.class, id);
    }

    @Override
    public List<Producto> porNombre(String nombre) throws Exception {
        String sql = "select p from Producto p where p.nombre like %:nombre%";
        return em.createQuery(sql, Producto.class).setParameter("nombre",nombre).getResultList();
    }

    @Override
    public void guardar(Producto producto) throws Exception {
        if(producto.getId() != null && producto.getId() > 0) {
            em.merge(producto);
        }
        else {
            em.persist(producto);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        em.remove(porId(id));
    }
}
