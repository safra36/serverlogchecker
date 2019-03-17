# serverlogchecker
A very simple java program that checks your server's console (Any Source GameServer) for attacks and capture the attacker ip.

Starting up:

According to this peice of code taken form the app it self, the application has 5 launch options:

- final String serverIP = args[0] --> Your server's ip
- final String serverPort = args[1] --> Your server's port
- final String rconPassword = args[2] --> Your server's rcon password
- final String logPath = args[3] --> Your server's console.log path.
- final int checknum = Integer.valueOf(args[4]) --> How much a message should be shown before the attacker's ip get banned.

something like this:

java -jar ServerLogChecker.jar 127.0.0.1 27015 passWord "C:/serverPath/csgo/console.log" 5

- To generate console.log in that path add -condebug to your launch options.
