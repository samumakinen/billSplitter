
import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void alussaRahaaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void alussaMyytyjaLounaitaNolla() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }
   @Test
   public void edullisenOstoKateisellaTasaraha() {
       assertEquals(0, kassa.syoEdullisesti(240));
       assertEquals(1, kassa.edullisiaLounaitaMyyty());
   }
   @Test
   public void maukkaanOstoKateisellaTasaraha() {
       assertEquals(0, kassa.syoMaukkaasti(400));
       assertEquals(1, kassa.maukkaitaLounaitaMyyty());
   }
   @Test
   public void edullisenOstoKateisellaYlimaaraistaRahaa() {
       assertEquals(50, kassa.syoEdullisesti(290));
       assertEquals(1, kassa.edullisiaLounaitaMyyty());
   }
   @Test
   public void maukkaanOstoKateisellaYlimaaraistaRahaa() {
       assertEquals(50, kassa.syoMaukkaasti(450));
       assertEquals(1, kassa.maukkaitaLounaitaMyyty());
   }
   @Test
   public void edullisenOstoKateisellaEiTarpeeksiRahaa() {
       assertEquals(150, kassa.syoEdullisesti(150));
       assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
   }
   @Test
   public void maukkaanOstoKateisellaEiTarpeeksiRahaa() {
       assertEquals(150, kassa.syoMaukkaasti(150));
       assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
   }
   
   // Maksukortilliset testit alkavat
   
    @Test
    public void edullisenOstoKortillaTasaraha() {
        Maksukortti temp = new Maksukortti (240);
        assertTrue(kassa.syoEdullisesti(temp));
        assertEquals(0, temp.saldo());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void maukkaanOstoKortillaTasaraha() {
        Maksukortti temp = new Maksukortti (400);
        assertTrue(kassa.syoMaukkaasti(temp));
        assertEquals(0, temp.saldo());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void edullisenOstoKortillaYlimaaraistaRahaa() {
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(760, kortti.saldo());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void maukkaanOstoKortillaYlimaaraistaRahaa() {
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(600, kortti.saldo());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void edullisenOstoKortillaEiTarpeeksiRahaa() {
        Maksukortti temp = new Maksukortti(150);
        assertFalse(kassa.syoEdullisesti(temp));
        assertEquals(150, temp.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void maukkaanOstoKortillaEiTarpeeksiRahaa() {
        Maksukortti temp = new Maksukortti(150);
        assertFalse(kassa.syoMaukkaasti(temp));
        assertEquals(150, temp.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void kortilleLataaminenPositiivisilla() {
        kassa.lataaRahaaKortille(kortti, 5000);
        assertEquals(6000, kortti.saldo());
        assertEquals(105000, kassa.kassassaRahaa());
    }
    @Test
    public void kortilleLataaminenNegatiivisilla() {
        kassa.lataaRahaaKortille(kortti, -500);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
}