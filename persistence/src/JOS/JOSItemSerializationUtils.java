package JOS;

import cargos.UtilityClass;
import verwaltung.Lager;

import java.io.*;
import java.util.Collection;

public class JOSItemSerializationUtils {
    public static void serialize(String fileName, Lager lager){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(lager);
        }catch (FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public static Lager deserialize(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Lager lager = (Lager)ois.readObject();
            int i=0;
            while(lager.getCargoList().get(i) != null){
                lager.getCargoList().get(i).getDurationOfStorage();
                lager.setMonitor(new Object());
                UtilityClass.setStorageLocation(lager.getCargoList().get(i),i);
                //lager.getCargoList().get(i).setStorageLocation(i);
                i++;
            }
            return lager;
        }catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error occurred during deserialization: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
