package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Cuenta;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorDatosPalabrasCorrectos {
    /**
     * Atributo estructuraRegular, en este se guarda el patrón de estructura regular utilizada para una
     * correcta entrada.
     */
    private String estructuraRegular;
    /**
     * Atributo entradaPalabra, en este se guarda la entrada recibida en la ventana.
     */
    private String entradaPalabra;
    /**
     * Atributo datoCorrecto, en este se guarda un true o false si el dato cumple con la estructura o no.
     */
    private boolean datoCorrecto;
    /**
     * Atributo mensaje, en este se guarda el campo especializado en el que puede ocurrir el ingreso erróneo de datos.
     */
    private String mensaje;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Constructor de la clase DatosPalabrasCorrectas.
     * @param entradaPalabra Cadena ingresada para validar.
     * @param mensaje campo en dónde se ingresando mal el dato.
     */
    public ControladorDatosPalabrasCorrectos(String entradaPalabra,String mensaje) {
        this.entradaPalabra = entradaPalabra;
        this.mensaje = mensaje;
        /**
         *Esta estructura regular narra: Que no se acepten espacios vacíos en la entrada ni datos mayores a 13 caracteres.
         */
        estructuraRegular="^[^\\s]{1,13}$";
    }

    /**
     * En este método se crea el código que se encargará de verificar que la entrada se ingrese correctamente.
     * Se creó un objeto Pattern para convertir la expresión regular en un patrón. Luego se utiliza un objeto
     * Matcher para poder comparar la entrada con el patrón creado. Por último, si la cadena ingresada es errónea
     * entonces se enviará un mensaje de error y se devolverá un true o false correspondiente.
     * @return datoCorrecto, valor que contiene si hay un error o no en la entrada.
     */
    public boolean confirmarPalabrasCorrectas() {

        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("",
                "src/main/java/Imagenes/Error.png","","Error.png");
        rutasDeEjecutable.rutas();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();

        Pattern patron = Pattern.compile(estructuraRegular);
        Matcher coincidencia = patron.matcher(entradaPalabra);
        datoCorrecto = coincidencia.matches();
        if (datoCorrecto==false) {
            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\">¡Uups parece que cometió un error " +
                            "en el campo "+mensaje+"!<br><b>(recuerde solo usar 13 carácteres y sin separaciones)</b></p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
        }
        return datoCorrecto;
    }

    /**
     * Getter Error.
     * @return datoCorrecto, valor del atributo datoCorrecto de la clase Controladora de entradas ingresadas.
     */
    public boolean errorCadena() {
        return datoCorrecto;
    }
}
