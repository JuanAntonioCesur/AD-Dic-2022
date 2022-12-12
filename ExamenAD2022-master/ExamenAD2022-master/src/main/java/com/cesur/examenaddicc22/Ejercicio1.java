package com.cesur.examenaddicc22;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Ejercicio1 {

    /**
     * Enunciado:
     * 
     * Completar el método estadísticasDeArchivo de manera que lea el archivo
     * de texto que se le pasa como parámetro, lo analice y muestre por consola 
     * el número de caracteres, palabras y líneas total.
     * 
     * Modificar solo el código del método.
     * 
     */
    
    static void solucion() {

        estadísticasDeArchivo("pom.xml");
    }

    private static void estadísticasDeArchivo(String archivo) {
        
        /* añadir código */
        try (BufferedReader br = new BufferedReader(new FileReader("pom.xml"))){
            
            //Si no hay retornode carro almenos hay 1 linea, si hay 1 retorno tenemos 2 lineas
            int nLineas=1;
            //Existe 1 espacio si hay dos palabras, no hay espacio si solo hay 1 palabra
            int nPalabras=1;
            int nCaracteres=0;

                int ascci;
                while((ascci=br.read())!=-1){
                    //Si hay un espacio, hay una palabra
                    if(ascci==' '){
                    nPalabras++;
                    }
                    else if(ascci=='\n'){
                    //lineas con el caracter de retorno de newline
                    nLineas++;
                    }
                    //Si no es un espacio o un retorno de carro es un caracter
                    else{
                        nCaracteres++;
                    }
                }
            
            
            
            System.out.println("Estadísticas de pom.xml:");
            System.out.print(nLineas);
            System.out.println(" líneas");
            System.out.print(nCaracteres);
            System.out.println(" caracteres");
            System.out.print(nPalabras);
            System.out.println(" Palabras");
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        System.out.println("--Ejercicio resuelto--");
    }
    
}
