package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Administrador;
import ve.edu.ucab.lab.modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Este controlador se enfoca en efectuar el cambio correcto del pin del usuario, confirmando el uso del Tokken enviado
 * y las contraseñas a ingresar.
 *
 * @version 2.30.6
 * @author Manuel Antias.
 */

public class ControladorPinAdmiCambiado {
    /**
     * Este atributo guarda el tokken enviado al correo del administrador.
     */
    private String tokken;
    /**
     * Este atributo guarda un objeto del tipo {@linkplain Administrador} para hacer uso de sus atributos.
     */
    private Administrador administrador;
    /**
     * Este atributo guarda otro objeto del tipo {@linkplain Administrador} para con él cambiar el atributo pin del
     * administrador.
     */
    private Administrador administradorCambio;
    /**
     * Este atributo guarda un objeto del tipo {@linkplain ControladorBaseDatosAdministrador} para poder extraer de la
     * base de datos. Así mismo este objeto es una instancia de dicha clase.
     */
    private ControladorBaseDatosAdministrador controladorAdmis=ControladorBaseDatosAdministrador.getInstanciaAdministrador();
    /**
     * Esta lista del tipo Map garda un compilado de varios objetos del tipo {@linkplain Administrador}.
     */
    private Map<String, Administrador> administradores = new LinkedHashMap<>();
    /**
     * Este atributo guarda el nuevo pin ingresado por el Administrador.
     */
    private String nuevoPin;
    /**
     * Este atributo guarda el pin de confirmación ingresado por el usuario (que debe ser igual al pin nuevo).
     */
    private String pinConfirmado;
    /**
     * Este atributo guarda un objeto del tipo {@linkplain ControladorEnviarCorreo}
     */
    private ControladorEnviarCorreo correoEnviado=new ControladorEnviarCorreo();
    /**
     * Este atributo guarda si la operación se llevó a cabo de manera fructífera.
     */
    private boolean correcto;

    private String rutaImagenes;

    private String rutaBaseDatos;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Constructor de la clase.
     *
     * @param tokken es el tokken ingresado por el adminsitrador.
     * @param administrador es el objeto Administrador.
     * @param nuevoPin es el pin que ingresado por el administrador.
     * @param correoEnviado es el correo ingresado por el administrador.
     * @param pinConfirmado es el pin de confirmación ingresado por el administrador.
     */
    public ControladorPinAdmiCambiado(String tokken, Administrador administrador,String nuevoPin,
                                      ControladorEnviarCorreo correoEnviado,String pinConfirmado) {
        this.nuevoPin=nuevoPin;
        this.pinConfirmado=pinConfirmado;
        this.tokken = tokken;
        this.administrador = administrador;
        this.correoEnviado=correoEnviado;
        administradores=controladorAdmis.getAdministradores();
        correcto=false;
    }

    public void archivito(String ruta,String rutaimg,String archivo, String png){
        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable(ruta,rutaimg,archivo,png);
        rutasDeEjecutable.rutas();
        rutaBaseDatos=rutasDeEjecutable.getRutaBaseDatos();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();
    }

    /**
     * Este método se encarga de primero validar que el tokken ingresado sea igual al enviado por al correo electrónico
     * del Administrador, para eso usamos la variable que guarda el dato ingresado por el Administrador y el controlador
     * de enviar correo, del cual obtenemos el tokken con su respectivo get. Si el tokken es correcto, entonces la
     * persona debe proceder a ingresar su nuevo pin, el cual debe ser distinto al pin anteriormente registrador. Luego
     * se verifica que el administrador coloque un pin de confirmación, dicho pin debe ser igual al nuevo pin. Si todas
     * estas condiciones se cumplen se cambiará directamente el pin al atributo del administrador y luego de esto
     * se cargará inmediatamente a la base de datos de administradores.
     *
     * @return correcto, retornamos el valor que nos indicará que este proceso se llevó a cabo con éxito.
     */
    public boolean pinCambiado(){
        administradorCambio=administradores.get(administrador.getUserAdministrador());
        if(administradorCambio!=null){
            if(tokken.equals(correoEnviado.getTokken())){
                if (!nuevoPin.equals(administradorCambio.getPin())){
                    if (nuevoPin.equals(pinConfirmado)) {
                        administradorCambio.setPin(nuevoPin);
                        try {

                            archivito("src/main/java/ve/edu/ucab/lab/baseDeDatos/DatosAdmis",
                                    "src/main/java/Imagenes/cambiar-la-contrasena.png","DatosAdmis",
                                    "cambiar-la-contrasena.png");

                            FileWriter archivo = new FileWriter(rutaBaseDatos);
                            /**
                             * Se crea este StringBuilder como recipiente de toda los datos que se ingresarán en la BD.
                             */
                            StringBuilder linea = new StringBuilder();
                            String datos;
                            for (Administrador administradorCambio : administradores.values()) {
                                datos = administradorCambio.getPin()+ "," + administradorCambio.getNombreAdministrador() + "," +
                                        administradorCambio.getApellidoAdministrador() + "," +
                                        administradorCambio.getCorreoAdministrador()+","+administradorCambio.getUserAdministrador()+
                                        ","+administradorCambio.getCiAdministrador()+","+administradorCambio.getCuentaAdministrador();
                                linea.append(datos + "\n");
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
                            nuevoPin="";
                            pinConfirmado="";
                            tokken="";
                            correcto=true;
                            ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                            Image img = icono.getImage();
                            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                            icono = new ImageIcon(scaledImage);
                            JOptionPane.showMessageDialog(null,
                                    "<html><p style=\"color:black;font-size:15px;\">¡Su pin se ha cambiado con éxito!" +
                                            "<br><br>ya puede acceder al sistema RapidoCash</p></html>",
                                    "PIN CAMBIADO", JOptionPane.INFORMATION_MESSAGE, icono);
                        }catch (IOException e) {

                            archivito("", "src/main/java/Imagenes/ArchivoCorrupto.png","",
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
                    else {

                        archivito("", "src/main/java/Imagenes/ArchivoCorrupto.png","",
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
                else {

                    archivito("", "src/main/java/Imagenes/Contrasena_erronea.png","",
                            "Contrasena_erronea.png");

                    ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                    Image img = icono.getImage();
                    Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    icono = new ImageIcon(scaledImage);
                    JOptionPane.showMessageDialog(null,
                            "<html><p style=\"color:black;font-size:15px;\">¡Ups parece que cometió un error!" +
                                    "<br><br>el nuevo pin no puede ser igual al pin anterior registrado.<br>" +
                                    "Por favor ingrese un nuevo pin.</p></html>",
                            "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
                    pinConfirmado="";
                    nuevoPin="";
                }

            }else {

                archivito("", "src/main/java/Imagenes/error-de-contrasena.png","",
                        "error-de-contrasena.png");

                ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                Image img = icono.getImage();
                Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icono = new ImageIcon(scaledImage);
                JOptionPane.showMessageDialog(null,
                        "<html><p style=\"color:black;font-size:15px;\">¡Ups parece que cometió un error!" +
                                "<br><br>el tokken de verificación de identidad no coincide con el de su bandeja de entrada<br>" +
                                "por favor colocarlo nuevamente.</p></html>",
                        "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
                tokken="";
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"Hubo un error");
        }
        return correcto;
    }

    /**
     * Getter correcto.
     * @return correcto, retornamos el valor que nos indica que el proceso se llevó a cabo de manera satisfactoria.
     */
    public boolean esCorrecto() {
        return correcto;
    }

    /**
     * Getter tokken.
     * @return retornamos el tokken en vacío por su hay que borrar algún cambio.
     */
    public String getTokken() {
        return tokken;
    }

    /**
     * Getter pinConfirmado.
     * @return pinConfirmado, retornamos el pin de confirmación en vacío por si debemos borrar algún campo.
     */
    public String getPinConfirmado() {
        return pinConfirmado;
    }

    /**
     * Getter nuevoPin.
     * @return retornamos el nuevo pin ingresado en vacío por si deseamos borrar algún campo.
     */
    public String getNuevoPin() {
        return nuevoPin;
    }
}
