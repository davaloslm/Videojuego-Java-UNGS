package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import entorno.Entorno;
import entorno.Herramientas;

public class Dinosaurio extends Rectangle{

	private String direccion;
	private double velocidad;
	private boolean cayendo;
	private Proyectil misil;
	private int tickDeDisparo; //Representa el momento en el que el dinosaurio dispara
	private Image dinoIzq;
	private Image dinoDer;
	private Color color;
	
	public Dinosaurio(double xCentro, double yCentro, double ancho, double alto, String direccion,double velocidad, Color color) {
		
		this.x = (int) (xCentro-(ancho/2));
		this.y = (int) (yCentro-(alto/2));
		this.width = (int) (ancho);
		this.height = (int) (alto);
		this.direccion = direccion;
		this.velocidad = velocidad;
		this.color = color;
		this.cayendo = false;
		this.dinoDer = Herramientas.cargarImagen("imagenes/dino-R.gif");
		this.dinoIzq = Herramientas.cargarImagen("imagenes/dino-L.gif");
	}
	
	public void dibujarse(Entorno e) {
		e.dibujarRectangulo(this.getCenterX(), this.getCenterY(), this.width, this.height, 0, this.color);
	}
	
	public void dibujarImgDino( Entorno e) {
		if (this.getDireccion().equalsIgnoreCase("derecha")) {
			e.dibujarImagen(this.dinoDer,  this.getCenterX(), this.getCenterY(), 0, 0.20);
		}else {
			e.dibujarImagen(this.dinoIzq,  this.getCenterX(), this.getCenterY(), 0, 0.20);

		}
	}

		
	
	public void moverse(Entorno e, String direccion) {
		if(this.getCenterX()+(this.width/2) <= e.ancho()&& this.direccion.equals("derecha")) {
		this.x +=this.velocidad;
		}
		if(this.getCenterX()-(this.width/2)  >= (e.ancho()-e.ancho()) && this.direccion.equals("izquierda")) {
			this.x -=this.velocidad;
		}
	}
	
	public void moverseAbajo() {
		this.setLocation(this.x, this.y+1);
	}
	
	public void disparoAutomatico() {
		this.misil = new Proyectil(this.getCenterX(), this.getCenterY(), 15,15 , this.color, this.direccion);
	}
	
	
	public void cambiarDireccion() {
		if(this.direccion.equals("derecha")){
			this.direccion = "izquierda"; 
		
		}else {
			this.direccion = "derecha";
		}
	}
	
	public void caer() {
		if(this.isCayendo()) {
			this.y += 5;
			
		}
	}
	
	public boolean detectarLadoSupBloque(Bloque b) {
		return this.intersectsLine(b.getLadoSuperior());
	}
	
	public boolean detectarLadoSupEnPiso(Piso p) {
		for (int i = 0; i < p.getBloques().length; i++) {
			if(p.getBloques()[i] != null && this.detectarLadoSupBloque(p.getBloques()[i])) {
				return true;
			}
		}
		return false;
	}
	
	public boolean detectarLadoSupEnArrayPisos(Piso[] pisos) {
		for (int i = 0; i < pisos.length; i++) {
			if (this.detectarLadoSupEnPiso(pisos[i])) {
				return true;
			}
		}
		return false;
	}
	
	//Getters & Setters 
	

	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
	public boolean isCayendo() {
		return cayendo;
	}
	public void setCayendo(boolean cayendo) {
		this.cayendo = cayendo;
	}

	public Proyectil getMisil() {
		return misil;
	}
	public void setMisil(Proyectil misil) {
		this.misil=misil;
	}
	public int getTickDeDisparo() {
		return tickDeDisparo;
	}
	public void setTickDeDisparo(int tickDeDisparo) {
		this.tickDeDisparo = tickDeDisparo;
	}


	
	

	
}
