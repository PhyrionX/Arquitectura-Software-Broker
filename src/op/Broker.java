package op;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Broker implements Servicios{
	private HashMap<String, Servidores> servidores = new HashMap<String, Servidores>();

	public Broker() {
		super();
	}
	
	@Override
	public String registrarServicios(String nombreServicio, String ... parametros)
			throws RemoteException {
		try {
			Servidores servidor = this.servidores.get(parametros[0]);
			servidor.getClase(parametros[1]).addMetodo(nombreServicio);
			return "registrado correctamente los metodos";
		} catch (NullPointerException e) {
			return "no tienes registrado el servicio";
		}
	}

	@Override
	public String ejecutarServicios(String nombreServicio, String ... parametros)
			throws RemoteException {
		// Recorremos el hashmap de direcciones de las maquinas que tenemos registradas
		for (Entry<String, Servidores> entry : this.servidores.entrySet()) {
			
			Servidores server = this.servidores.get(entry.getKey());
			try {
				// Buscamos el nombreServicio del método a llamar
				// Y recuperamos el nombre del servicio registrado
				// si coincide con claseA Utilizamos este método
				if (server.getMetodo(nombreServicio).equals("ClaseA")) {
					Registry registry = LocateRegistry.getRegistry(entry.getKey());
						ClaseAMetodos claseA;
						try {
							claseA = (ClaseAMetodos) registry.lookup("ClaseA");
							return (String) claseA.getClass().getMethod(nombreServicio).invoke(claseA);
						} catch (NotBoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				} else if(server.getMetodo(nombreServicio).equals("ClaseB")) {
					Registry registry = LocateRegistry.getRegistry(entry.getKey());
						ClaseBMetodos claseB;
						try {
							claseB = (ClaseBMetodos) registry.lookup("ClaseB");
							if (parametros[0].equals("noParams")) {
								return (String) claseB.getClass().getMethod(nombreServicio).invoke(claseB);
							} else if (parametros[0].equals("stringParams")) {
								return (String) claseB.getClass().getMethod(nombreServicio,String.class).invoke(claseB, parametros[1]);							
							}
						} catch (NotBoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
				}
			} catch (NullPointerException ex) {
				return "No ha introducido ningún metodo válido";
			}
		}

		return nombreServicio;
        
	}

	/**
	 * Registramos los host/Servidor, puede haber mas de uno en una máquina
	 */
	@Override
	public String registrarServidor(String hostRemoto, String nombreRegistrado) {
		if (this.servidores.containsKey(hostRemoto)) {
			Servidores servidor = this.servidores.get(hostRemoto);
			if(servidor.getClase(nombreRegistrado) != null) {
				servidor.delClase(nombreRegistrado);
				//System.out.println(servidores.get(hostRemoto));
			}
			servidor.addClase(nombreRegistrado);
		} else {
			Servidores servidor = new Servidores(hostRemoto, nombreRegistrado);
			servidores.put(hostRemoto, servidor);
		}
		
		System.out.println(servidores.get(hostRemoto));
		return "Ok";
	}

	@Override
	public String listarMetodos() throws RemoteException {
		String metodos = "";
		for (Entry<String, Servidores> entry : this.servidores.entrySet()) {
			Servidores server = this.servidores.get(entry.getKey());
			String respuestaMetodos = server.getMetodos();
			metodos += respuestaMetodos;
		}
		return metodos;
	}

}
