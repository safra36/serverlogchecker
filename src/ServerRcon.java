import com.github.koraktor.steamcondenser.steam.servers.SourceServer;

public class ServerRcon {

    private String serverIP;
    private String serverPassword;
    private int serverPort;

    public ServerRcon(String ip, String password, int port){

        this.serverIP = ip;
        this.serverPassword = password;
        this.serverPort = port;

    }

    public String sendCommand2(String command){

        String results = null;
        try {
            SourceServer server = new SourceServer(this.serverIP, this.serverPort);
            server.rconAuth(this.serverPassword);
            results = server.rconExec(command);
        }
        catch(Exception e) {
            System.out.println("[ERROR LOG]: "+ e.getMessage());
            return "1";
        }
        return results;
    }


}
