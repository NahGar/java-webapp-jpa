package org.ngarcia.webapp.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.ngarcia.webapp.configs.RepositoryCDI;
import org.ngarcia.webapp.models.entities.Usuario;

import java.util.List;

@RepositoryJpa
@RepositoryCDI
public class UsuarioRepositoryJpaImpl implements UsuarioRepository {

    //está defindo en ProducerResources
    @Inject
    private EntityManager em;

    @Override
    public Usuario porUsername(String username) throws Exception {
        String sql = "select u from Usuario u where u.username = :username";
        return em.createQuery(sql,Usuario.class).setParameter("username",username).getSingleResult();
    }

    @Override
    public List<Usuario> listar() throws Exception {
        String sql = "from Usuario";
        return em.createQuery(sql, Usuario.class).getResultList();
    }

    @Override
    public Usuario porId(Long id) throws Exception {
        return em.find(Usuario.class,id);
    }

    @Override
    public List<Usuario> porNombre(String nombre) throws Exception {
        String sql = "select u from Usuario u where u.nombre like %:nombre%";
        return em.createQuery(sql, Usuario.class).setParameter("nombre",nombre).getResultList();
    }

    @Override
    public void guardar(Usuario usuario) throws Exception {
        if(usuario.getId() != null && usuario.getId() > 0) {
            em.merge(usuario);
        }
        else {
            em.persist(usuario);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        em.remove(porId(id));
    }
}
