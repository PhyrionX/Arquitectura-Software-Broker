package op;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClaseA implements ClaseAMetodos{
    private static final String NOMBRE = "ClaseA";
    private String[] direccion;
    private Servicios broker;
    
	
	public ClaseA(String host) throws NotBoundException {
		super();
		try {
			Registry registryBroker = LocateRegistry.getRegistry(host);
			broker = (Servicios) registryBroker.lookup("Broker");
			direccion = (Inet4Address.getLocalHost().toString()).split("/");
			// Registra su servidor
			System.out.println(broker.registrarServidor(direccion[1], NOMBRE));
			// Registra sus servicios
			System.out.println(broker.registrarServicios("darFecha", direccion[1], NOMBRE));
			System.out.println(broker.registrarServicios("darHora", direccion[1], NOMBRE));
		} catch (UnknownHostException e) {
			System.out.println("Error de a la hora de registrar al broker");
			System.exit(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
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
//            ClaseA claseA = new ClaseA("localhost");
//            ClaseAMetodos stub =	
//                (ClaseAMetodos) UnicastRemoteObject.exportObject(claseA, 0);
//            Registry registry = LocateRegistry.getRegistry();
//            registry.rebind(NOMBRE, stub);
//            System.out.println("ClaseA arrancado");
//            Servicios broker = (Servicios) registry.lookup("Broker");
//            System.out.println(broker.listarMetodos());
////            System.out.println(broker.ejecutarServicios("introLibros", "stringParams", "Op soy ruben"));
////            System.out.println(broker.ejecutarServicios("listarLibros", "noParams"));
//        } catch (Exception e) {
//            System.err.println("ComputeEngine exception:");
//            e.printStackTrace();
//        }
//	}


	@Override
	public String darFecha() throws RemoteException {
		// TODO Auto-generated method stub
		GregorianCalendar gc = new GregorianCalendar();
		return String.format("%02d/%02d/%04d",
					gc.get(Calendar.DAY_OF_MONTH),
					gc.get(Calendar.MONTH) + 1 , 
					gc.get(Calendar.YEAR));

	}

	@Override
	public String darHora() throws RemoteException {
		GregorianCalendar gc = new GregorianCalendar();
		return String.format("%02d:%02d:%02d",
					gc.get(Calendar.HOUR_OF_DAY) , 
					gc.get(Calendar.MINUTE),
					gc.get(Calendar.SECOND));
	}
	
}
