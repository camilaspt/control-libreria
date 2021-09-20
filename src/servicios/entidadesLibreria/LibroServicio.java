/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.entidadesLibreria;

import entidades.daos.sistemaLibreria.AutorDao;
import entidades.daos.sistemaLibreria.EditorialDao;
import entidades.daos.sistemaLibreria.LibroDao;
import entidades.sistemaLibreria.Autor;
import entidades.sistemaLibreria.Libro;

import java.util.List;
import java.util.Scanner;

public class LibroServicio {

    private final Scanner read = new Scanner(System.in).useDelimiter("\n");
    LibroDao libroDao = new LibroDao();
    AutorDao autorDao = new AutorDao();
    EditorialDao editorialDao = new EditorialDao();
    AutorServicio autorServicio = new AutorServicio();
    EditorialServicio editorialServicio = new EditorialServicio();

    public void llenaLibro() {

        System.out.println("Ingrese el ISDBN");
        Long isdbn = read.nextLong();

        System.out.println("Ingrese el titulo del libro");
        String titulo = read.next();

        System.out.println("Ingrese el año de publicacion");
        Integer año = read.nextInt();

        System.out.println("Ingrese la cantidad de ejemplares");
        Integer ejemplares = read.nextInt();

        System.out.println("Ingrese la cantidad de prestados ");
        Integer prestados = read.nextInt();

        System.out.println("Ingrese el nombre del autor");
        String autor = read.next();
        if (verificaExistenciaAutor(autor)) {
            System.out.println("El autor no ha sido creado. Ingrese nuevamente el nombre");
            autorServicio.llenarAutor();
        }

        System.out.println("Ingrese el nombre de la editorial");
        String editorial = read.next();
        if (verificaExistenciaEditorial(editorial)) {
            System.out.println("La editorial no ha sido creada. Ingrese nuevamente el nombre");
            editorialServicio.llenarEditorial();
        }

        crearLibro(new Libro(isdbn, titulo, año, ejemplares, prestados, ejemplares - prestados, true, autorDao.findByNombre(autor), editorialDao.findByNombre(editorial)));
        System.out.println("Libro ingresado correctamente");
    }

    private Libro crearLibro(Libro libro) {
        return libroDao.crearLibro(libro);
    }

    private boolean verificaExistenciaAutor(String nombre) {
//        return autorDao.findByNombre(nombre).size() == 0;
        return autorDao.findByNombre(nombre) == null;

    }

    private boolean verificaExistenciaEditorial(String nombre) {
        return editorialDao.findByNombre(nombre) == null;
    }

    public void modificarLibro() throws Exception {
        try {
            System.out.println("Ingrese el titulo del libro a modificar");
            Libro libro = libroDao.findByTitulo(read.next());
            if (libro == null) {
                throw new Exception("El libro no se encuentra en la base de datos");
            }

            System.out.println("Nuevos datos:");
            if (modifica("titulo")) {
                System.out.println("Ingrese el nuevo titulo");
                libro.setTitulo(read.next());
            }
            if (modifica("ISBN")) {
                System.out.println("Ingrese el nuevo ISBN");
                libro.setIsbn(read.nextLong());
            }
            if (modifica("año")) {
                System.out.println("Ingrese el nuevo año");
                libro.setAño(read.nextInt());
            }
            if (modifica("ejemplares")) {
                System.out.println("Ingrese la nueva cantidad de ejemplares");
                libro.setEjemplares(read.nextInt());
            }
            if (modifica("ejemplares prestados")) {
                System.out.println("Ingrese la nueva cantidad de prestados");
                libro.setEjemplaresPrestados(read.nextInt());
            }
            if (modifica("autor")) {
                System.out.println("Ingrese el nuevo nombre del autor");
                String autor = read.next();
                if (verificaExistenciaAutor(autor)) {
                    System.out.println("El autor no ha sido creado. Ingrese nuevamente el nombre");
                    autorServicio.llenarAutor();
                }
                libro.setAutor(autorDao.findByNombre(autor));
            }

            if (modifica("editorial")) {
                System.out.println("Ingrese el nuevo nombre de la editorial");
                String editorial = read.next();
                if (verificaExistenciaEditorial(editorial)) {
                    System.out.println("La editorial no ha sido creada. Ingrese nuevamente el nombre");
                    editorialServicio.llenarEditorial();
                }
                libro.setEditorial(editorialDao.findByNombre(editorial));
            }

            libroDao.modificarLibro(libro);
        } catch (Exception e) {
            System.out.println("Ocurrio un error al realizar la modificacion" + e);
        }
    }

    private boolean modifica(String atributo) {

        System.out.println("Desea modificar la categoria: " + atributo + "S/N");
        String op = read.next().substring(0).toUpperCase();
        return op.equals("S");

    }

    public void eliminarLibro() {
        try {
            System.out.println("Ingrese el titulo del libro que desea eliminar");
            libroDao.eliminarLibro(libroDao.findByTitulo(read.next()));
        } catch (Exception e) {
            System.out.println("Ocurrio un error al realizar la eliminacion");
        }
    }

    public List<Libro> findAllByAutor(Autor autor) {
        List<Libro> lista = libroDao.findAllbyAutor(autor);
        mostrarLista(lista);
        return lista;
    }

    private void mostrarLista(List<Libro> lista) {
        for (Libro libro : lista) {
            System.out.println(libro);
        }
    }

    public void busquedaPorISBN() {
        System.out.println("Ingrese el ISBN del libro a buscar");
        Libro libro = libroDao.findByISBN(read.nextLong());
        encontroLibro(libro);
    }

    public void busquedaPorTitulo() {
        System.out.println("Ingrese el titulo del libro a buscar");
        Libro libro = libroDao.findByTitulo(read.next());
        encontroLibro(libro);
    }

    private void encontroLibro(Libro libro) {
        if (libro == null) {
            System.out.println("El libro no se encuentra en la base de datos");
        } else {
            System.out.println(libro);
        }
    }

    public void busquedaPorAutor() {
        System.out.println("Ingrese el nombre del autor a buscar");
        List<Libro> lista = libroDao.findAllbyAutor(autorDao.findByNombre(read.next()));
        mostrarLista(lista);
    }

    public void busquedaPorEditorial() {
        System.out.println("Ingrese el nombre de la editorial a buscar");
        List<Libro> lista = libroDao.findAllbyEditorial(editorialDao.findByNombre(read.next()));
        mostrarLista(lista);
    }

}
