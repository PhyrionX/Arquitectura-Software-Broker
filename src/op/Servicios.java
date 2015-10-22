package op;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Servicios extends Remote{
	public String registrarServidor(String hostRemoto, String nombreRegistrado) throws RemoteException;
	public String registrarServicios(String nombreServicio, String ... parametros) throws RemoteException;
	public String ejecutarServicios(String nombreServicio, String... parametros) throws RemoteException;
	public String listarMetodos() throws RemoteException;
}
