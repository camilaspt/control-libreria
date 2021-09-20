/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.daos.sistemaLibreria;

import entidades.sistemaLibreria.Editorial;

/**
 *
 * @author camil
 */
public class EditorialDao extends Dao {

    public Editorial crearEditorial(Editorial editorial) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(editorial);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return editorial;
    }

    public Editorial modificarEditorial(Editorial editorial) {

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(editorial);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return editorial;
    }

    public Editorial eliminarEditorial(Editorial editorial) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(editorial);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return editorial;
    }

    public Editorial findByNombre(String nombre) {
        try {
            return (Editorial) entityManager.createQuery("select e from Editorial e where e.nombre =:nombre").setParameter("nombre", nombre).getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }
}
