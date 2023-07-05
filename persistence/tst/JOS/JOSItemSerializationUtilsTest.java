package JOS;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import verwaltung.Lager;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JOSItemSerializationUtilsTest {

    @org.junit.jupiter.api.Test
    void serializeTest() {
        try {
            ObjectOutput oo = Mockito.mock(ObjectOutput.class);
            Lager lager = new Lager();
            ArgumentCaptor<Lager> captorLager = ArgumentCaptor.forClass(Lager.class);
            JOSItemSerializationUtils.serialize(oo, lager);
            verify(oo).writeObject(captorLager.capture());

            Lager written = captorLager.getValue();
            assertTrue(lager == written);


        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
    @org.junit.jupiter.api.Test
    void deserializeTest(){
        try{
            ObjectInput oi = Mockito.mock(ObjectInput.class);
            when(oi.readObject()).thenReturn(new Lager());

            Lager loadedLager = JOSItemSerializationUtils.deserialize(oi);

            assertEquals(0, loadedLager.getCargoList().size());
            


        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            fail();
        }
    }

}