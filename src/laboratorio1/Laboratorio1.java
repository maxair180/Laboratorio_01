/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laboratorio1;

import java.util.Stack;             //para usar pilas
import javax.swing.JOptionPane;     //mostrar caja de texto

/**
 *
 * @author chris
 */

public class Laboratorio1{
    
    // metodo para verificar si un caracter es un operador = true
    public static boolean isOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    // metodo para invertir una cadena y recorrerla
    public static String reverseString(String str) {
        StringBuilder reversed = new StringBuilder();   //constructor
        
    // iterar cadena de derecha a izquierda    
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c == '(') {
                reversed.append(')');
            } else if (c == ')') {
                reversed.append('(');
            } else {
                reversed.append(c);
            }
        }
        return reversed.toString();     //retorna cadena invertida
    }

    // metodo para validar la jerarquia de los operadores
    public static int validateHierarchy(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    // metodo para convertir una expresión infija a postfija
    public static String convertToPostfix(String expresionInfija) {
        StringBuilder resultado = new StringBuilder();      //constructor
        Stack<Character> pila = new Stack<>();              //pila de operadores y parentesis

        for (int i = 0; i < expresionInfija.length(); i++) {
            char c = expresionInfija.charAt(i);

            // si el caracter es un operando, añadirlo al resultado
            if (Character.isLetterOrDigit(c)) {
                resultado.append(c);
            }
            // si el caracter es '(', apilar a la pila
            else if (c == '(') {
                pila.push(c);
            }
            // si el caracter es ')', sacar de la pila hasta encontrar '('
            else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    resultado.append(pila.pop());
                }
                if (!pila.isEmpty() && pila.peek() == '(') {
                    pila.pop();                 // sacar '(' de la pila
                }
            }
            // si el caracter es un operador
            else if (isOperador(c)) {
                while (!pila.isEmpty() && validateHierarchy(pila.peek()) >= validateHierarchy(c)) {
                    resultado.append(pila.pop());
                }
                pila.push(c);
            }
        }

        // sacar todos los operadores restantes de la pila
        while (!pila.isEmpty()) {
            resultado.append(pila.pop());
        }

        return resultado.toString();    //retorna expresion postfija
    }

    // metodo para convertir una expresion infija a prefija
    public static String convertToPrefix(String expresionInfija) {
        // invertir la expresión infija y cambiar '(' por ')' y viceversa
        String expresionInvertida = reverseString(expresionInfija);

        // convertir la expresion invertida a postfija
        String expresionPostfijaInvertida = convertToPostfix(expresionInvertida);

        // invertir la expresion postfija para obtener la prefija
        return reverseString(expresionPostfijaInvertida);
    }

             // metodo principal
    public static void main(String[] args) {
                
            // mostrar una caja de texto para que el usuario ingrese la expresion infija
        String expresionInfija = JOptionPane.showInputDialog("Ingrese la expresión infija:");

        if (expresionInfija != null && !expresionInfija.isEmpty()) {
           
            // convertir la expresion infija a postfija
            String expresionPostfija = convertToPostfix(expresionInfija);
            
            // convertir la expresion infija a prefija
            String expresionPrefija = convertToPrefix(expresionInfija);

            // construye mensaje con los resultados de la conversion
            String mensaje = "expresion infija: " + expresionInfija + "\n"
                           + "expresion postfija: " + expresionPostfija + "\n"
                           + "expresion prefija: " + expresionPrefija;

        // mostrar mensaje en caja de texto    
            JOptionPane.showMessageDialog(null, mensaje, "resultados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR", "no se ingreso ninguna expresion.", JOptionPane.ERROR_MESSAGE);
        }
    }
}