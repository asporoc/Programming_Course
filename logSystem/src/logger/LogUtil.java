package logger;

import logger.LogEnum;

import java.io.*;
import java.util.Date;
import java.util.HashMap;

public class LogUtil {
    private HashMap<String, String> sprachDict;
    final Object monitor = new Object();

    public LogUtil(String languageCode) {
        sprachDictLaden(languageCode);
    }
    /***********************************************/
    /*Zum Hinzufügen neuer Sprachen muss nur eine  */
    /*[Sprachcode]_dict.txt nach dem Muster der    */
    /*bisherigen Sprach Implementationen erfolgen  */
    /*eine Änderung am SourceCode ist nicht von    */
    /* nöten                                       */

    public LogUtil() {
        sprachDictLaden("DE");
    }

    private void sprachDictLaden(String languageCode) {
        sprachDict = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(languageCode + "_dict.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    sprachDict.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
        }
    }

    public void logChange(LogEnum changeType) {
        String logMessage = sprachDict.get(changeType.toString()) + " - " + new Date();
        File logFile = new File("log.txt");


            synchronized (monitor) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
                    writer.write(logMessage);
                    writer.newLine();
                } catch (IOException e) {
                }
            }
        }
    }

