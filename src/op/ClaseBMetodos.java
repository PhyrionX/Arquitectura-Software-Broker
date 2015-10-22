package op;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClaseBMetodos extends Remote{
	public String listarLibros() throws RemoteException;
	public String introLibros(String libro) throws RemoteException;
}
