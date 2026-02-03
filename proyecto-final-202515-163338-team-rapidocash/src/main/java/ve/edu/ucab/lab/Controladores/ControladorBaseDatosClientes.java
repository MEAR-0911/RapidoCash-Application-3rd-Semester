package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Cliente;
import ve.edu.ucab.lab.modelo.Cuenta;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.ImageIcon;

/**
 * En esta clase Controlador la cual genera una lista de objetos del tipo {@linkplain Cliente},
 * se extraen los datos de los clientes provenientes de la BD(Base de Datos) de la empresa. A su vez en esta
 * clase se hace uso del patrón de diseño Singleton.
 *
 * @author Antias Manuel
 * @version 2.14.1
 */

public class ControladorBaseDatosClientes {
    /**
     *  Atributo instanciaCliente, se creó dicho atributo de manera "Static" para asegurarnos que haya una sola copia de
     *  dicho atributo para todas las instancias de esta clase, cumpliendo con el patrón Singleton.
     */
    private static ControladorBaseDatosClientes instanciaCliente;
    /**
     * Atributo clientes, en este atributo de tipo Lista se guarda los objetos Clientes.
     */
    private Map<String, Cliente> clientes = new LinkedHashMap<>();
    /**
     *  Creamos un constructor privado para evitar que se creen múltiples instancias desde afuera y de esta manera
     *  darnos pasos a crear una única instancia global. A su vez se respeta el principio de responsabilidad
     *  única tras la llamada de la lógica para cargar a los clientes de la BD, para inicializarla.
     */
    private ControladorBaseDatosClientes() {
        cargarClientes();
    }

    private String rutaBaseDatos;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Método correspondiente a la extracción de datos de los Clientes, provenientes de la base de datos.
     * En este procedimiento, gracias a un While recorremos por completo el txt de la DB Administradores. Gracias al
     * ".hasNextLine" logramos determinar si hay más líneas de texo por recorrer. El".split" permite tomar todos los
     * datos antes de una coma y así poder dividir la información y guardarla en un arreglo para mejor manejo de datos.
     * Por último es enviado al modelo Cliente y {@linkplain Cuenta} para poder construir los objetos y al final lo
     * ingresamos en una lista de objetos "Cliente".
     */

    public void cargarClientes() {
        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("src/main/java/ve/edu/ucab/lab/baseDeDatos/DatosClientes",
                "src/main/java/Imagenes/ArchivoCorrupto.png","DatosClientes","ArchivoCorrupto.png");
        rutasDeEjecutable.rutas();
        rutaBaseDatos=rutasDeEjecutable.getRutaBaseDatos();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();
        try {
            File archivo1 = new File(rutaBaseDatos);
            Scanner lecturaArchiv = new Scanner(archivo1);
            while (lecturaArchiv.hasNextLine()) {
                String linea = lecturaArchiv.nextLine();
                String[] datos = linea.split(",");
                String minusculasTipCuenta;
                String minusculasEstadoCuenta;
                double saldo = Double.parseDouble(datos[8]);
                minusculasTipCuenta = datos[6].toLowerCase(Locale.ROOT);
                minusculasEstadoCuenta = datos[7].toLowerCase(Locale.ROOT);

                Cuenta cuent = new Cuenta(minusculasTipCuenta, minusculasEstadoCuenta, saldo);
                Cliente client = new Cliente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], cuent);
                clientes.put(datos[4], client);
            }
            lecturaArchiv.close();
        } catch (FileNotFoundException e) {
            ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\">¡Archivo no encontrado o Corrupto!</p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);

        }
    }

    /**
     * Getter instanciaCliente.
      * @return instanciaCliente, valor del atributo estático "instancia" del controlador de la base de datos de los
     *                            clientes. Con este getter conseguimos que la instancia se realice una única vez
     *                            durante toda la ejecución de programa.
     */
    public static ControladorBaseDatosClientes getInstanciaCliente() {
        if (instanciaCliente == null) {
            instanciaCliente = new ControladorBaseDatosClientes();
        }
        return instanciaCliente;
    }

    /**
     *Getter clientes.
     * @return clientes, valor del atributo tipo lista "clientes" del Controlador de la base de datos de los Clientes.
     */
    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    /**
     *Setter clientes.
     * @param clientes parámetro clientes para modificar en el atributo de la base de datos de clientes.
     */
    public void setClientes(Map<String, Cliente> clientes) {
        this.clientes = clientes;
    }

}

