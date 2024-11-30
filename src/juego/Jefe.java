package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import entorno.Entorno;
import entorno.Herramientas;

public class Jefe extends Rectangle {

	private Rectangle puntoDebil;
	private Image jefeImg;
	private Color color;
	private Proyectil disparo;
	private int puntosDeVida;
	
	
	public Jefe(double xCentro, double yCentro, double ancho, double alto, Color color) {
		
		this.x = (int) (xCentro-(ancho/2));
		this.y = (int) (yCentro-(alto/2));
		this.width = (int) (ancho);
		this.height = (int) (alto);
		this.color = color;
		this.jefeImg = Herramientas.cargarImagen("imagenes/grimlock.gif");
		this.puntosDeVida = 5;
	}
	
	public void dibujarse(Entorno e) {
		e.dibujarRectangulo(this.getCenterX(), this.getCenterY(), this.width, this.height, 0, this.color);
	}
	
	public void dibujarImagen(Entorno e) {
		e.dibujarImagen(jefeImg, this.getCenterX(), this.getCenterY(), 0, 1.4);
	}
	
	
	public void dibujarPuntoDebil(Entorno e) {
		e.dibujarRectangulo(this.getPuntoDebil().getCenterX(), this.getPuntoDebil().getCenterY(), this.getPuntoDebil().width, this.getPuntoDebil().height, 0, Color.green);
	}
	
	public void disparar() {
		this.disparo = new Proyectil(this.getCenterX()+(this.getPuntoDebil().width*2.8), this.getCenterY(), 30, 30 , this.color, null);
	}

	///Getters & Setters
	//Su cabeza, el punto debil, es proporcional a su "cuerpo"
	public Rectangle getPuntoDebil() {
		return new Rectangle((int)this.getMaxX()-(int)this.getWidth()/7, (int)this.getMinY()+(int)this.getHeight()/6, (int)this.getWidth()/4, (int)this.getHeight()/2);
	}

	public Proyectil getDisparo() {
		return disparo;
	}

	public void setDisparo(Proyectil disparo) {
		this.disparo = disparo;
	}

	public int getPuntosDeVida() {
		return puntosDeVida;
	}

	public void setPuntosDeVida(int puntosDeVida) {
		this.puntosDeVida = puntosDeVida;
	}
	
	
	

	
	

	
	
	
	
}
