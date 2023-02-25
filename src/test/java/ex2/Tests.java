package ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
public class Tests {
    @ParameterizedTest
    @CsvSource({"1, elemento1"})
    void test_put_enHashTableVacio(String key, String value) {
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        Assertions.assertEquals("\n bucket[1] = [1, elemento1]", hashTable.toString());
        Assertions.assertEquals(1,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());

    }
    @org.junit.jupiter.api.Test
    void test_put_enHashTableNoVacio() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.put("2","elemento2");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, elemento1]\n" +
                " bucket[2] = [2, elemento2]", hashTable.toString());
        Assertions.assertEquals(2,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());


    }
    @org.junit.jupiter.api.Test
    void test_put_enHashTableNoVacioConColisionCon2() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.put("12","elemento2");
        Assertions.assertEquals("\n bucket[1] = [1, elemento1] -> [12, elemento2]", hashTable.toString());
        Assertions.assertEquals(2,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());


    }
    @org.junit.jupiter.api.Test
    void test_put_enHashTableNoVacioConColisionCon3() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.put("12","elemento2");
        hashTable.put("23","elemento3");
        Assertions.assertEquals("\n bucket[1] = [1, elemento1] -> [12, elemento2] -> [23, elemento3]", hashTable.toString());
        Assertions.assertEquals(3,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());


    }
    @org.junit.jupiter.api.Test
    void test_put_ActualizarElementoSinColision() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.put("1","elemento1.1");
        System.out.println(hashTable.toString());
        String bucket = "\n bucket[1] = [1, elemento1.1]";
        Assertions.assertEquals(bucket,hashTable.toString());
        Assertions.assertEquals(1,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());


    }
    @org.junit.jupiter.api.Test
    void test_put_ActualizarElementoCon1Colision() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.put("12","elemento1.1");
        hashTable.put("12","elemento1.2");
        System.out.println(hashTable.toString());
        String bucket = "\n" +
                " bucket[1] = [1, elemento1] -> [12, elemento1.2]";
        Assertions.assertEquals(bucket,hashTable.toString());
        Assertions.assertEquals(2,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());


    }
    @org.junit.jupiter.api.Test
    void test_put_ActualizarElementoCon2Colision() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.put("12","elemento2");
        hashTable.put("23","elemento3");
        hashTable.put("23","elemento3.2");
        System.out.println(hashTable.toString());
        String bucket = "\n" +
                " bucket[1] = [1, elemento1] -> [12, elemento2] -> [23, elemento3.2]";
        Assertions.assertEquals(bucket,hashTable.toString());
        Assertions.assertEquals(3,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());


    }
    @org.junit.jupiter.api.Test
    void test_put_ActualizarElementoCon3Colision() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.put("12","elemento2");
        hashTable.put("23","elemento3");
        hashTable.put("34","elemento4");
        hashTable.put("34","elemento4.2");
        System.out.println(hashTable.toString());
        String bucket = "\n" +
                " bucket[1] = [1, elemento1] -> [12, elemento2] -> [23, elemento3] -> [34, elemento4.2]";
        Assertions.assertEquals(bucket,hashTable.toString());
        Assertions.assertEquals(4,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());


    }
    @org.junit.jupiter.api.Test
    void test_get_enHashTableVacio() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.get("1");
        Assertions.assertEquals("elemento1", hashTable.get("1").toString());
    }
    @org.junit.jupiter.api.Test
    void test_get_enHashTableNoVacio1Posicion() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.put("1","elemento2");

        Assertions.assertEquals("elemento2", hashTable.get("1").toString());
    }
    @org.junit.jupiter.api.Test
    void test_get_enHashTableNoVacio2Posicion() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.put("12","elemento2");
        hashTable.put("12","elemento3");
        hashTable.get("12");
        Assertions.assertEquals("elemento3", hashTable.get("12").toString());
    }
    @org.junit.jupiter.api.Test
    void test_get_enHashTableNoVacio3Posicion() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.put("12","elemento2");
        hashTable.put("23","elemento3");
        hashTable.put("23","elemento4");
        hashTable.get("23");
        Assertions.assertEquals("elemento4", hashTable.get("23").toString());
    }
    @org.junit.jupiter.api.Test
    void test_get_ElementoNoExistenteEnHashTableVacio() {
        HashTable hashTable = new HashTable();
        Assertions.assertNull(hashTable.get("1"));
    }
    @org.junit.jupiter.api.Test
    void test_get_ElementoNoExistenteEnPosicionOcupadaSinColision() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        Assertions.assertNull(hashTable.get("2"));
    }
    @org.junit.jupiter.api.Test
    void test_get_ElementoNoExistenteEnPosicionOcupadaConColision() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.put("12","elemento2");
        hashTable.put("23","elemento3");
        Assertions.assertNull(hashTable.get("34"));
    }
    @org.junit.jupiter.api.Test
    void test_drop() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","elemento1");
        hashTable.drop("1");
        Assertions.assertEquals("",hashTable.toString());
        Assertions.assertEquals(0,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());


    }
    @org.junit.jupiter.api.Test
    void test_drop_ElementoQueNoColisiona() {
        HashTable hashTable = new HashTable();
        hashTable.put("2","elemento1");
        hashTable.put("1","elemento2");
        hashTable.put("12","elemento3");
        hashTable.put("23","elemento4");
        hashTable.drop("2");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, elemento2] -> [12, elemento3] -> [23, elemento4]" ,hashTable.toString());
        Assertions.assertEquals(3,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());


    }
    @org.junit.jupiter.api.Test
    void test_drop_ElementoQueSiColisiona1Posicion() {
        HashTable hashTable = new HashTable();
        hashTable.put("2","elemento1");
        hashTable.put("1","elemento2");
        hashTable.put("12","elemento3");
        hashTable.put("23","elemento4");
        hashTable.drop("1");
        Assertions.assertEquals("\n" +
                " bucket[1] = [12, elemento3] -> [23, elemento4]\n" +
                " bucket[2] = [2, elemento1]" ,hashTable.toString());
        Assertions.assertEquals(3,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());

    }
    @org.junit.jupiter.api.Test
    void test_drop_ElementoQueSiColisiona2Posicion() {
        HashTable hashTable = new HashTable();
        hashTable.put("2","elemento1");
        hashTable.put("1","elemento2");
        hashTable.put("12","elemento3");
        hashTable.put("23","elemento4");
        hashTable.drop("12");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, elemento2] -> [23, elemento4]\n" +
                " bucket[2] = [2, elemento1]" ,hashTable.toString());
        Assertions.assertEquals(3,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());

    }
    @org.junit.jupiter.api.Test
    void test_drop_ElementoQueSiColisiona3Posicion() {
        HashTable hashTable = new HashTable();
        hashTable.put("2","elemento1");
        hashTable.put("1","elemento2");
        hashTable.put("12","elemento3");
        hashTable.put("23","elemento4");
        hashTable.drop("23");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, elemento2] -> [12, elemento3]\n" +
                " bucket[2] = [2, elemento1]" ,hashTable.toString());
        Assertions.assertEquals(3,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());

    }

    @org.junit.jupiter.api.Test
    void test_drop_ElementoBucketVacio() {
        HashTable hashTable = new HashTable();
        hashTable.put("2","elemento1");
        hashTable.put("1","elemento2");
        hashTable.put("12","elemento3");
        hashTable.put("23","elemento4");
        hashTable.drop("3");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, elemento2] -> [12, elemento3] -> [23, elemento4]\n" +
                " bucket[2] = [2, elemento1]" ,hashTable.toString());
        Assertions.assertEquals(4,hashTable.count());
        Assertions.assertEquals(16,hashTable.size());

    }

    @org.junit.jupiter.api.Test
    void test_drop_ElementoNoExisteBucketLlenoNoExistePosicionOcupadaPorOtraQueColisiona(){
        HashTable hashtable = new HashTable();
        hashtable.put("1","elemento1");
        hashtable.drop("12");
        String expected = "\n bucket[1] = [1, elemento1]";
        Assertions.assertEquals(expected,hashtable.toString());
    }

    @org.junit.jupiter.api.Test
    void test_drop_ElementoNoExistePosicionOcupadaPor3ElementosColisionants(){
        HashTable hashtable = new HashTable();
        hashtable.put("1","elemento1");
        hashtable.put("12","elemento2");
        hashtable.put("23","elemento3");
        hashtable.drop("34");
        String expected = "\n bucket[1] = [1, elemento1] -> [12, elemento2] -> [23, elemento3]";
        Assertions.assertEquals(expected,hashtable.toString());
    }
}