package op;

import java.util.ArrayList;

public class Servidores {
	private String host; // direccion del host
	private ArrayList<Clase> clases; // Servidores que contiene
	
	public Servidores(String host, String clase) {
		this.host = host;
		this.clases =  new ArrayList<Clase>();
		this.clases.add(new Clase(clase));
	}
	
	public String getHost() {
		return this.host;
	}
	
	public void addClase(String clase) {
		this.clases.add(new Clase(clase));
	}
	
	public Clase getClase(String clase) {
		for (Clase cl : this.clases) {
			if (cl.getNombre().equals(clase)) {
				return cl;
			}
		}
		return null;
	}
	
	public boolean delClase(String clase) {
		int index = 0;
		for (Clase cl : this.clases) {
			if (cl.getNombre().equals(clase)) {
				this.clases.remove(index);
				return true;
			}
			index++;
		}
		return false;
	}
	
	public String getMetodos() {
		String cadena = "";
		for(Clase cl : this.clases) {
			cadena += cl.getMetodos() + " ";
		}
		return cadena;
	}
	public String toString() {
		String cadena = "";
		for (Clase cl : this.clases) {
			cadena += cl.getNombre() + "\n" + cl.getMetodos();
		}
		return this.host + "\n" + cadena;
	}
	
	/**
	 * Es la función que recupera el nombre del servidor que 
	 * tiene el método a usar
	 * @param nombre
	 * @return
	 */
	public String getMetodo(String nombre) {
		for (Clase cl : this.clases) {
			//System.out.println(cl.getMetodo(nombre));
			if (cl.getMetodo(nombre)) {
				return cl.getNombre();
			}
		}
		return null;
	}
}
