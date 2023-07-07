package JOS;

import cargos.UtilityClass;
import verwaltung.Lager;

import java.io.*;
import java.util.Collection;

public class JOSItemSerializationUtils {
    public static void serialize(String fileName, Lager lager){ //zwei zuständigkeiten speichern und speicherort
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            serialize(oos, lager);
        }catch (IOException f) {
            f.printStackTrace();
        }
    }
    public static void serialize(ObjectOutput objectOutput, Lager lager)throws IOException{
        objectOutput.writeObject(lager);
    }
    public static Lager deserialize(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Lager lager = (Lager)ois.readObject();
            int i=0;
            while(lager.getCargoList().get(i) != null){
                lager.getCargoList().get(i).getDurationOfStorage();
                lager.setMonitor(new Object());
                UtilityClass.setStorageLocation(lager.getCargoList().get(i),i);
                i++;
            }
            return lager;
        }
        catch (FileNotFoundException e) {
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
    public static Lager deserialize(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        return (Lager)objectInput.readObject();
    }

}
