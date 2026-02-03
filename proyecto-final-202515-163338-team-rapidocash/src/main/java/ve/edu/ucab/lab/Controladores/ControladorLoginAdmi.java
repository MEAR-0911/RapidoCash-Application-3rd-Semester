package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Administrador;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * En esta clase Controlador se llevarán a cabo las validaciones pertinentes para el ingreso de los administradores
 * al programa. A su vez esta clase hace uso de la instancia de la clase {@linkplain ControladorBaseDatosAdministrador},
 * para poder acceder a la lista de objetos Administradores.
 *
 * @author Antias Manuel
 * @version 2.11.2
 */

public class ControladorLoginAdmi {
    /**
     * Atributo controladorAdmis, se creó un objeto de la clase {@linkplain ControladorBaseDatosAdministrador} para
     * llamar a la instancia de la clase sigleton para así luego hacer uso de sus métodos.
     */
    ControladorBaseDatosAdministrador controladorAdmis=ControladorBaseDatosAdministrador.getInstanciaAdministrador();
    /**
     * Atributo administradores, en este atributo se guarda una lista de objetos {@linkplain Administrador}, dicha lista
     * de tipo Hashmap será llenada con la lista proveniente de los métodos de la clase
     * {@linkplain ControladorBaseDatosAdministrador}.
     */
    private Map<String, Administrador> administradores = new LinkedHashMap<>();    /**
    /**
     * Atributo nombreUsuario, en este atributo se guarda el nombre de usuario ingresado por el Administrador.
     */
    private String nombreUsuario;
    /**
     * Atributo pinAdmi, en este atributo se guarda el pin ingresado por el Administrador.
     */
    private String pinAdmi;

    /**
     * Atributo resultado, en este atributo se guarda si el usuario logra ingresar exitosamente o no (true/false).
     */
    private boolean resultado;

    /**
     * contadorIntentos, en este atributo se guarda la cantidad de intentos que le quedan al usuario para ingresar.
     */
    private int contadorIntentos;

    /**
     * Este atributo guarda un objeto del tipo {@linkplain Administrador} para luego hacer uso de sus atributos.
     */
    private Administrador admi;

    private String rutaImagenes;

    private String rutaBaseDatos;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Constructor de la clase ControladorLoginAdmi.
     * @param nombreUsuario nombre de usuario ingresado por el mismo en
     *                      {@linkplain ve.edu.ucab.lab.Vista.VentanaAdmin}
     * @param pinAdmi pin del administrador ingresado por el mismo en
     *               {@linkplain ve.edu.ucab.lab.Vista.VentanaAdmin}
     * @param contadorIntentos contador de los intentos realizados por el administrador en
     *                        {@linkplain ve.edu.ucab.lab.Vista.VentanaAdmin}
     */
    public ControladorLoginAdmi(String nombreUsuario, String pinAdmi, int contadorIntentos) {
        this.nombreUsuario = nombreUsuario;
        this.pinAdmi = pinAdmi;
        administradores=controladorAdmis.getAdministradores();
        resultado=false;
        this.contadorIntentos=contadorIntentos;
    }

    public void archivito(String ruta,String rutaimg,String archivo, String png){
        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable(ruta,rutaimg,archivo,png);
        rutasDeEjecutable.rutas();
        rutaBaseDatos=rutasDeEjecutable.getRutaBaseDatos();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();
    }

    /**
     * Este es la clase que se encarga de verificar si el usuario ingresa los datos correctos de entrada. Tomando en
     * cuenta que solamente se trabaja con Administradores predeterminados se toma en cuenta que no hay nombres de
     * usuarios repetidos, por esta misma razón esa es la Key del HashMap para poder buscar en nuestra lista. Si el
     * nombre de usuario existe en la lista entonces se pasa a verificar si la cuenta no está bloqueada. En dado caso
     * de que la cuenta no esté bloqueada se verifica que el pin ingresado exista en nuestra lista. En dado caso de que
     * el dato ingresado sea erróneo entonces le damos dos intentos más al usuario, si se acaban las oportunidades
     * entonces se cambia el estado de "activo" a "bloqueado" y luego se reescribe toda la base de datos con esta
     * modifcación. Por último se vuelve a cargar en automático la BD. En dado caso de que la clave se ingrese
     * correctamente entonces se ingresa exitósamente.
     * @return resultado, valor que indicará la entrada exitosa del usuario.
     */
    public boolean loginAdmi(){
        admi=administradores.get(nombreUsuario);
        if(admi==null){

            archivito("","src/main/java/Imagenes/error-de-base-de-datos.png","",
                    "error-de-base-de-datos.png");

            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\">Tal parece que el nombre de usuario ingresado<br>" +
                            "<b>no se encuentra en la base de datos</b>,<br> por favor ingréselo nuevamente</p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE,icono);

        } else if (admi!=null){
            if (admi.getEstadoCuentaAdministrador()){
                if (!admi.getPin().equals(pinAdmi)){
                    if (contadorIntentos!=0){

                        archivito("","src/main/java/Imagenes/ClaveInvalida.png","",
                                "ClaveInvalida.png");

                        ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
                        Image img = icono.getImage();
                        Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                        icono = new ImageIcon(scaledImage);
                        JOptionPane.showMessageDialog(null,
                                "<html><p style=\"color:black;font-size:15px;\">Clave inválida verifíquela nuevamente<br><br>" +
                                        "<b>le quedan "+contadorIntentos+" intentos</b></p></html>",
                                "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE,icono);
                        pinAdmi="";
                    } else if (contadorIntentos==0){
                        admi.setCuentaAdministrador("bloqueado");
                        try {
                            archivito("src/main/java/ve/edu/ucab/lab/baseDeDatos/DatosAdmis",
                                    "src/main/java/Imagenes/bloquear-usuario.png","DatosAdmis",
                                    "bloquear-usuario.png");

                            FileWriter archivo = new FileWriter(rutaBaseDatos);
                            /**
                             * Se crea este StringBuilder como recipiente de toda los datos que se ingresarán en la BD.
                             */
                            StringBuilder linea = new StringBuilder();
                            String datos;
                            for (Administrador admi2: administradores.values()){
                                datos=admi2.getPin()+","+admi2.getNombreAdministrador()+","+admi2.getApellidoAdministrador()+
                                        ","+admi2.getCorreoAdministrador()+","+admi2.getUserAdministrador()+","
                                        +admi2.getCiAdministrador()+","+admi2.getCuentaAdministrador();
                                linea.append(datos+"\n");
                                /**
                                 * Convertimos el StringBuilder en una línea de texto aceptable para poder ser escrita
                                 * en la BD.
                                 */
                                archivo.write(linea.toString());
                                /**
                                 * Sirve para vaciar nuestro recipiente de datos (StringBuilder) y no concatenar
                                 * erróneamente cosas de más.
                                 */
                                linea.setLength(0);
                            }
                            archivo.close();
                            controladorAdmis.cargarAdministradores();
                            ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                            Image img = icono.getImage();
                            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                            icono = new ImageIcon(scaledImage);
                            JOptionPane.showMessageDialog(null,
                                    "<html><p style=\"color:black;font-size:15px;\"><b>¡La clave que ha ingresado es errónea</b><br> " +
                                            "por medidas de seguridad, <b>la cuenta ha sido bloqueada</b>, por favor contacte al gerente bancario" +
                                            "!</p></html>",
                                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
                            nombreUsuario="";
                            pinAdmi="";
                        } catch (IOException e) {

                            archivito("","src/main/java/Imagenes/ArchivoCorrupto.png","",
                                    "ArchivoCorrupto.png");

                            ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                            Image img = icono.getImage();
                            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                            icono = new ImageIcon(scaledImage);
                            JOptionPane.showMessageDialog(null,
                                    "<html><p style=\"color:black;font-size:15px;\">¡Archivo no encontrado o Corrupto!</p></html>",
                                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);

                        }
                    }

                } else if (admi.getPin().equals(pinAdmi)) {

                    archivito("","src/main/java/Imagenes/entradaExitosa.png","",
                            "entradaExitosa.png");

                    ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                    Image img = icono.getImage();
                    Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    icono = new ImageIcon(scaledImage);
                    JOptionPane.showMessageDialog(null,
                            "<html><p style=\"color:black;font-size:15px;\">¡<b>INGRESO EXITOSO</b>, BIENVENIDO: "+
                                    admi.getNombreAdministrador()+" "+admi.getApellidoAdministrador()+"</p></html>",
                            "BIENVENIDO A RAPIDOCASH", JOptionPane.INFORMATION_MESSAGE, icono);
                    resultado=true;
                    nombreUsuario="";
                    pinAdmi="";
                }

            }
            else if (!admi.getEstadoCuentaAdministrador()){

                archivito("","src/main/java/Imagenes/CuentaBloqueada.png","",
                        "CuentaBloqueada.png");

                ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                Image img = icono.getImage();
                Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icono = new ImageIcon(scaledImage);
                JOptionPane.showMessageDialog(null,
                        "<html><p style=\"color:black;font-size:15px;\">Estimado usuario, lamentamos informarle "+
                                "que actualmente su cuenta<br> se <b>encuentra bloqueada</b>. Por favor recupere su cuenta con su" +
                                "superior</p></html>",
                        "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
                nombreUsuario="";
                pinAdmi="";
            }
        }
        return resultado;
    }

    /**
     * Getter ResultadoAdministrador.
     * @return resultado, valor del atributo resultado de la clase Controladora del Login del Administrador.
     */
    public boolean resultadoAdministrador() {
        return resultado;
    }

    /**
     * Getter pinAdmi.
     * @return pinAdmi, valor del pin del Administrador.
     */
    public String getPinAdmi() {
        return pinAdmi;
    }

    /**
     * Getter nombreUsuario.
     * @return nombreUsuario, valor del nombre del usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Getter administrador.
     * @return admi, retornamos el objeto Administrador llenado por el proceso de Login para que se reutilice este
     * objeto durante el programa, asegurando que una sola persona esté manipulando el programa.
     */
    public Administrador getAdmi() {
        return admi;
    }
}


