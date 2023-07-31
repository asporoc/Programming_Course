package logger;

import logger.LogEnum;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LogUtil {
    private HashMap<String, String> sprachDict;
    Object monitor = new Object();

    public LogUtil(String languageCode) {
        sprachDictLaden(languageCode);
    }

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
        if (!logFile.exists()) {
            try {
                if (logFile.createNewFile()) {
                    if (!logFile.setReadable(true, false) || !logFile.setWritable(true, true)) {
                        System.err.println("Fehler beim Setzen der Zugriffsrechte für die Log-Datei.");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            synchronized (monitor) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
                    writer.write(logMessage);
                    writer.newLine();
                } catch (IOException e) {
                }
            }
        }
    }
}

