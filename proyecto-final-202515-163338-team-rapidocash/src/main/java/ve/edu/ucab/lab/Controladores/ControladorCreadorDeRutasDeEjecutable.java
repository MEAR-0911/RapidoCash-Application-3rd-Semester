package ve.edu.ucab.lab.Controladores;

public class ControladorCreadorDeRutasDeEjecutable {
    private String rutaBaseDatos;
    private String rutaImagenes;
    private String rutaEjecutable;
    private boolean ejecutandoDesdeJar;
    private String archivo;
    private String imag;

    public ControladorCreadorDeRutasDeEjecutable(String rutaBaseDatos, String rutaImagenes,String archivo,String imag) {
        this.rutaBaseDatos = rutaBaseDatos;
        this.rutaImagenes = rutaImagenes;
        this.archivo = archivo;
        this.imag = imag;
        /**
         * Ruta ejecutable se encarga de traer el path de la ruta de la ejecución del programa.
         */
        rutaEjecutable= getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        /**
         * Creamos un boolean que se encarga de verificar si el entorno de ejcución es .exe
         */
        ejecutandoDesdeJar = rutaEjecutable.endsWith(".exe");
    }

    public void rutas(){
        if (ejecutandoDesdeJar) {
            /**
             * Si el entorno de ejecución es .exe entonces modificamos la ruta para la ecución de la misma.
             */
            rutaBaseDatos = "baseDeDatos/"+archivo;
            rutaImagenes = "Imagenes/"+imag;
        }
    }

    public String getRutaBaseDatos() {
        return rutaBaseDatos;
    }

    public String getRutaImagenes() {
        return rutaImagenes;
    }
}
