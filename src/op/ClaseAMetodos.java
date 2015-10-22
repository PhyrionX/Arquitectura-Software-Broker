package op;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClaseAMetodos extends Remote{
	public String darFecha() throws RemoteException;
	public String darHora() throws RemoteException;
}
