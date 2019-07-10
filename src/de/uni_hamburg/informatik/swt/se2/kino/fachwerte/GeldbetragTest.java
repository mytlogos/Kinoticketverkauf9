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
        assertEquals(1, p2.getEuro());
        assertEquals(5, p2.getCent());     
        assertEquals(1, p3.getEuro());
        assertEquals(5, p3.getCent());
    }
    
    @Test
    public void testGeldbetragSumme()
    {
    	Geldbetrag p = Geldbetrag.get(210);
    	Geldbetrag p2 = Geldbetrag.get(105);
    	
    	Geldbetrag summe = p.addiere(p2);
    	
        assertEquals(3, summe.getEuro());
        assertEquals(15, summe.getCent());
    }
    
    @Test
    public void testGeldbetragDifferenz()
    {
    	Geldbetrag p = Geldbetrag.get(210);
    	Geldbetrag p2 = Geldbetrag.get(105);
    	
    	Geldbetrag summe = p.subtrahiere(p2);
    	
        assertEquals(1, summe.getEuro());
        assertEquals(5, summe.getCent());
    }
    
    @Test
    public void testGeldbetragProdukt()
    {
    	Geldbetrag p = Geldbetrag.get(210);
    	
    	Geldbetrag summe = p.multipliziere(105);
    	
        assertEquals(220, summe.getEuro());
        assertEquals(50, summe.getCent());
    }
    
    
    
    @Test
    public void testGeldbetragGetString()
    {
    	Geldbetrag p = Geldbetrag.get("0");
    	Geldbetrag p2 = Geldbetrag.get("101,05");
    	Geldbetrag p3 = Geldbetrag.get("-101,05");
        assertEquals(0, p.getEuro());
        assertEquals(0, p.getCent());
        assertEquals(101, p2.getEuro());
        assertEquals(5, p2.getCent());
        assertEquals(-101, p3.getEuro());
        assertEquals(-5, p3.getCent());
    }

    @Test
    public void testEqualsUndHashCode()
    {
        Geldbetrag p1 = Geldbetrag.get(1);
        Geldbetrag p2 = Geldbetrag.get(1);
        Geldbetrag p3 = Geldbetrag.get(3001);
        Geldbetrag p4 = Geldbetrag.get(3201);
        Geldbetrag p5 = Geldbetrag.get(-3201);
        Geldbetrag p6 = Geldbetrag.get(-3201);

        assertFalse(p1.equals(null));
        
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());

        assertFalse(p1.equals(p3));
        assertFalse(p1.equals(p4));
        
        assertEquals(p5, p6);
        assertEquals(p5.hashCode(), p6.hashCode());
    }
    
    
    @Test
    public void testAddierbar()
    {
    	Geldbetrag p1 = Geldbetrag.get(Integer.MAX_VALUE);
    	Geldbetrag p2 = Geldbetrag.get(Integer.MIN_VALUE);
    	Geldbetrag p3 = Geldbetrag.get(1);
    	
    	assertFalse(p1.addierbar(p1));
    	assertTrue(p2.addierbar(p3));
    	assertTrue(p1.addierbar(p2));
    	assertTrue(p3.addierbar(p3));
    }
    
    
    @Test
    public void testSubtrahierbar()
    {
    	Geldbetrag p1 = Geldbetrag.get(Integer.MAX_VALUE);
    	Geldbetrag p2 = Geldbetrag.get(Integer.MIN_VALUE);
    	Geldbetrag p3 = Geldbetrag.get(1);
    	
    	assertFalse(p1.subtrahierbar(p2));
    	assertFalse(p2.subtrahierbar(p3));
    	assertTrue(p1.subtrahierbar(p1));
    }
    
    
    @Test
    public void testMultiplizierbar()
    {
    	Geldbetrag p1 = Geldbetrag.get(Integer.MAX_VALUE);
    	Geldbetrag p2 = Geldbetrag.get(Integer.MIN_VALUE);
    	
    	assertTrue(p1.multiplizierbar(1));
    	assertTrue(p1.multiplizierbar(-1));
    	assertTrue(p2.multiplizierbar(1));
    	assertFalse(p2.multiplizierbar(-1));
    	assertFalse(p1.multiplizierbar(2));
    	assertFalse(p1.multiplizierbar(-2));
    	assertFalse(p2.multiplizierbar(Integer.MIN_VALUE));
    	assertFalse(p1.multiplizierbar(Integer.MIN_VALUE));
    	assertFalse(p2.multiplizierbar(Integer.MAX_VALUE));
    	assertFalse(p1.multiplizierbar(Integer.MAX_VALUE));
    }
}
