import java.io.File;
import java.util.*;

public class Main {

    public static HashMap<String, Integer> userWarningList = new HashMap<>();


    public static void main(String[] args) {

        final String serverIP = args[0];
        final String rconPassword = args[2];
        final String logPath = args[3];
        final String serverPort = args[1];
        final int checknum = Integer.valueOf(args[4]);

        System.out.println("Server is starting ...");
        System.out.println("ServerIP: " + serverIP);
        System.out.println("ServerPort: " + serverPort);
        System.out.println("Server Rcon Password: " + rconPassword);
        System.out.println("logPath: " + logPath);

        System.out.println("Setting up ServerRcon Class ...");

        ServerRcon serverRcon = new ServerRcon(serverIP, rconPassword, Integer.valueOf(serverPort));

        System.out.println("Setting up checker timer ...");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ArrayList<String> conLog = new ArrayList<>();
                conLog = new Utils().getConsoleLog(new File(logPath));
                for(int i=0;i<conLog.size();i++){

                    if(conLog.get(i).contains("clc_VoiceData")){
                        String userIP = new Utils().applyRegex("(\\d{0,3}\\.\\d{0,3}\\.\\d{0,3}\\.\\d{0,3})", conLog.get(i)).get(0);
                        if(userIP != null){
                            System.out.println("IP Captured: " + userIP);
                            if(userWarningList.get(userIP) != null){
                                int userWarn = userWarningList.get(userIP);
                                if(userWarn >= checknum){
                                    serverRcon.sendCommand2(String.format("addip 0 " + userIP));
                                }else{
                                    System.out.println("IP Address " + userIP + " was captured for " + userWarn + "th time.");
                                    userWarn++;
                                    userWarningList.put(userIP, userWarn);
                                }
                            }else{
                                System.out.println("New ip added to the warning list: " + userIP);
                                userWarningList.put(userIP, 1);
                            }


                        }else{
                            System.out.println("Attack message captured but could not read ip address.");
                        }
                    }else if(conLog.get(i).contains("CCLCMsg_VoiceData")){
                        String userIP = new Utils().applyRegex("(\\d{0,3}\\.\\d{0,3}\\.\\d{0,3}\\.\\d{0,3})", conLog.get(i)).get(0);
                        if(userIP != null){
                            System.out.println("IP Captured: " + userIP);
                            if(userWarningList.get(userIP) != null){
                                int userWarn = userWarningList.get(userIP);
                                if(userWarn >= checknum){
                                    serverRcon.sendCommand2(String.format("addip 0 " + userIP));
                                }else{
                                    System.out.println("IP Address " + userIP + " was captured for " + userWarn + "th time.");
                                    userWarn++;
                                    userWarningList.put(userIP, userWarn);
                                }
                            }else{
                                System.out.println("New ip added to the warning list: " + userIP);
                                userWarningList.put(userIP, 1);
                            }


                        }else{
                            System.out.println("Attack message captured but could not read ip address.");
                        }
                    }else if(conLog.get(i).contains("svc_VoiceData")){
                        String userIP = new Utils().applyRegex("(\\d{0,3}\\.\\d{0,3}\\.\\d{0,3}\\.\\d{0,3})", conLog.get(i)).get(0);
                        if(userIP != null){
                            System.out.println("IP Captured: " + userIP);
                            if(userWarningList.get(userIP) != null){
                                int userWarn = userWarningList.get(userIP);
                                if(userWarn >= checknum){
                                    serverRcon.sendCommand2(String.format("addip 0 " + userIP));
                                }else{
                                    System.out.println("IP Address " + userIP + " was captured for " + userWarn + "th time.");
                                    userWarn++;
                                    userWarningList.put(userIP, userWarn);
                                }
                            }else{
                                System.out.println("New ip added to the warning list: " + userIP);
                                userWarningList.put(userIP, 1);
                            }


                        }else{
                            System.out.println("Attack message captured but could not read ip address.");
                        }
                    }
                }

            }
        }, 1000, 1000);

        Timer cleaner  = new Timer();
        cleaner.schedule(new TimerTask() {
            @Override
            public void run() {

                Iterator iterator = userWarningList.entrySet().iterator();
                while (iterator.hasNext()){

                    Map.Entry pair = (Map.Entry)iterator.next();
                    userWarningList.remove(pair.getKey());
                }
            }
        }, 20000, 20000);


    }
}
