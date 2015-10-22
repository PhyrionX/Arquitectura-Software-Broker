package op;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import propias.Teclado;

public class Launcher {
	public static void main(String[] args) {
		System.out.println("Que desea lanzar:");
		System.out.println("1.- Broker");
		System.out.println("2.- ClaseA");
		System.out.println("3.- ClaseB");
		System.out.println("0.- Salir");
		
		int opcion;
		do {
			opcion = Teclado.leerEntero("Elige opcion: ");
			switch (opcion) {
				case 0: System.out.println("Gracias por venir");
					break;
				case 1: 
					try {
			            String name = "Broker";
			            Broker broker = new Broker();
			            Servicios stub =
			                (Servicios) UnicastRemoteObject.exportObject(broker, 0);
			            Registry registry = LocateRegistry.getRegistry();
			            registry.rebind(name, stub);
			            System.out.println("Broker arrancado");
			            opcion = 0;
			        } catch (Exception e) {
			            System.err.println("Broker exception:");
			            e.printStackTrace();
			        }
					break;	
				case 2:
					try {
						String host = Teclado.leerCadena("Introduce la dirección del broker:");
			            ClaseA claseA = new ClaseA(host);
			            ClaseAMetodos stub =	
			                (ClaseAMetodos) UnicastRemoteObject.exportObject(claseA, 0);
			            Registry registry = LocateRegistry.getRegistry();
			            registry.rebind("ClaseA", stub);
			            System.out.println("ClaseA arrancado");
			            Servicios broker = claseA.getBroker();
			            System.out.println("Lista de metodos a usar: ");
			            System.out.println(broker.listarMetodos());
			            usarMetodos(broker);
			            opcion = 0;
			        } catch (Exception e) {
			            System.err.println("ClaseA Exception:");
			            e.printStackTrace();
			            opcion = 0;
			        }
					break;
				case 3:
					try {
						String host1 = Teclado.leerCadena("Introduce la dirección del broker:");
			            ClaseB claseB = new ClaseB(host1);
			            ClaseBMetodos stub =	
			                (ClaseBMetodos) UnicastRemoteObject.exportObject(claseB, 0);
			            Registry registry = LocateRegistry.getRegistry();
			            registry.rebind("ClaseB", stub);
			            System.out.println("ClaseB arrancado");
			            Servicios broker = claseB.getBroker();
			            System.out.println("Lista de metodos a usar: ");
			            System.out.println(broker.listarMetodos());
			            usarMetodos(broker);
			            opcion = 0;
			        } catch (Exception e) {
			            System.err.println("ClaseB exception:");
			            e.printStackTrace();
			            opcion = 0;
			        }
					break;
			}
		} while (opcion != 0);
	}

	private static void usarMetodos(Servicios broker) {
		String metodo;
		do {
			metodo = Teclado.leerCadena("Elige método: (FIN para salir) , (REFRESCAR para refrescar metodos)");
			try {
				if(metodo.equals("REFRESCAR")) {
					System.out.println("Lista de metodos:\n" + broker.listarMetodos());
				} else if (metodo.equals("FIN")) {
					System.out.println("Gracias por su visita");
				} else {
					if (metodo.equals("introLibros")) {
						System.out.println(broker.ejecutarServicios(metodo, "stringParams", Teclado.leerCadena("Libro que quiere introducir:")));
					} else {
						System.out.println(broker.ejecutarServicios(metodo, "noParams"));
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!metodo.equals("FIN"));
	}

}
