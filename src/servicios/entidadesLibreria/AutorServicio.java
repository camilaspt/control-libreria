package servicios.entidadesLibreria;

import entidades.daos.sistemaLibreria.AutorDao;
import entidades.sistemaLibreria.Autor;
import java.util.Scanner;

public class AutorServicio {

    AutorDao autorDao = new AutorDao();
    private Scanner read = new Scanner(System.in).useDelimiter("\n");

    public Autor crearAutor(Autor autor) {
        return autorDao.crearAutor(autor);
    }

    public void llenarAutor() {

        System.out.println("Ingrese el nombre del autor");
        String nombre = read.next();
        crearAutor(new Autor(Integer.SIZE, nombre, true));

    }

    public Autor elegirAutor() {

        System.out.println("Ingrese el nombre del autor");
        Autor autor = autorDao.findByNombre(read.next());
        if (autor == null) {
            System.out.println("El autor ingresado no existe. Ingrese uno nuevamente");
            autor = autorDao.findByNombre(read.next());
        }
        return autor;
    }

    public void modificarAutor() {
        try {
            System.out.println("Ingrese el nombre del autor que desea modificar");
            Autor autor = autorDao.findByNombre(read.next());
            System.out.println("Ingrese el numero nombre del autor");
            autor.setNombre(read.next());
            autorDao.modificarAutor(autor);
        } catch (Exception e) {
            System.out.println("Ocurrio un error al realizar la modificacion");
        }

    }

    public void eliminarAutor() {
        try {
            System.out.println("Ingrese el nombre del autor a eliminar");
            Autor autor = autorDao.findByNombre(read.next());
            autorDao.eliminarAutor(autor);
        } catch (Exception e) {
            System.out.println("Ocurrio un error al realizar la eliminacion");
        }

    }

    public void busquedaPorNombre() {
        System.out.println("Ingrese el nombre del autor");
        Autor autor = autorDao.findByNombre(read.next());
        if (autor == null) {
            System.out.println("No existe ese autor creado en la base de datos");
        } else {
            System.out.println(autor);
        }
    }
//Ciudad mardel = ciudadServicio.crearCiudad(new Ciudad("Mar del Plata"));
//public Ciudad crearCiudad(Ciudad ciudad) {
//   return ciudadDao.crearCiudad(ciudad);
//  }
}
