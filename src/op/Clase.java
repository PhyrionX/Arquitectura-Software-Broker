package op;

import java.util.ArrayList;

public class Clase {
	private String nombre;
	private ArrayList<String> metodos; //Lista de metodos
	
	public Clase(String nombre) {
		this.nombre = nombre;
		this.metodos = new ArrayList<String>();
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void addMetodo(String metodo) {
		this.metodos.add(metodo);
	}
	
	public boolean getMetodo(String metodo) {
		return metodos.contains(metodo);
	}

	public String getMetodos() {
		String cadena = "";
		for (String metodo : this.metodos) {
			cadena += metodo + " ";
		}
		// TODO Auto-generated method stub
		return cadena;
	}
	/**
	 * Recupera el nombre del servidor clase que utiliza ese método
	 * @param metodo
	 * @return
	 */
	public String getClase(String metodo) {
		for (String metod : this.metodos) {
			if (metod.equals(metod)) {
				return this.nombre;
			}
		}
		return null;
	}
}
