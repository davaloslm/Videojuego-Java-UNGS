package juego;


import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.management.loading.PrivateClassLoader;
import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	private Princesa elizabeth;
	private Piso pisos[];
	private Dinosaurio dinos[];
	private Rectangle magma;
	private boolean erupcion; //Controla la subida del magma
	private boolean ascenso;  //Controla la subida de la pantalla
	private boolean menu = true;
	private boolean indicadorNivel; 
	private boolean jefeDesbloqueado;
	private int contadorTicks;
	private int tickInterrupcionDeJuego; //Contiene el tick en el cual se interrumpe el juego para mostrar la pantalla indicadora de nivel
	private int cantVidas = 3;
	private int puntaje;
	private int nivel = 1;
	private int cantEliminados;
	private Rectangle puerta;
	private Jefe jefe;
	
	////multimedia///

	private Image fondoMenu;
	private Image fondo;
	private Image gameOver;
	private Image ganaste;
	private Image presionaControl;
	private Image presionaEnter;
	private Image presionaEspacio;
	private Image titulo;
	private Image magmaImg;
	private Clip musicaFondo;


	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Super Elizabeth Sis. - Grupo 6 Dávalos Valdiviezo", 800, 600);
		
		//La inicializacion de todos los objetos esta en el metodo inicializar

		resetear();
		
		


		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		

		//se decide qué pantalla se debe mostrar
		if(cantVidas == 0) {
			mostrarGameOver();
		}else {
			
			if (menu) {
				mostrarMenu();
			}
			
			if(indicadorNivel) {
				mostrarPantallaIndicadoraNivel();
			}
			
			if (nivel == 1 && !indicadorNivel && !menu) {
				menu = false;
				juegoPrincipal();
			}
			
			if (nivel == 2 & !indicadorNivel) {
				menu = false;
				juegoPrincipal();
			}
			
			if (nivel == 3 && !indicadorNivel) {
				menu = false;
				juegoPrincipal();
			}
			if (nivel > 3) {
				menu = false;
				mostrarFinDeJuego();
			}
			
		}
		
		
	}
	
	//pantalla menu
	public void mostrarMenu() {

		
		entorno.dibujarImagen(fondoMenu, entorno.ancho()/2, entorno.alto()/2, 0, 0.9);
		entorno.dibujarImagen(titulo, entorno.ancho()/2-20, entorno.alto()-500, 0, 0.5);
		entorno.dibujarImagen(presionaEnter, entorno.ancho()/2, entorno.alto()-100, 0, 0.2);
		entorno.dibujarImagen(presionaControl, entorno.ancho()/2, entorno.alto()-50, 0, 0.2);

		
		
		if(entorno.sePresiono(entorno.TECLA_ENTER)) {
			menu = false;
			return;
		}
		
		if(entorno.sePresiono(entorno.TECLA_CTRL)) {
			System.exit(0);
		}
	}
	
	//pantalla gameOver
	public void mostrarGameOver() {

		
		entorno.dibujarImagen(gameOver, entorno.ancho()/2-20, entorno.alto()-500, 0, 0.5);
		
		entorno.dibujarImagen(presionaEspacio, entorno.ancho()/2, entorno.alto()-50, 0, 0.2);
		

		
		if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			menu = true;
			indicadorNivel = false;
			jefeDesbloqueado = false;
			cantVidas = 3;
			puntaje = 0;
			nivel = 1;
			cantEliminados = 0;
			resetear();
			return;
		}
	}
	
	//mostrar pantalla de victoria
	public void mostrarFinDeJuego() {
		entorno.dibujarImagen(ganaste, entorno.ancho()/2-20, entorno.alto()-500, 0, 0.5);
		entorno.dibujarImagen(presionaEspacio, entorno.ancho()/2, entorno.alto()-50, 0, 0.2);

		
		if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			menu = true;
			indicadorNivel = false;
			cantVidas = 3;
			puntaje = 0;
			nivel = 1;
			cantEliminados = 0;
			resetear();
			return;
		}
	}
	
	
	
	
	public void juegoPrincipal() {
		// Procesamiento de un instante de tiempo
		// ...
	
		
		//Musica de fondo del juego
		if(musicaFondo==null) {
			musicaFondo = Herramientas.cargarSonido("sonidos/musicaFondo.wav");
			musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
//			musicaFondo.start();
		}
		contadorTicks++;
		
		
		//Imagen Fondo
		entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0, 1.3);
		//falta magmaImg


		
		//Dibujar Bloques
		for (Piso piso : pisos) {
			for (Bloque bloque : piso.getBloques()) {
				if (bloque != null) {
					bloque.dibujarse(entorno);
					bloque.dibujarImgBloque(entorno);
					
					//Si el disparo de la princesa choca un bloque, desaparece
					if (elizabeth.getFlecha() != null && elizabeth.getFlecha().intersects(bloque)) {
						elizabeth.setFlecha(null);
					}
				}
			}
		}
		
		//Puerta
		//Puede ser null si hay jefe
		if (puerta != null) {
			entorno.dibujarRectangulo(puerta.getX(), puerta.getY(), puerta.getWidth(), puerta.getHeight(), 0, Color.darkGray);
		}
		
		////////////////////////JEFE////////////////////////////////
		if (jefeDesbloqueado) {
			jefe.dibujarse(entorno);
			jefe.dibujarImagen(entorno);
			
			if (jefe.getDisparo() != null) {
				jefe.getDisparo().dibujarBolaDeFuegoJefe(jefe, entorno);
				jefe.getDisparo().moverse("derecha");
			}
			
			//Si el jefe recibe disparos en la cabeza
			if (elizabeth.getDisparo() != null && elizabeth.getDisparo().intersects(jefe.getPuntoDebil())) {
				jefe.setPuntosDeVida(jefe.getPuntosDeVida()-1);
				elizabeth.setFlecha(null);
				if(jefe.getPuntosDeVida() == 0) {
					jefeDesbloqueado = false;
					nivel++;
					menu = true;
					musicaFondo.stop();
					musicaFondo = null;
					resetear();
					return;
					
				}
			}
			
			if (jefe.getDisparo() != null && (jefe.getDisparo().getCenterX() > entorno.ancho() || jefe.getDisparo().getCenterX() < 0)) {
				jefe.setDisparo(null);
			}
			
			if (jefe.getDisparo() == null) {
				jefe.disparar();
			}
			
			//Si elizabeth colisiona con el cuerpo o la cabeza del jefe
			if (jefe.getDisparo() != null && (jefe.intersects(elizabeth) || jefe.getDisparo().intersects(elizabeth))) {
				cantVidas--;
				resetear();
				indicadorNivel=true;
				tickInterrupcionDeJuego = contadorTicks;
				return;
			}
			
			//Si el disparo del jefe colisiona con la princesa
			if (jefe.getDisparo() != null && jefe.getDisparo().intersects(elizabeth)) {
				indicadorNivel=true;
				cantVidas--;
				tickInterrupcionDeJuego = contadorTicks;
				resetear();
				return;
			}
		}
		
		
		/////////////////DINOS////////////////////////
		//El array puede ser null si hay jefe
		if (dinos != null) {
			
			for(int n=0; n <dinos.length;n++) {
				
				if (dinos[n] != null) {
					
					dinos[n].dibujarse(entorno);
					dinos[n].dibujarImgDino(entorno);
					
					if(!dinos[n].isCayendo()) {
						dinos[n].moverse(entorno, null);
					}
					
					
					//si choca con alguno de los bordes de la pantalla , cambia de direccion
					if( (dinos[n].getCenterX()+(dinos[n].getWidth()/2) >= entorno.ancho()) || (dinos[n].getCenterX()-(dinos[n].getWidth()/2) <= 0) ) {
						dinos[n].cambiarDireccion();
						
					}
					// disparo Dino
					if (dinos[n].getMisil() != null) {
						dinos[n].getMisil().dibujarse(entorno);
						dinos[n].getMisil().dibujarMisilDino(dinos[n], entorno);
						dinos[n].getMisil().moverse(dinos[n].getMisil().getDireccion());
					}
					
					//Si se va de la pantalla
					if (dinos[n].getMisil() != null && (dinos[n].getMisil().getCenterX() > entorno.ancho() || dinos[n].getMisil().getCenterX() < 0)) {
						dinos[n].setMisil(null);
					}
					
					//Activa el disparo cada 50 ticks             
					if ( dinos[n].getMisil() == null && contadorTicks-dinos[n].getTickDeDisparo() >50) {
						dinos[n].disparoAutomatico();
						dinos[n].setTickDeDisparo(contadorTicks);
					}
					
					
					//Deteccion del suelo para que el dino camine
					if(dinos[n].detectarLadoSupEnArrayPisos(pisos)) {
						dinos[n].setCayendo(false);
						
						
					}else {
						dinos[n].setCayendo(true);
						dinos[n].caer();
					}
					
					//Si el dinosaurio y la princesa chocan
					if (dinos[n].intersects(elizabeth)) {
						cantVidas--;
						resetear();
						indicadorNivel=true;
						tickInterrupcionDeJuego = contadorTicks;
						return;
					}
					
					//Si el Misil golpea a la princesa
					if (dinos[n].getMisil() != null &&  dinos[n].getMisil().intersects(elizabeth)) {
						dinos[n].setMisil(null);
						indicadorNivel=true;
						cantVidas--;
						tickInterrupcionDeJuego = contadorTicks;
						resetear();
						return;
					}
					
					//Si el disparo de la princesa choca con la Misil del dinosaurio
					if (elizabeth.getFlecha() != null && dinos[n].getMisil() != null && elizabeth.getFlecha().intersects(dinos[n].getMisil())) {
						dinos[n].setMisil(null);
					}
					
					////Si la princesa le dispara al dinosaurio
					if (elizabeth.getFlecha() != null && elizabeth.getFlecha().intersects(dinos[n])) {
						dinos[n]= null;
						elizabeth.setFlecha(null);
						puntaje +=50 ;
						cantEliminados += 1;
						
					}					
				}
			}
		}
				
				

		//Dibujar magma
		entorno.dibujarRectangulo(magma.x+(magma.width/2), magma.y+(magma.height/2), magma.width, magma.height, 0, Color.orange);

		////////////////////////Elizabeth///////////////////
		
		elizabeth.dibujarse(entorno);
		elizabeth.dibujarImgPricesa(entorno);
		
		
		//Disparo de la princesa
		if (elizabeth.getFlecha() != null) {
			elizabeth.getFlecha().dibujarse(entorno);
			elizabeth.getFlecha().dibujarProyectilPrincesa(elizabeth, entorno);
			elizabeth.getFlecha().moverse(elizabeth.getDisparo().getDireccion());
		}
		
		if (elizabeth.getFlecha() != null && (elizabeth.getFlecha().getCenterX() > entorno.ancho() || elizabeth.getFlecha().getCenterX() < 0)) {
			elizabeth.setFlecha(null);
		}

		//Deteccion de bloques para caminar sobre ellos
			
		if(elizabeth.detectarLadoSupEnArrayPisos(pisos)) {
			elizabeth.setCayendo(false);
			elizabeth.reestablecerSalto();
			
		}else if(!elizabeth.isSaltando()){
			elizabeth.setCayendo(true);
			elizabeth.caer();
		}
		
		//Deteccion de bloques para romperlos al saltar
		//Si elizabeth choca con el lado inferior de un bloque siempre deja de subir sin importar si lo rompe o no
		if(elizabeth.detectarLadoInfEnArrayPisos(pisos) ) {
			elizabeth.setFuerzaSalto(0);
			elizabeth.setSaltando(false);
			elizabeth.setCayendo(true);
			elizabeth.caer();
		}

		
		//La segunda condición es para que no atraviese los bloques cuando elizabeth está en el aire		
		if (entorno.estaPresionada(entorno.TECLA_DERECHA) && !elizabeth.detectarLadoIzqEnArrayPisos(pisos)) {
			elizabeth.moverseDerecha(entorno);
		}

		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !elizabeth.detectarLadoDerEnArrayPisos(pisos)) {

			elizabeth.moverseIzquierda(entorno);
		}

		
		if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && elizabeth.getFuerzaSalto() !=0 && elizabeth.getCenterY() < 600) {
			elizabeth.setSaltando(true);
			elizabeth.saltar();		
		}else {
			elizabeth.setSaltando(false);
		}
		
		
		if (entorno.sePresiono(entorno.TECLA_ENTER) && elizabeth.getFlecha() == null ) {
			elizabeth.disparar();

		}

		if (elizabeth.getCenterY() < 100) {
			ascenso = true;
		}
		
		if (puerta != null && elizabeth.intersects(puerta)) {
			if (nivel == 2) {
				jefeDesbloqueado = true;
			}
			nivel++;
			resetear();
			indicadorNivel=true;

			return;
		}
		
		if (elizabeth.intersects(magma)) {
			cantVidas--;
			resetear();
			indicadorNivel=true;
			tickInterrupcionDeJuego = contadorTicks;
			return;
		}
		
		//Texto
		entorno.cambiarFont("Arial", 15, Color.white);
		entorno.escribirTexto("Vidas: "+ String.valueOf(cantVidas), 10, 20);
		entorno.escribirTexto("Eliminados: "+ String.valueOf(cantEliminados), 10, 40);
		entorno.escribirTexto("Puntaje: "+ String.valueOf(puntaje), 10, 60);

		//Subida de magma y de pantalla
		//Cuando hay jefe, la pantalla y el magma no suben, 
		if (nivel != 3) {
			
			subirPantalla(elizabeth, pisos, dinos, contadorTicks);
			
			
			if (elizabeth.getCenterY()<200 && magma.getX() < 10) {
				erupcion = true;
			}
			subirMagma(contadorTicks);
		}
		
		
			
	}
	
	public void resetear() {
		this.elizabeth = null;
		this.dinos = null;
		this.pisos = null;
		this.puerta = null;
		this.magma = null;
		this.ascenso = false;
		this.erupcion = false;
		if (nivel ==1) {
			inicializar(7, 2, jefeDesbloqueado);
		}
		if (nivel == 2) {
			inicializar(5, 4, jefeDesbloqueado);
		}
		if (nivel == 3) {
			jefeDesbloqueado = true;
			inicializar(1, 0, jefeDesbloqueado);	
			
		}


	}
	
	public void mostrarPantallaIndicadoraNivel() {
		
		contadorTicks++;

		entorno.cambiarFont("Arial", 20, Color.white);
		entorno.escribirTexto("NIVEL " + String.valueOf(nivel), entorno.ancho()/2-40, entorno.alto()/2);
		entorno.escribirTexto("VIDAS " + String.valueOf(cantVidas), entorno.ancho()/2-40, entorno.alto()/2+20);
		
		
		if(contadorTicks - tickInterrupcionDeJuego > 80) {
			indicadorNivel = false;
			tickInterrupcionDeJuego = 0;
			return;
		}
	}
	
	
	
	public void inicializar(int cantPisos, double velocidadDinosaurios, boolean jefe) {
		// Inicializar lo que haga falta para el juego
		// ...
		
		//Sonido
//		musicaFondo = Herramientas.cargarSonido("sonidos/musicaFondo.wav");
		
		//Imagenes
		this.fondo = Herramientas.cargarImagen("imagenes/fondo.gif");
		this.fondoMenu = Herramientas.cargarImagen("imagenes/fondoMenu.jpg");
		this.gameOver = Herramientas.cargarImagen("imagenes/gameover.png");
		this.ganaste = Herramientas.cargarImagen("imagenes/ganaste.png");
		this.presionaControl = Herramientas.cargarImagen("imagenes/presionaControl.png");
		this.presionaEnter = Herramientas.cargarImagen("imagenes/presionaEnter.png");
		this.presionaEspacio = Herramientas.cargarImagen("imagenes/presionaEspacio.png");
		this.titulo = Herramientas.cargarImagen("imagenes/titulo.png");

		
		
		this.elizabeth = new Princesa(380, 550, 20, 40, new Color(0,0,0,0));
		
		
		//Magma
		this.magma = new Rectangle(0, 590, entorno.ancho(), entorno.alto());
		this.magmaImg= Herramientas.cargarImagen("imagenes/magma.gif");
		
		//Inicializacion de array de pisos
		double distanciaEntrePisos = 0;
		this.pisos = new Piso[cantPisos];
		for (int i = 0; i < pisos.length; i++) {
			pisos[i] = new Piso(20, 600 - distanciaEntrePisos, 40, 40, 20);
			distanciaEntrePisos += 120;
		}
		
		//Si hay jefe, no hay puerta de salida ni otros dinosaurios
		if (jefeDesbloqueado) {
			
			this.jefe = new Jefe((entorno.ancho()-entorno.ancho())+150, (int)this.pisos[this.pisos.length-1].getY()-95, 300, 150, new Color(0,0,0,0));
			
		}else {
			
			//Puerta de salida de nivel
			//Aparece siempre por encima del ultimo piso, sin importar cuantos pisos haya
			//Aparece en un x aleatorio que no toque los bordes de la pantalla
			this.puerta = new Rectangle(XRandomEntreBordes(30, this.entorno.ancho()-30), //x
					(int)this.pisos[this.pisos.length-1].getY()-(int)this.pisos[this.pisos.length-1].getAltoBloque(), //y
					30, 40);
			
			
			
			//Inicializacion de array de dinosaurios
			//Aparecen en un x random a cierta distancia en y
			//Aparecen 2 dinos por cada piso sin contar el primero
			this.dinos= new Dinosaurio[(cantPisos*2)-2];
			double distanciadinosy = 0;
			for(int j =0; j< dinos.length;j++) {
				boolean f = true;
				double random1 = XRandomEntreBordes(30, this.entorno.ancho()-30);
				double random2 = XRandomEntreBordes(30, this.entorno.ancho()-30);
				while(f) {
					
					double dif= random1 -random2;
					if(dif*1 >=100) {
						f=false;
					}else {
						random1 = XRandomEntreBordes(30, this.entorno.ancho()-30);
						random2 = XRandomEntreBordes(30, this.entorno.ancho()-30);
					}
				}			
				
				dinos[j]= new Dinosaurio( random1,400-distanciadinosy ,20, 40, "derecha",velocidadDinosaurios,new Color(0,0,0,0));
				j++;
				
				dinos[j]= new Dinosaurio( random2,400-distanciadinosy ,20, 40, "derecha",velocidadDinosaurios,new Color(0,0,0,0));
				distanciadinosy+=120;
				
				
				
			}
		}
		
		
		////////////Fin inicializacion/////////////////////
}
	
	public void subirPantalla(Princesa princesa, Piso[] pisos, Dinosaurio[] dinos, int ticks) {
		
		//la pantalla sube cada dos ticks
		if (ticks%2 == 0 && ascenso) {

			
			princesa.setLocation((int)princesa.getX(), (int)princesa.getY()+1);
			
			puerta.setLocation((int)puerta.getX(), (int)puerta.getY()+1);
			
			if (princesa.getDisparo() != null) {
				princesa.getDisparo().setLocation((int)princesa.getDisparo().getX(), (int)princesa.getDisparo().getY()+1);
			}
			
			for (int i = 0; i < pisos.length; i++) {
				for (int j = 0; j < pisos[i].getBloques().length; j++) {
					
					if(pisos[i].getBloques()[j] != null) {
						
						pisos[i].getBloques()[j].moverseAbajo();;
					}
				}
			}
			
			for (int i = 0; i < dinos.length; i++) {
				if(dinos[i] != null) {
					dinos[i].moverseAbajo();
					
					if (dinos[i].getMisil()!=null) {
						dinos[i].getMisil().moverseAbajo();
					}
				}	
			}
		}
	}
	
	//magma sube cada dos ticks
	public void subirMagma(int ticks) {
		if (ticks%4 == 0 && erupcion) {
			magma.setLocation(magma.x, magma.y-1);
		}
	}
	
	public int XRandomEntreBordes(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
