package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoOikeinAlussa() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(500);
        assertEquals("saldo: 15.0", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimiiOikein() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenEiTapahduJosSaldoEiRiita() {
        kortti.otaRahaa(2000);
        assertEquals("saldo: 10.0", kortti.toString());
    }
        @Test
        public void otaRahaaPalauttaaTrueKunRahatRiittivat() {
            assertTrue(kortti.otaRahaa(500));
    }
        @Test
        public void otaRahaaPalauttaaFalseKunRahatEiRiita() {
            assertFalse(kortti.otaRahaa(2000));
    }
        @Test
        public void saldoPalauttaaSaldonOikein() {
            assertEquals(1000, kortti.saldo());
        }
}
