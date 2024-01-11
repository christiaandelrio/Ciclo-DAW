/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog03.figuras;

/**
 *
 * @author yo
 */
public class Rectangulo {
    public float base;
    public float altura;
    
    public Rectangulo () {
        
    }
    
    public Rectangulo (float base, float altura) {
        this.base = base;
        this.altura = altura;
    }
    
    public void setBase(float base) {
        this.base = base;
    }
    public float getBase() {
        return base;
    }
     public void setAltura(float altura) {
        this.altura = altura;
    }
    public float getAltura() {
        return altura;
    }
    public float getArea(float base, float altura) {
       float area = base * altura;
       return area;
    }
    
    @Override
    public String toString() {
        float area = base * altura;
        String resultado = "El área del rectángulo es " +area +" cm." +"\nLa altura del rectángulo es " +altura +"cm.";
        return resultado;
    }
    public boolean isCuadrado() {
     boolean cuadrado = (base == altura);
     return cuadrado;
    }
}
    
