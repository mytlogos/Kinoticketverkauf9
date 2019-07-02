package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GeldbetragTest
{
    @Test
    public void testGeldbetragIstGetIntGueltig()
    {
    	Geldbetrag p = Geldbetrag.get(0);
    	Geldbetrag p2 = Geldbetrag.get(105);
    	Geldbetrag p3 = Geldbetrag.get((Integer)105);
    	
        assertEquals(0, p.getEuro());
        assertEquals(0, p.getCent());
        assertEquals(10, p2.getEuro());
        assertEquals(5, p2.getCent());     
        assertEquals(10, p3.getEuro());
        assertEquals(5, p3.getCent());
    }
    
    @Test
    public void testGeldbetragSumme()
    {
    	Geldbetrag p = Geldbetrag.get(210);
    	Geldbetrag p2 = Geldbetrag.get(105);
    	
    	Geldbetrag summe = p.addiere(p2);
    	
        assertEquals(3, p.getEuro());
        assertEquals(15, p.getCent());
    }
    
    @Test
    public void testGeldbetragDifferenz()
    {
    	Geldbetrag p = Geldbetrag.get(210);
    	Geldbetrag p2 = Geldbetrag.get(105);
    	
    	Geldbetrag summe = p.subtrahiere(p2);
    	
        assertEquals(0, p.getEuro());
        assertEquals(105, p.getCent());
    }
    
    @Test
    public void testGeldbetragProdukt()
    {
    	Geldbetrag p = Geldbetrag.get(210);
    	Geldbetrag p2 = Geldbetrag.get(105);
    	
    	Geldbetrag summe = p.multipliziere(p2);
    	
        assertEquals(220, p.getEuro());
        assertEquals(50, p.getCent());
    }
    
    
    
    @Test
    public void testGeldbetragGetString()
    {
    	Geldbetrag p = Geldbetrag.get("0");
    	Geldbetrag p2 = Geldbetrag.get("101,05");
        assertEquals(0, p.getEuro());
        assertEquals(0, p.getCent());
        assertEquals(101, p2.getEuro());
        assertEquals(5, p2.getCent());
    }

    @Test
    public void testEqualsUndHashCode()
    {
        Geldbetrag p1 = Geldbetrag.get(1);
        Geldbetrag p2 = Geldbetrag.get(1);
        Geldbetrag p3 = Geldbetrag.get(3001); // Sitz unterschiedlich
        Geldbetrag p4 = Geldbetrag.get(3201); // Reihe unterschiedlich

        assertTrue(p1.equals(p2));
        assertTrue(p1.hashCode() == p2.hashCode());

        assertFalse(p1.equals(p3));
        assertFalse(p1.equals(p4));
    }
}
