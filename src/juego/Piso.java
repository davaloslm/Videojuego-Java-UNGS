package juego;

import java.awt.Color;

public class Piso {

	private double xInicial;
	private double y;
	private double desplazamiento;
	private double anchoBloque;
	private double altoBloque;
	private Bloque[] bloques;
	private int cantBloques;
	
	
	public Piso(double xInicial, double y, double anchoBloque, double altoBloque, int cantBloques) {
		this.xInicial = xInicial;
		this.y = y;
		this.desplazamiento = 0;
		this.anchoBloque = anchoBloque;
		this.altoBloque = altoBloque;
		this.cantBloques = cantBloques;
		this.bloques = new Bloque[cantBloques];
		for (int i = 0; i < this.cantBloques; i++) {
			bloques[i] = new Bloque(this.xInicial+this.desplazamiento, this.y, this.anchoBloque, this.altoBloque, new Color(0,0,0,0));
			this.desplazamiento += this.anchoBloque;
		}
	}

//
	public double getxInicial() {
		return xInicial;
	}
//
//
//	public void setxInicial(double xInicial) {
//		this.xInicial = xInicial;
//	}
//
//
	public double getY() {
		return y;
	}
//
//
//	public void setyCentro(double yCentro) {
//		this.yCentro = yCentro;
//	}
//
//
//	public double getDesplazamiento() {
//		return desplazamiento;
//	}
//
//
//	public void setDesplazamiento(double desplazamiento) {
//		this.desplazamiento = desplazamiento;
//	}
//
//
//	public double getAnchoBloque() {
//		return anchoBloque;
//	}
//
//
//	public void setAnchoBloque(double anchoBloque) {
//		this.anchoBloque = anchoBloque;
//	}
//
//
	public double getAltoBloque() {
		return altoBloque;
	}
//
//
//	public void setAltoBloque(double altoBloque) {
//		this.altoBloque = altoBloque;
//	}


	public Bloque[] getBloques() {
		return bloques;
	}


	public void setBloques(Bloque[] bloques) {
		this.bloques = bloques;
	}


//	public int getCantBloques() {
//		return cantBloques;
//	}
//
//
//	public void setCantBloques(int cantBloques) {
//		this.cantBloques = cantBloques;
//	}
//	
	
	
}
