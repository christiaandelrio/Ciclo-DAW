/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.leerguardarobjetos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Sergio
 */
public class LeerGuardarObjetos {

    public static void main(String[] args) {

        Persona persona = new Persona("Sergio", "García López");

        try {
            //Guardamos la persona:
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("persona.dat"));
            objectOutputStream.writeObject(persona);
            objectOutputStream.close();

            //Recuperamos la persona: 
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("persona.dat"));
            Persona personaRecuperada = (Persona) objectInputStream.readObject();

            System.out.println("Datos de la persona recuperados de archivo: " + personaRecuperada.nombre + " " + personaRecuperada.apellidos + "\n");
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
        }
        
        
        ArrayList <Persona> personas = new ArrayList<>();
        
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("personas.dat"));
            
            Persona persona1 = new Persona("Sergio", "García López");
            Persona persona2 = new Persona("Rosa", "Otero Malvárez");
            Persona persona3 = new Persona("Panchito", "Alonso"
                    + " Rodríguez");
            
            personas.add(persona1);
            personas.add(persona2);
            personas.add(persona3);
            
            Collections.sort(personas);
            
            for (Persona person : personas) {
                objectOutputStream.writeObject(person);
            }
            
            objectOutputStream.close();
            
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("personas.dat"));

            System.out.println("Datos de personas recuperados de archivo ordenados alfabéticamente: ");
            while (true) {
                Persona personaRecuperada = (Persona) objectInputStream.readObject();
                System.out.println(" - " + personaRecuperada.apellidos + ", " + personaRecuperada.nombre);
            }
        }catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

