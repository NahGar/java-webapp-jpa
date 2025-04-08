package org.ngarcia.webapp.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.ngarcia.webapp.configs.RepositoryCDI;
import org.ngarcia.webapp.models.entities.Categoria;

import java.util.List;

@RepositoryJpa
@RepositoryCDI
public class CategoriaRepositoryJpaImpl implements CrudRepository<Categoria> {

    //está defindo en ProducerResources
    @Inject
    private EntityManager em;

    @Override
    public List<Categoria> listar() throws Exception {
        String sql = "from Categoria";
        return em.createQuery(sql, Categoria.class).getResultList();
    }

    @Override
    public Categoria porId(Long id) throws Exception {
        return em.find(Categoria.class,id);
    }

    @Override
    public List<Categoria> porNombre(String nombre) throws Exception {
        String sql = "select c from Categoria c where c.nombre like %:nombre%";
        return em.createQuery(sql, Categoria.class).setParameter("nombre",nombre).getResultList();
    }

    @Override
    public void guardar(Categoria categoria) throws Exception {
        if(categoria.getId() != null && categoria.getId() > 0) {
            em.merge(categoria);
        }
        else {
            em.persist(categoria);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        em.remove(porId(id));
    }
}
