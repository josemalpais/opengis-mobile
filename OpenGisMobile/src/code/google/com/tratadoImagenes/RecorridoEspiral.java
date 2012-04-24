package code.google.com.tratadoImagenes;

import com.google.android.maps.Projection;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RecorridoEspiral {
	
	private Bitmap imagen;
	private boolean empezar = false;
	private int eskina = 0;
	private boolean recorridoTerminado = false;
	
	public RecorridoEspiral(Bitmap imagen){
		
		this.imagen = imagen;
		
	}

	
	public Bitmap pintarPerimetro(){
		
		Canvas canvas = new Canvas(this.imagen);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		
		 for(int i=0;i<imagen.getWidth();i++){
          	for(int j=0;j<imagen.getHeight();j++){
          		
          		if(imagen.getPixel(i,j) == Color.RED || imagen.getPixel(i,j) == Color.BLUE){
          			canvas.drawPoint(i,j,p);
          			
          		}
          		
          	}
          	
         }
		 
		
		return imagen;
		
	}
	
	
	public Bitmap recorridoEspiral(int posicionX,int posicionY){
		
		Bitmap imagenTratar = pintarPerimetro();
		
		
		Canvas canvas = new Canvas(imagenTratar);
		
		Paint p = new Paint();
		p.setColor(Color.RED);
		
		posicionY = posicionY - 50; // Le quitamos el alto del espacio de arriba
		
		System.out.println("Y: "+posicionY);
		
		System.out.println("X: "+posicionX);
		
		//canvas.drawCircle(496,290,4,p);
		
		//Bitmap imagenTratada = scanner(488,284,imagenTratar);
		
		Bitmap imagenTratada = scanner(posicionX,posicionY,imagenTratar);
		
		
		return imagenTratada;
		
	}
	
	public Bitmap getImagen(){
		
		return this.imagen;
	}
	
	
	public int media(int a1, int a2, int a3, int a4) {
		boolean lol = true;
		if (a1 >= 20 && lol == true) {
			lol = false;
			a1 = 10;
		}
		if (a2 >= 20 && lol == true) {
			lol = false;
			a2 = 10;
		}
		if (a3 >= 20 && lol == true) {
			lol = false;
			a3 = 10;
		}
		if (a4 >= 20 && lol == true) {
			lol = false;
			a4 = 10;
		}

		return (a1 + a2 + a3 + a4) / 4;

	}
	
	
	
	public Bitmap scanner (int x ,int y,Bitmap img){ //metodo que posiciona el inicio de trabajo al lugar mas cercano ademas gestiona todos los metodos que se usan para trazar la espiral

		Bitmap imagen2 = img;
		
		Canvas canvas = new Canvas(imagen2);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		
		boolean condicion = false;
		int derecha = 5;
		int izquierda = 5;
		int arriba = 5;
		int abajo = 5;

		if (!recorridoTerminado) {
			for (; derecha <= 100; derecha++) { // comprueba la distancia hasta
												// el azul

				 
				if (imagen2.getPixel(x + derecha, y) == Color.BLUE
						|| imagen2.getPixel(x + derecha, y) == Color.GREEN) {
					break;
				}
			}
			for (; izquierda <= 50; izquierda++) {// comprueba la distancia
													// hasta el azul

				
				if (imagen2.getPixel(x - izquierda, y) == Color.BLUE
						|| imagen2.getPixel(x - izquierda, y) == Color.GREEN) {
					break;
				}
			}
			for (; arriba <= 50; arriba++) {// comprueba la distancia hasta el
											// azul
				
				if (imagen2.getPixel(x, y - arriba) == Color.BLUE
						|| imagen2.getPixel(x, y - arriba) == Color.GREEN) {

					break;
				}
			}
			for (; abajo <= 50; abajo++) {// comprueba la distancia hasta el
											// azul
				
				if (imagen2.getPixel(x, y + abajo) == Color.BLUE
						|| imagen2.getPixel(x, y + abajo) == Color.GREEN) {
					break;
				}
			}

			int media = (derecha + arriba + abajo + izquierda) / 4; // esto ara
																	// que se
																	// pare
																	// recorrido

			System.out.println("media " + media + " derecha" + derecha
					+ " izquierda" + izquierda + " abajo" + abajo + " arriba"
					+ arriba); // imprime la distancia

			// media = media(izquierda,derecha,abajo,arriba);

			if (izquierda <= 10 && abajo <= 10 && arriba <= 10 && media <= 10
					|| izquierda <= 10 && derecha <= 10 && abajo <= 10
					&& media <= 10 || izquierda <= 10 && arriba <= 10
					&& derecha <= 10 && media <= 10 || derecha <= 10
					&& arriba <= 10 && abajo <= 10 && media <= 10) { 
				
				recorridoTerminado = true;
				condicion = true;

			} else {
				if (empezar == false) {
					if (derecha < izquierda && derecha < abajo
							&& derecha < arriba && condicion == false) { // comprueba
																			// x
																			// donde
																			// empieza
																			// segun
																			// cual
																			// esta
																			// mas
																			// cerca
						System.out.println("empieza derechaAbajo");
						condicion = true;

						derechaAbajo(x + derecha - 10, y,img);
						
					}
					if (izquierda < derecha && izquierda < abajo
							&& izquierda < arriba && condicion == false) {// comprueba
																			// x
																			// donde
																			// empieza
																			// segun
																			// cual
																			// esta
																			// mas
																			// cerca
						System.out.println("empieza izquierdaArriba");
						condicion = true;

						izquierdaArriba(x - izquierda + 10, y,img);
					}
					if (arriba < derecha && arriba < abajo
							&& arriba < izquierda && condicion == false) {// comprueba
																			// x
																			// donde
																			// empieza
																			// segun
																			// cual
																			// esta
																			// mas
																			// cerca
						System.out.println("empieza arribaDerecha");
						condicion = true;

						arribaDerecha(x, y - arriba + 10,img);
					}
					if (abajo < arriba && abajo < derecha && abajo < izquierda
							&& condicion == false) {// comprueba x donde empieza
													// segun cual esta mas cerca
						System.out.println("empieza abajoIzquierda");
						condicion = true;
						abajoIzquierda(x, y + abajo - 10,img);
					}
				} else {
					for (int control = -2; control <= 2; control++) {
						if (arriba == derecha + control && condicion == false) { // gestiona
																					// los
																					// rincones
							condicion = true;
							if (arriba < 25) {
								System.out.println("pinta derechaAbajo");
								derechaAbajo(x, y,img);
								// arribaDerecha(x,y);

							}
						}
					}
					for (int control = -2; control <= 2; control++) {
						if (abajo == derecha + control && condicion == false) {
							condicion = true;
							if (abajo < 25) {
								System.out.println("pinta abajoIzquierda");// gestiona
																			// los
																			// rincones
								abajoIzquierda(x, y,img);

							}
						}
					}
					for (int control = -2; control <= 2; control++) {
						if (abajo == izquierda + control && condicion == false) {
							condicion = true;
							if (abajo < 25) {
								System.out.println("pinta izquierdaArriba");// gestiona
																			// los
																			// rincones
								izquierdaArriba(x, y,img);

							}
						}
					}
					for (int control = -2; control <= 2; control++) {
						if (izquierda == arriba + control && condicion == false) {
							condicion = true;
							if (izquierda < 25)
								System.out.println("pinta arribaDerecha");// gestiona
																			// los
																			// rincones
							arribaDerecha(x, y,img);

						}
					}

					if (condicion == false) { // otra condicion
						condicion = true;
						System.out.println("otra condicion en scanner"
								+ "derexa " + derecha + " izquierda:"
								+ izquierda + " abajo:" + abajo + " arriba:"
								+ arriba); // otra condicion como una eskina que
											// aun no e desarollado
					}
				}
			}
		}
		
		return img;
	}

	public void derechaAbajo(int x, int y,Bitmap img) {
		eskina++;
		
		Bitmap imagen2 = img;
		
		Canvas canvas = new Canvas(imagen2);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);

		empezar = true;
		int x2 = x;
		int y2 = y;
		int linea = 0;
		boolean romper = false;

		while (romper == false) {
			linea++;
			for (int derecha = 1; derecha <= 20; derecha++) {
				
				if (derecha > 15) {
					hacerEsquinas(x, y + linea, 1,img);
					romper = true;
					break;
				}
				if (imagen2.getPixel(x + derecha, y + linea) == Color.BLUE
						|| imagen2.getPixel(x + derecha, y + linea) == Color.GREEN) { // SI HEMOS
																	// ENCONTRADO
																	// EL
																	// PERÍMETRO...
					int resultado = 10 - derecha;
					x = x - resultado;
					canvas.drawPoint(x, y + linea,p);
					if (eskina == 5) {

						for (int to = 1; to <= 10; to++) {
							for (int larg = 1; larg <= 10; larg++) {
								
								//aux = this.getPixel(x-to , y +linea+ larg);
							//aux.setColor(Color.red);
								if (imagen2.getPixel(x-to , y +linea+ larg) == Color.BLUE) {
									for (int incli1 = 0; incli1 <= 8; incli1++) {
									
										canvas.drawPoint(x-incli1 , y +linea+(incli1*2),p);
										eskina = 6;
										to = 11;
										x2 = x - incli1;
									y2 = y +linea+incli1;
									}

									x = x2;
									y = y2;
									eskina++;
									break;
								}
							}
						}

					}
					 
					// aux.setColor(Color.red);
					if (imagen2.getPixel(x+resultado, y + linea + 10) ==Color.BLUE
							|| imagen2.getPixel(x+resultado, y + linea + 10) == Color.GREEN) {
						romper = true;
					}
					break;
				}
			}
		}

		scanner(x, y + linea,img);
	}

	public void abajoIzquierda(int x, int y,Bitmap img) {
		eskina++;
		
		Bitmap imagen2 = img;
		
		Canvas canvas = new Canvas(imagen2);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		
		System.out.println(eskina + "abajo izquierda");
		int x2 = x;
		int y2 = y;
		empezar = true;
		int linea = 1;
		boolean romper = false;

		while (romper == false) {
			for (int abajo = 1; abajo <= 20; abajo++) {
				
				if (abajo > 15) {
					hacerEsquinas(x - linea, y+ abajo, 2,img);
					romper = true;
					break;
				}
				if (imagen2.getPixel(x - linea, y + abajo) == Color.BLUE
						|| imagen2.getPixel(x - linea, y + abajo) == Color.GREEN) {
					int resultado = 10 - abajo;
					y = y - resultado;
					

					if (imagen2.getPixel(x - linea - 10, y) == Color.BLUE) {
						romper = true;
					}

					canvas.drawPoint(x - linea, y,p);

					if (eskina == 5) {

						for (int to = 1; to <= 10; to++) {
							for (int larg = 1; larg <= 10; larg++) {
								
								if (imagen2.getPixel(x - linea - larg, y - to) == Color.BLUE){
									for (int incli1 = 0; incli1 <= 8; incli1++) {

										canvas.drawPoint(x - linea - incli1, y
												- (incli1 * 2),p);
										eskina = 6;
										x2 = x - incli1 - 1;
										y2 = y - (incli1 * 2);
									}

									x = x2;
									y = y2;
									eskina++;
									break;
								}
							}
						}

					}
					

					if (imagen2.getPixel(x-linea-10 , y  + resultado) == Color.BLUE
							|| imagen2.getPixel(x-linea-10 , y  + resultado) == Color.GREEN) {
						romper = true;
					}
					break;
				}

			}

			linea++;
		}

		scanner(x - linea, y,img);
	}

	public void izquierdaArriba(int x, int y,Bitmap img) {
		eskina++;
System.out.println(eskina + "izquierda Arriba");
		empezar = true;
		
		Bitmap imagen2 = img;
		
		Canvas canvas = new Canvas(imagen2);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		
		
		int linea = 1;
		boolean romper = false;

		int x2 = x;
		int y2 = y;
		while (romper == false) {
			linea++;
			for (int izquierda = 1; izquierda <= 25; izquierda++) {
				if (izquierda > 15) {
					System.out.println("izquierda :" + izquierda);
					hacerEsquinas(x, y - linea, 2,img);
					romper = true;
					break;
				}
				
				if (imagen2.getPixel(x - izquierda, y - linea) == Color.BLUE
						|| imagen2.getPixel(x - izquierda, y - linea) == Color.GREEN) {
					int resultado = 10 - izquierda;
					x = x + resultado;
					canvas.drawPoint(x, y - linea,p);
					if (eskina == 5) {

						for (int alto = 3; alto <= 10; alto++) {
							for (int larg = 3; larg <= 10; larg++) {

								
								if (imagen2.getPixel(x , y -linea- larg) == Color.BLUE) {
									for (int incli1 = 0; incli1 <= 8; incli1++) {

										canvas.drawPoint(x + (incli1 * 2), y - (incli1)
												- linea,p);
										eskina = 6;

										y2 = y - incli1;
										x2 = x + (incli1 * 2);
									}

									x = x2;
									y = y2;
									break;
								}
							}
						}

					}
					

					if (imagen2.getPixel(x + resultado, y - linea - 10) == Color.BLUE
							||imagen2.getPixel(x + resultado, y - linea - 10) == Color.GREEN) {
						romper = true;
					}
					break;
				}
			}
		}
		
		canvas.drawPoint(x, y - linea,p);
	
	}

	public void arribaDerecha(int x, int y,Bitmap img) {
		eskina++;
		
		Bitmap imagen2 = img;
		
		Canvas canvas = new Canvas(imagen2);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		
		
		empezar = true;
		int x2 = x;
		int y2 = y;
		
		int linea = 0;
		boolean romper = false;

		while (romper == false) {
			
			linea++; // añado 1 a la coordenada X del Pixel a buscar
			for (int arriba = 1; arriba <= 30; arriba++) {// busca hacia arriba
															// del actual pixel,
															// buscando el
															// perímetro
				

				if (imagen2.getPixel(x + linea, y - arriba) == Color.BLUE
						|| imagen2.getPixel(x + linea, y - arriba) == Color.GREEN) {
					int resultado = 10 - arriba;
					y = y + resultado;
					canvas.drawPoint(x + linea, y,p);
					if (eskina == 5) {

						for (int to = 1; to <= 10; to++) {
							for (int larg = 1; larg <= 10; larg++) {
								
								if (imagen2.getPixel(x + linea + larg, y + to) == Color.BLUE) {

									for (int incli1 = 0; incli1 <= 8; incli1++) {

										canvas.drawPoint(x + linea + incli1, y
												+ (incli1 * 2),p);
										eskina = 6;

										x2 = x + incli1;
										y2 = y + (incli1 * 2);
									}
									// aux.setColor(Color.red);
									x = x2;
									y = y2;
									eskina++;
									break;
								}
							}
						}

					}
					
					if (imagen2.getPixel(x + linea + 10, y) == Color.BLUE
							|| imagen2.getPixel(x + linea + 10, y) == Color.GREEN) {
						romper = true;
					}
					break;
				}
			}
		}

		scanner(x + linea, y,img);

	}



	public void hacerEsquinas(int x, int y, int key,Bitmap img) {
		int o = 0;
		
		Bitmap imagen2 = img;
		
		Canvas canvas = new Canvas(imagen2);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		
		
		switch (key) {
		case 1:
			for (; o <= 10; o++) {
				canvas.drawPoint(x + o, o + y,p);

			}
			arribaDerecha(x + o, y + o,img);// derechaAbajo
			break;
		case 2:
			for (; o <= 10; o++) {
				canvas.drawPoint(x - o, y - o,p);

			}
			abajoIzquierda(x - o - 3, y - o - 2,img); // abajoIzquierda
			break;
		case 3:
			for (; o <= 10; o++) {
				canvas.drawPoint(x + o, o - +y,p);

			}
			abajoIzquierda(x + o, y + o,img); // izquierdaArriba
			break;
		case 4:
			for (; o <= 10; o++) {
				canvas.drawPoint(x + o, o + y,p);

			}
			izquierdaArriba(x + o, y + o,img);// arribaDerecha
			break;

		}
	}
}