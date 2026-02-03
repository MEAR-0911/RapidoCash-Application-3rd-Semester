package ve.edu.ucab.lab.Controladores;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * En esta clase controladora de datos numéricos correctos el código se enfoca en usar las expresiones regulares
 * para poder confirmar que los datos numéricos ingresados como el teléfono, el pin y/o la cédula de identidad
 * cumplan con una estructura necesaria para el correcto funcionamiento de la aplicación, así nos evitamos el ingreso
 * incorrecto de datos como entradas vacías, carácteres incorrectos o demás de los requeridos. A su vez se adaptó
 * este código de manera general para el correcto uso de la reutilización, pudiendo ser usada en cualquier campo
 * de entrada numérica.
 *
 * @author Antias Manuel
 * @version 2.11.1
 */

public class ControladorDatosNumericosCorrectos {
    /**
     * Atributo estructuraRegular, en este se guarda el patrón de estructura regular utilizada para una
     * correcta entrada.
     */
    protected String estructuraRegular;
    /**
     * Atributo entradaNumerica, en este se guarda la entrada numérica recibida en la ventana.
     */
    protected String entradaNumerica;
    /**
     * Atributo cantChar, en este se guarda la cantidad de dígitos que debería tener la entrada, especificada en
     * el código de las ventanas (más específicamente como parámetro en el constructor).
     */
    protected String cantChar;
    /**
     * Atributo datoCorrecto, en este se guardará un booleano que nos confirmará si la cadena recibida cumple con
     * el patrón designado en la variable estructuraRegular.
     */
    protected boolean datoCorrecto;
    /**
     * Atributo mensaje, en este se guarda el campo especializado en el que puede ocurrir el ingreso erróneo de datos.
     */
    private String mensaje;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;


    /**
     * Constructor de la clase ControladorDatosNumericosCorrectos.
     * @param entradaNumerica cadena a comparar con la estructura regular.
     * @param cantChar cantidad de digitos que debería tener la entrada.
     * @param mensaje campo de entrada en el que se está trabajando.
     */
    public ControladorDatosNumericosCorrectos(String entradaNumerica, String cantChar, String mensaje) {
        this.entradaNumerica = entradaNumerica;
        this.cantChar = cantChar;
        this.mensaje=mensaje;
        /**
         * La estructura regular nos narra lo siguiente: la cadena solo puede contener dígitos del (0-9); La cadena
         * solamente puede tener "CantChar" dígitos; Además no se permiten espacios en blanco u otros caracteres.
         */
        estructuraRegular="^\\d{"+cantChar+"}$";
    }

    /**
     * En este método se crea el código que se encargará de verificar que la entrada se ingrese correctamente.
     * Se creó un objeto Pattern para convertir la expresión regular en un patrón. Luego se utiliza un objeto
     * Matcher para poder comparar la entrada con el patrón creado. Por último, si la cadena ingresada es errónea
     * entonces se enviará un mensaje de error.
     *
     * @return datoCorrecto, valor de si lo ingresado por el usuario está correcto.
     */
    public boolean confirmarDatos() {

        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("",
                "src/main/java/Imagenes/Error.png","","Error.png");
        rutasDeEjecutable.rutas();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();

        Pattern patron = Pattern.compile(estructuraRegular);
        Matcher coincidencia = patron.matcher(entradaNumerica);
        datoCorrecto = coincidencia.matches();
        if (datoCorrecto==false) {
            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\">¡Uups parece que cometió un error " +
                            "en el campo "+mensaje+"!<br> <b>(recuerde solo usar "+cantChar+" dígitos)</b></p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
        }
        return datoCorrecto;
    }

    /**
     * Getter Error.
     * @return datoCorrecto, valor del atributo datoCorrecto de la clase Controladora de números ingresados.
     */
    public boolean errorNum() {
        return datoCorrecto;
    }
}
