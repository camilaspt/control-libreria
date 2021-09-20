package sistemaLibreria;

import servicios.entidadesLibreria.EjecucionServicio;

public class MainSistemaLibreria {

    public static void main(String[] args) throws Exception {
        EjecucionServicio servicio = new EjecucionServicio();
        servicio.ejecutar();
    }
}
