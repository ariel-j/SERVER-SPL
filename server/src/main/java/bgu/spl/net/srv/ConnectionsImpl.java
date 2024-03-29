package bgu.spl.net.srv;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
public class ConnectionsImpl<T> implements Connections<T> {

    // Fields
    private final ConcurrentHashMap<Integer, ConnectionHandler<T>> connections;
    int idCounter;

    // Constructor
    public ConnectionsImpl() {
        connections = new ConcurrentHashMap<Integer, ConnectionHandler<T>>();
        idCounter = -1;
    }

    // Methods:
    public void connect(int connectionId, ConnectionHandler<T> handler){
        if(!connections.containsKey(connectionId) && handler != null) // check
            connections.put(connectionId, handler);
    }

    public boolean send(int connectionId, T msg){
        ConnectionHandler<T> connectionHandler = connections.get(connectionId);
        if(connectionHandler!= null){
            connectionHandler.send(msg);
            return true;
        }
        return false;
    }

    public void disconnect(int connectionId){
            connections.remove(connectionId);
    }

    public int addconnection (ConnectionHandler<T> ch){
        idCounter++;
        connect(idCounter,ch);
        return idCounter;

    }
}