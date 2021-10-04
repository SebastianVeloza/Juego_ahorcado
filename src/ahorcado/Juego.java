/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahorcado;

import java.applet.AudioClip;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Rojeru San
 */
public class Juego {

    String can;
    int a = 0;
    AudioClip error, inicio, victoria, acierto, perdio, inicio1;
    JTextField jt;
    JLabel lb;
    JLabel lb2;
    boolean win = false;
    String pistaP = "";
    private boolean play = false;
    private String[] diccionario = {
        "MANZANA", "PLATANO", "NARANJA", "PERA", "SANDIA", "LIMA", "MANDARINA", "GUAYABA", "FRESA", "UVA", "DURAZNO",//11  Posicion 0-10 Frutas
        "BURRO", "CABALLO", "PERRO", "GATO", "CONEJO", "LEON", "TIGRE", "LEOPARDO", "PANTERA", "ELEFANTE", "GALLINA", "POLLO",
        "JIRAFA", "COCODRILO", "RANA", "SAPO",//16 Posicion 11-26 Animales
        "PADRASTROS", "HERMANOS", "PAPA", "MAMA", "TIOS", "ABUELA", "PRIMOS", "ABUELOS", "HERMANASTROS", "HIJOS", "BISABUELOS",
        "HIJASTROS",//12 Posicion 27-38 Personas cercanas
        "RIO", "MAR", "PLAYA", "LAGUNA", "ESTERO", "ARROYO", "PACIFICO", "ATLANTICO", "OCEANO", "PANTANO",//10 Posicion 39-48 Lugares con agua
        "MICHOACAN", "GUERRERO", "MEXICO", "COLIMA", "GUADALAJARA",//5 Posicion 49-53 Estados
        "INGLATERRA", "EUROPA", "ESPAÑA", "CANADA", "GUATEMALA", "CUBA", "CHILE", "FRANCIA", "RUSIA", "SUIZA",//10 Posicion 54-63 Paises
        "MESA", "LICUADORA", "REFRIGERADOR", "SILLA", "SILLON", "TELEVISION", "ROPERO", "VENTILADOR", "SARTEN",
        "CUBIERTOS",//10 Posicion 64-73 Cosas del hogar
        "PIZZA", "HAMBURGUESA", "ENCHILADAS", "TACOS", "QUESADILLAS", "PASTEL", "GUISADO", "MOLE", "SANDWICH", "TOCINO",
        "CHOCOLATE", "HELADO", "NIEVE", "DONA",//14 Posicion 74-87 Comidas o postres
        "ROJO", "VERDE", "AMARILLO", "AZUL", "GRIS", "CAFE", "ROSA", "MORADO", "ANARANJADO", "NEGRO",//10 Posicion 88-97 Colores
        "CERO", "UNO", "DOS", "TRES", "CUATRO", "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE",//10 Posicion 98-107 Numeros
        "AGUA", "LIMONADA", "LICUADO", "TE", "REFRESCO", "JUGO", "CERVEZA",//7 Posicion 108-114 Bebidas
        "CUADRADO", "RECTANGULO", "TRIANGULO", "ROMBO", "PENTAGONO", "HEXAGONO", "CUBO", "ROMBOIDE",//8 Posicion 115-122 Figuras
        "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO",//7 Posicion 123-129 Dias de la semana
        "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE",
        "DICIEMBRE"};//12 Posicion 130-141 Meses del año

    //PALABRAS 142
    public char[] palabra_secreta;
    private char[] palabra;

    int intentos = 0;
    boolean cambios = false;
    boolean activo = false;

    public Juego() {
    }

    public Juego(JTextField texto, JLabel loser, JLabel ahorcado) {
        inicio = java.applet.Applet.newAudioClip(getClass().getResource("/tonos/ahorcado.wav"));
        inicio.play();
        activo = true;
        this.palabra_secreta = Random().toCharArray();
        String var = "";
        //llena un string con "_" 
        for (int i = 0; i <= this.palabra_secreta.length - 1; i++) {
            var = var + "―";

        }

        //convierte este en un array
        this.palabra = var.toCharArray();
        this.jt = texto;
        this.lb = loser;
        this.lb2 = ahorcado;

        jt.setText(var);
        lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/vidas/v0.png")));
        lb2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imaAhorcado/0.png")));

        can = String.valueOf(this.palabra_secreta.length);
        JOptionPane.showMessageDialog(null, pistaP, "Ahorcado",
                0, new javax.swing.ImageIcon(getClass().getResource("/ahorcado/pista.png")));        
        inicio1 = java.applet.Applet.newAudioClip(getClass().getResource("/tonos/2.mp3"));
        inicio1.play();
        this.play = true;
    }

    //evalua el juego de acuerdo a los caracteres que se le pase
    public void evaluar(char word) {

        if (play) {
            String p = "";
            //controla que aun se pueda jugar
            //evalua caracter por caracter
            for (int j = 0; j <= this.palabra_secreta.length - 1; j++) {
                //si el caracter se encuentra en la palabra secreta            
                if (this.palabra_secreta[j] == word) {
                    acierto = java.applet.Applet.newAudioClip(getClass().getResource("/tonos/acierto.wav"));
                    acierto.play();
                    this.palabra[j] = word;//se asigna para que se pueda ver en pantalla
                    this.cambios = true;
                }
                p = p + this.palabra[j];
            }//fin for
            //si no se produjo ningun cambio, quiere decir que el jugador se equivoco
            if (this.cambios == false) {
                this.intentos += 1; //se incrementa            
                lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/vidas/v" + this.intentos + ".png")));
                lb2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imaAhorcado/" + this.intentos + ".png")));
                if (this.intentos < 8) {
                    error = java.applet.Applet.newAudioClip(getClass().getResource("/tonos/error.wav"));
                    error.play();
                    JOptionPane.showMessageDialog(null, "          ¡Incorrecto! \n Te quedan " + ((8) - this.intentos) + " intentos más.",
                            "Ahorcado", 0, new javax.swing.ImageIcon(getClass().getResource("/ahorcado/nosuper.png")));
                    inicio1 = java.applet.Applet.newAudioClip(getClass().getResource("/tonos/2.mp3"));
                    inicio1.play();
                } else {
                    lb2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imaAhorcado/8.png")));

                    String RESPT = "";
                    for (int i = 0; i <= this.palabra_secreta.length - 1; i++) {
                        RESPT = RESPT + this.palabra_secreta[i];
                    }
                    inicio.stop();
                    error = java.applet.Applet.newAudioClip(getClass().getResource("/tonos/error.wav"));
                    error.play();
                    perdio = java.applet.Applet.newAudioClip(getClass().getResource("/tonos/fallo.wav"));
                    perdio.play();
                    a = 1;
                    this.intentos = 8;
                    this.jt.setText(RESPT);
                    JOptionPane.showMessageDialog(null, "¡ Has Fallado !", "Ahorcado", 0,
                            new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imaAhorcado/trist.png")));
                    inicio1 = java.applet.Applet.newAudioClip(getClass().getResource("/tonos/2.mp3"));
                    inicio1.play();
                }
            } else {
                this.cambios = false;
                this.jt.setText(p);
                //comprobamos el estado del juego
                gano();

            }
        }
    }

    private void gano() {
        for (int i = 0; i <= this.palabra_secreta.length - 1; i++) {
            if (this.palabra[i] == this.palabra_secreta[i]) {
                win = true;
            } else {
                win = false;
                break;
            }
        }
        if (win) {
            inicio.stop();
            victoria = java.applet.Applet.newAudioClip(getClass().getResource("/tonos/victoria.wav"));
            victoria.play();
            JOptionPane.showMessageDialog(null, "                Errores (" + (this.intentos) + "/8)." + "\n" + "Lo Has Logrado !!   Felicidades !!",
                    "Ahorcado", 0, new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imaAhorcado/salvado.png")));
            inicio1 = java.applet.Applet.newAudioClip(getClass().getResource("/tonos/2.mp3"));
            inicio1.play();
            victoria.stop();
            a = 1;
        }
    }

    private String Random() {
        int num = (int) (Math.random() * (diccionario.length));
        if (num >= 0 && num <= 10) {
            pistaP = "Es una fruta";
        }
        if (num >= 11 && num <= 26) {
            pistaP = "Es un animal";
        }
        if (num >= 27 && num <= 38) {
            pistaP = "Es un familiar";
        }
        if (num >= 39 && num <= 48) {
            pistaP = "Es un lugar donde hay agua,\n"
                    + "ya sea  dulce o salada";
        }
        if (num >= 49 && num <= 53) {
            pistaP = "Es un estado del País de Mexíco";
        }
        if (num >= 54 && num <= 63) {
            pistaP = "Es un Pais";
        }
        if (num >= 64 && num <= 73) {
            pistaP = "Es algo del hogar";
        }
        if (num >= 74 && num <= 87) {
            pistaP = "Es una comida o un postre";
        }
        if (num >= 88 && num <= 97) {
            pistaP = "Es un color";
        }
        if (num >= 98 && num <= 107) {
            pistaP = "Es un número";
        }
        if (num >= 108 && num <= 114) {
            pistaP = "Es algo que se toma\n"
                    + "o bebe (Bebida).";
        }
        if (num >= 115 && num <= 122) {
            pistaP = "Es una figura geométrica";
        }
        if (num >= 123 && num <= 129) {
            pistaP = "Es un dia de la semana";
        }
        if (num >= 130 && num <= 141) {
            pistaP = "Es un mes del año";
        }
        return diccionario[num];
    }
}
