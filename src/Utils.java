import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public ArrayList<String> applyRegex(String regex, String data) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        ArrayList<String> returnResults = new ArrayList<String>();
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                if (matcher.group(i) != null) {
                    String eachResult = matcher.group(i);
                    returnResults.add(eachResult);
                }
            }
        }
        return returnResults;
    }

    public ArrayList<String> getConsoleLog(File file){

        ArrayList<String> returnArrayList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String data;
            while ((data = bufferedReader.readLine()) != null){
                returnArrayList.add(data);
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            bufferedWriter.write("Nothing");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnArrayList;
    }


}
