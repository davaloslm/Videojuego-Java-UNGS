package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil extends Rectangle {
	

	private Color color;
	private String direccion;
	private Image bolaDeFuegoIzq;
	private Image bolaDeFuegoDer;
	private Image misilIzq;
	private Image misilDer;
	private Image bolaDeFuegoJefe;

	
	public Proyectil(double xCentro, double yCentro, double ancho, double alto, Color color, String direccion) {
		
		this.x = (int) (xCentro-ancho/2);
		this.y = (int) (yCentro-alto/2);
		this.width = (int) ancho;
		this.height = (int) ancho;
		this.direccion = direccion;
		this.color = color;
		this.bolaDeFuegoDer = Herramientas.cargarImagen("imagenes/bolaDeFuego-R.gif");
		this.bolaDeFuegoIzq = Herramientas.cargarImagen("imagenes/bolaDeFuego-L.gif");
		this.misilDer = Herramientas.cargarImagen("imagenes/misil-R.gif");
		this.misilIzq = Herramientas.cargarImagen("imagenes/misil-L.gif");
		this.bolaDeFuegoJefe = Herramientas.cargarImagen("imagenes/bolaDeFuegoJefe.gif");

				
	}
	
	public void dibujarse(Entorno e) {
		e.dibujarRectangulo(this.getCenterX(), this.getCenterY(), this.width, this.height, 0, this.color);
	}
	
	public void dibujarProyectilPrincesa(Princesa p, Entorno e) {
		if (p.getDireccion().equalsIgnoreCase("derecha")) {
			e.dibujarImagen(bolaDeFuegoDer, p.getDisparo().getCenterX(), p.getDisparo().getCenterY(), 0, 0.2);
		}else {
			e.dibujarImagen(bolaDeFuegoIzq, p.getDisparo().getCenterX(), p.getDisparo().getCenterY(), 0, 0.2);
		}
	}
	
	public void dibujarMisilDino(Dinosaurio d, Entorno e) {
		if (d.getDireccion().equalsIgnoreCase("derecha")) {
			e.dibujarImagen(misilDer, d.getMisil().getCenterX(), d.getMisil().getCenterY(), 0, 0.2);
		}else {
			e.dibujarImagen(misilIzq, d.getMisil().getCenterX(), d.getMisil().getCenterY(), 0, 0.35);
		}
	}
	
	public void dibujarBolaDeFuegoJefe(Jefe j, Entorno e) {
		e.dibujarImagen(bolaDeFuegoJefe, j.getDisparo().getCenterX(), j.getDisparo().getCenterY(), 0, 0.1);
	}
	
	public void moverse(String direccion) {
		

		if (direccion.equalsIgnoreCase("derecha")) {
			this.x +=8;

		}
		if (direccion.equalsIgnoreCase("izquierda")) {
			this.x -=8;
		}

	}
	
	
	public void moverseAbajo() {
		this.setLocation(this.x, this.y+1);
	}

	//Getters & Setters


	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	
}