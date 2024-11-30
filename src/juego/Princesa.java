package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Princesa extends Rectangle{

	private double fuerzaSalto;
	private boolean saltando;
	private boolean cayendo;
	private String direccion;
	private Proyectil disparo; 
	private Color color;
	private Image princesaIzq;
	private Image princesaDer;
	private Clip sonidoColisionBloque;
	private Clip sonidoBolaDeFugo;

	
	public Princesa(double xCentro, double yCentro, double ancho, double alto, Color color) {

		this.x = (int) (xCentro-(ancho/2));
		this.y = (int) (yCentro-(alto/2));
		this.width = (int) (ancho);
		this.height = (int) (alto);
		this.direccion = "derecha";
		this.fuerzaSalto = 150;
		this.color = color;
		this.saltando = false;
		this.cayendo = false;
		this.disparo = null;
		this.princesaDer= Herramientas.cargarImagen("imagenes/princesa-R.gif");
		this.princesaIzq= Herramientas.cargarImagen("imagenes/princesa-L.gif");
		this.sonidoColisionBloque =Herramientas.cargarSonido("sonidos/colisionBloque.wav");
		this.sonidoBolaDeFugo = Herramientas.cargarSonido("sonidos/bolaDeFuego.wav");
	}
	


	public void dibujarse(Entorno e) {
		
		e.dibujarRectangulo(this.getCenterX(), this.getCenterY(), this.width, this.height, 0, this.color);
	}
	
//	public void dibujarPricesaDerecha(Princesa p,Entorno e) {
//		if(p.getDireccion().equalsIgnoreCase("derecha")) {
//			e.dibujarImagen(princesaDer, p.getX()+5, p.getY()+10, 0, 0.15);
//		}			
//		
//	}
	public void dibujarImgPricesa(Entorno e) {
		if(this.getDireccion().equalsIgnoreCase("izquierda")) {
			e.dibujarImagen(princesaIzq, this.getCenterX(), this.getCenterY()-7, 0, 0.15);
		}else {
			e.dibujarImagen(princesaDer, this.getCenterX(), this.getCenterY()-7, 0, 0.15);
		}
		
	}
	
	public void moverseDerecha(Entorno e) {

		if (this.getCenterX()+(this.width/2) < e.ancho() ) {
			this.x +=4;
			this.direccion = "derecha";
		}
	}

	public void moverseIzquierda(Entorno e) {
		if (this.getCenterX()-(this.width/2)  > (e.ancho()-e.ancho()) ) {
			this.x -= 4;
			this.direccion = "izquierda";

		}
		
	}
	

	public void saltar() {
		if (fuerzaSalto !=0) {
			this.setSaltando(true);
			this.y -= 6;
			this.fuerzaSalto -= 6;
		}
		
	}
	
	public void caer() {
		if(this.isCayendo()) {
			this.fuerzaSalto = 0;
			this.y += 5;
			
		}
	}
	
	
	public void reestablecerSalto() {	
		this.fuerzaSalto = 150;
	}
	
	public void disparar() {
		this.disparo = new Proyectil(this.getCenterX()+this.width, this.getCenterY()-(this.getHeight()/4), 10, 5, this.color, this.direccion);
		this.sonidoBolaDeFugo.setFramePosition(0);
		this.getSonidoBolaDeFugo().start();

	}
	
	//Deteccion de lados de bloque
	//Lado Sup
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
	
	//Lado Inferior
	public boolean detectarLadoInfBloque(Bloque b) {
		return this.intersectsLine(b.getLadoInferior());
	}
	
	public boolean detectarLadoInfEnPiso(Piso p) {
		for (int i = 0; i < p.getBloques().length; i++) {
			if(p.getBloques()[i] != null && this.detectarLadoInfBloque(p.getBloques()[i])) {
				if (p.getBloques()[i].isDestruible()) {
					p.getBloques()[i] = null;

					sonidoColisionBloque.setFramePosition(0);
					sonidoColisionBloque.start();
				}
				return true;
				
			}
		}
		return false;
	}
	
	public boolean detectarLadoInfEnArrayPisos(Piso[] pisos) {
		for (int i = 0; i < pisos.length; i++) {
			if (this.detectarLadoInfEnPiso(pisos[i])) {
				return true;
			}
		}
		return false;
	}
	
	//Lado Izquierdo
	public boolean detectarLadoIzqBloque(Bloque b) {
		return this.intersectsLine(b.getLadoIzquierdo());
	}
	
	public boolean detectarLadoIzqEnPiso(Piso p) {
		for (int i = 0; i < p.getBloques().length; i++) {
			if(p.getBloques()[i] != null && this.detectarLadoIzqBloque(p.getBloques()[i])) {
				return true;
				
			}
		}
		return false;
	}
	
	public boolean detectarLadoIzqEnArrayPisos(Piso[] pisos) {
		for (int i = 0; i < pisos.length; i++) {
			if (this.detectarLadoIzqEnPiso(pisos[i])) {
				return true;
			}
		}
		return false;
	}
	
	//Lado Derecho
	public boolean detectarLadoDerBloque(Bloque b) {
		return this.intersectsLine(b.getLadoDerecho());
	}
	
	public boolean detectarLadoDerEnPiso(Piso p) {
		for (int i = 0; i < p.getBloques().length; i++) {
			if(p.getBloques()[i] != null && this.detectarLadoDerBloque(p.getBloques()[i])) {
				return true;
				
			}
		}
		return false;
	}
	
	public boolean detectarLadoDerEnArrayPisos(Piso[] pisos) {
		for (int i = 0; i < pisos.length; i++) {
			if (this.detectarLadoDerEnPiso(pisos[i])) {
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




	public Proyectil getDisparo() {
		return disparo;
	}



	public void setDisparo(Proyectil disparo) {
		this.disparo = disparo;
	}



	public double getFuerzaSalto() {
		return fuerzaSalto;
	}

	public void setFuerzaSalto(double fuerzaSalto) {
		this.fuerzaSalto = fuerzaSalto;
	}

	public boolean isSaltando() {
		return saltando;
	}

	public void setSaltando(boolean saltando) {
		this.saltando = saltando;
	}


	public boolean isCayendo() {
		return cayendo;
	}

	public void setCayendo(boolean cayendo) {
		this.cayendo = cayendo;
	}



	public Proyectil getFlecha() {
		return disparo;
	}

	public void setFlecha(Proyectil flecha) {
		this.disparo = flecha;
	}

	public Clip getSonidoBolaDeFugo() {
		return sonidoBolaDeFugo;
	}

	public void setSonidoBolaDeFugo(Clip sonidoBolaDeFugo) {
		this.sonidoBolaDeFugo = sonidoBolaDeFugo;
	}

	


//	public int getContadorEliminados() {
//		return contadorEliminados;
//	}
//
//
//
//	public void setContadorEliminados(int contadorEliminados) {
//		this.contadorEliminados = contadorEliminados;
//	}
//
//
//
//	public int getPuntaje() {
//		return puntaje;
//	}
//
//
//
//	public void setPuntaje(int puntaje) {
//		this.puntaje = puntaje;
//	}
	
//	public double getY() {
//		return (yCentro-(alto/2));
//}


	
	

	
	
	
	
}
