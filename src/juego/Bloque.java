package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import entorno.Entorno;
import entorno.Herramientas;

public class Bloque extends Rectangle{

	private boolean destruible;
	private Color color;
	private Image bloqueIndestructible;
	private Image bloqueRompible;
	
	public Bloque(double xCentro, double yCentro, double ancho, double alto, Color color) {
		
		this.x = (int)(xCentro-ancho/2);
		this.y = (int) (yCentro-alto/2);
		this.width = (int) ancho;
		this.height = (int) alto;
		this.color = color;
		this.destruible = Math.random()>0.2;
		this.bloqueIndestructible=Herramientas.cargarImagen("imagenes/bloque.gif");
		this.bloqueRompible= Herramientas.cargarImagen("imagenes/rompible.gif");

	}
	

	public void dibujarse(Entorno e) {
		e.dibujarRectangulo(this.getCenterX(), this.getCenterY(), this.width, this.height, 0, this.color);
	}
	
	public void dibujarImgBloque(Entorno e) {
		if (destruible) {
			e.dibujarImagen(bloqueIndestructible, this.getCenterX(), this.getCenterY(), 0, 0.163);			
		}else {
			e.dibujarImagen(bloqueRompible, this.getCenterX(), this.getCenterY(), 0, 0.164);			
		}
	}

	
	public void moverseAbajo() {
		this.setLocation(this.x, this.y+1);
	}

	
	//Getters & Setters

	
	public boolean isDestruible() {
		return destruible;
	}

	public Line2D getLadoSuperior() {
		return new Line2D.Double(this.x+5, this.y, (this.x+this.width)-5, this.y);
	}
		
	public Line2D getLadoInferior() {
		return new Line2D.Double(this.x+5, this.y+this.height, (this.x+this.width)-5, this.y+this.height);
	}
	
	
	public Line2D getLadoDerecho() {
		return new Line2D.Double((this.x+this.width), this.y+5, (this.x+this.width), (this.y+this.height)-5);
	}

	public Line2D getLadoIzquierdo() {
		return new Line2D.Double(this.x, this.y+5, this.x, (this.y+this.height)-5);
	}


	
}
