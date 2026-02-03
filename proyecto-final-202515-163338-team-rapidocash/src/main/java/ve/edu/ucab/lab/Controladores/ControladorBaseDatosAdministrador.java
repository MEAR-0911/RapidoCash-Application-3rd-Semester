package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Administrador;
import ve.edu.ucab.lab.modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

/**
 * En esta clase Controlador la cual genera una lista de objetos del tipo {@linkplain Administrador},
 * se extrae los datos de los administradores provenientes de nuestra base de datos de la empresa. A su vez en esta
 * clase se hace uso del patrón de diseño Singleton.
 *
 * @author Antias Manuel
 * @version 2.11.1
 */

public class ControladorBaseDatosAdministrador {
    /**
     * Atributo instanciaAdministrador, se creó dicho atributo de manera "Static" para asegurarnos que haya una sola
     * copia de dicho atributo para todas las instancias de esta clase, cumpliendo con el patrón Singleton.
     */
    private static ControladorBaseDatosAdministrador instanciaAdministrador;
    /**
     * Atributo administradores, en este atributo de tipo Lista se guarda los objetos Administradores.
     */
    private Map<String, Administrador> administradores = new LinkedHashMap<>();
    /**
     *  Creamos un constructor privado para evitar que se creen múltiples instancias desde afuera y de esta manera
     *  darnos pasos a crear una única instancia global. A su vez se respeta el principio de responsabilidad
     *  única tras la llamada de la lógica para cargar a los administradores de la BD, para inicializarla.
     */
    private ControladorBaseDatosAdministrador() {
        cargarAdministradores();
    }

    private String rutaBaseDatos;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Método correspondiente a la extracción de datos de los Administradores, provenientes de la base de datos.
     * En este procedimiento, gracias a un While recorremos por completo el txt de la DB Administradores. Gracias al
     * ".hasNextLine" logramos determinar si hay más líneas de texo por recorrer. El".split" permite tomar todos los
     * datos antes de una coma y así poder dividir la información y guardarla en un arreglo para mejor manejo de datos.
     * Por último es enviado al modelo Administrador para poder construir el objeto y al final lo ingresamos en una
     * lista de objetos "Administradores".
     */
    public void cargarAdministradores()  {
        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("src/main/java/ve/edu/ucab/lab/baseDeDatos/DatosAdmis",
                "src/main/java/Imagenes/ArchivoCorrupto.png","DatosAdmis","ArchivoCorrupto.png");
        rutasDeEjecutable.rutas();
        rutaBaseDatos=rutasDeEjecutable.getRutaBaseDatos();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();
        try {
            File archivo2 = new File(rutaBaseDatos);
            Scanner lecturaArchiv = new Scanner(archivo2);
            while (lecturaArchiv.hasNextLine()) {
                String linea = lecturaArchiv.nextLine();
                String[] datos = linea.split(",");
                String minusculasEstadoCuenta;
                minusculasEstadoCuenta = datos[6].toLowerCase(Locale.ROOT);

                Administrador admi = new Administrador(datos[0],datos[1],datos[2],datos[3],datos[4],datos[5],minusculasEstadoCuenta );
                administradores.put(datos[4],admi);
            }
            lecturaArchiv.close();
        } catch (FileNotFoundException e) {
            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\">¡Archivo no encontrado o Corrupto!</p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE,icono);

        }
    }

    /**
     * Getter instanciaAdministrador.
     * @return instanciaCliente, valor del atributo estático "instancia" del controlador de la base de datos de los
     *                            clientes. Con este getter conseguimos que la instancia se realice una única vez
     *                            durante toda la ejecución de programa.
     */
    public static ControladorBaseDatosAdministrador getInstanciaAdministrador() {
        if (instanciaAdministrador == null) {
            instanciaAdministrador = new ControladorBaseDatosAdministrador();
        }
        return instanciaAdministrador;
    }

    /**
     * Getter administradores.
     * @return administradores, valor del atributo tipo lista "administradores" del Controlador de la base de datos de
     *                          los Administradores.
     */
    public Map<String, Administrador> getAdministradores() {
        return administradores;
    }

    /**
     * Setter administradores.
     * @param administradores parámetro administradores para modificar en el atributo de la base de datos de
     *                        administradores.
     */
    public void setAdministradores(Map<String, Administrador> administradores) {
        this.administradores = administradores;
    }

}
