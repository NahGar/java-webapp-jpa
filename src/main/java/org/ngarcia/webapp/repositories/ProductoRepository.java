package org.ngarcia.webapp.repositories;

import org.ngarcia.webapp.models.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto> {

   Producto porSku(String sku) throws Exception;
}
