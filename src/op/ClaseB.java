package op;

import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class ClaseB implements ClaseBMetodos{
    private static final String NOMBRE = "ClaseB";
    private String[] direccion;
    private Servicios broker;
    private ArrayList<String> libros = new ArrayList<String>();
	
	public ClaseB(String host) throws NotBoundException, UnknownHostException {
		super();
		try {
			Registry registryBroker = LocateRegistry.getRegistry(host);
			broker = (Servicios) registryBroker.lookup("Broker");
			direccion = (Inet4Address.getLocalHost().toString()).split("/");
			// Registra el servidor
			System.out.println(broker.registrarServidor(direccion[1], NOMBRE));
			// Registra sus servicios
			System.out.println(broker.registrarServicios("listarLibros", direccion[1], NOMBRE));
			System.out.println(broker.registrarServicios("introLibros", direccion[1], NOMBRE));
		} catch (UnknownHostException e) {
			System.out.println("Error de a la hora de registrar al broker");
			System.exit(0);
		} catch (RemoteException e) {
			System.out.println("Error de a la hora de registrar al broker");
			System.exit(0);
		}
	}
	
	public Servicios getBroker() {
		return this.broker;
	}
	
//	public static void main(String[] args) {
//		try {
//
//            ClaseB claseB = new ClaseB("localhost");
//            ClaseBMetodos stub =	
//                (ClaseBMetodos) UnicastRemoteObject.exportObject(claseB, 0);
//            Registry registry = LocateRegistry.getRegistry();
//            registry.rebind(NOMBRE, stub);
//            System.out.println("ClaseA arrancado");
//            Servicios broker = (Servicios) registry.lookup("Broker");
////            System.out.println(broker.ejecutarServicios("darFecha", "noParams"));
////            System.out.println(broker.ejecutarServicios("darHora", "noParams"));
//        } catch (Exception e) {
//            System.err.println("ComputeEngine exception:");
//            e.printStackTrace();
//        }
//	}

	@Override
	public String listarLibros() throws RemoteException {
		// TODO Auto-generated method stub
		String cadena = "Lista de libros:\n";
		for (String libro : this.libros) {
			cadena += libro + "\n";
		}
		return cadena;
	}

	@Override
	public String introLibros(String libro) throws RemoteException {
		// TODO Auto-generated method stub
		this.libros.add(libro);
		return "Se ha introducido el libro -> " + libro;
	}


	
}
