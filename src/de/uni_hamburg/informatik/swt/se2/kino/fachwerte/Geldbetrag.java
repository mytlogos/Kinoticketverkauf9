package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fachwert für Geldbeträge
 * Alle Werte sind erlaubt (auch negative).
 */
public final class Geldbetrag {

	private final int _eurocent;
	
	private Geldbetrag(int euroCent)
	{
		_eurocent = euroCent;
	}
	

	/**
	 * 
	 * @return euro anteil von diesen Geldbetrag
	 */
	public int getEuro()
	{
		return _eurocent / 100;
	}
	
	
	/**
	 * 
	 * @return cent anteil von diesem Geldbetrag
	 */
	public int getCent()
	{
		return _eurocent % 100;
	}
	
	
	/**
	 * Gibt einen String im Format -?\d+,\d{0,2}€ zurück.
	 * @return formatierter String
	 * 
	 * @ensure result != null && !result.isEmpty()
	 */
	public String getFormatierterString()
	{
		return String.format("%d,%s€",getEuro(),Math.abs(getCent()));
	}
	
	/**
	 * 
	 * @param summand summand der mit diesem Geldbetrag addiert werden soll
	 * @return geldbetrag Summe dieser und des summand
	 * 
	 * @require addierbar(summand)
	 * @ensure result != null
	 */
	public Geldbetrag addiere(Geldbetrag summand)
	{
		assert addierbar(summand):"Integer-overflow, vorbedingung verletzt";
		return new Geldbetrag(this._eurocent + summand._eurocent);
	}
	
	
	/**
	 * @param subtrahend subtrahend wird vom eigentlichen Geldbetrag abgezogen
	 * 
	 * @result result differenz von diesem Geldbetrag und subtrahend
	 * 
	 * @require subtrahierbar(subtrahend)
	 * @ensure result != null
	 */
	public Geldbetrag subtrahiere(Geldbetrag subtrahend)
	{
		assert subtrahierbar(subtrahend):"Integer-overflow, vorbedingung verletzt";
		return new Geldbetrag(this._eurocent - subtrahend._eurocent);
	}
	
	/**
	 * 
	 * @param faktor
	 * @return Produkt von faktor mit diesem Geldbetrag
	 * 
	 * @require multiplizierbar(faktor)
	 * @ensure result != null
	 */
	public Geldbetrag multipliziere(int faktor)
	{
		assert multiplizierbar(faktor):"Integer-overflow, vorbedingung verletzt";
		return new Geldbetrag(this._eurocent * faktor);
	}
	
	/**
	 * Testet ob dieser Geldbetrag größer gleich dem geldbetrag ist.
	 * 
	 * @param geldbetrag
	 * @return true, falls dieser Geldbetrag größer als geldbetrag ist
	 * 
	 * @require geldbetrag != null
	 */
	public boolean istGroeßerGleich(Geldbetrag geldbetrag) {
		assert geldbetrag != null : "Vorbedingung verletzt: null";
		return _eurocent >= geldbetrag._eurocent;
	}
	
	/**
	 * Gibt den Betrag der Differenz dieses und des anderen Geldbetrages wieder.
	 * 
	 * @param geldbetrag geldbetrag von dem der Betrag der Differenz 
	 * 		  mit diesem Geldbetrag gebildet werden soll
	 * @return Absolute Differenz von diesem und dem anderen Geldbetrag
	 * 
	 * @require geldbetrag != null
	 * @ensure result != null && result._eurocent >= 0
	 */
	public Geldbetrag absoluteDifferenz(Geldbetrag geldbetrag) {
		assert geldbetrag != null : "Vorbedingung verletzt: null";
		return new Geldbetrag(Math.abs(geldbetrag._eurocent - _eurocent));
	}
	
	/**
	 * Gibt zurück ob dieser Geldbetrag positiv ist.
	 * @return true, falls dieser Geldbetrag positiv ist
	 */
	public boolean istPositiv() {
		return _eurocent > 0;
	}
	
	/**
	 * Factory-Methode für Geldbetrag von int.
	 * 
	 * @param eurocent betrag von dem ein Geldbetrag gebildet werden soll
	 * @return ein Geldbetrag objekt
	 * 
	 * @ensure result != null
	 */
	public static Geldbetrag get(int eurocent)
	{
		return new Geldbetrag(eurocent);
	}
	
	
	/**
	 * Factory-Methode für Geldbetrag von String.
	 * Wandelt einen String im Format -?\d{1,8}(,\d\d)? in ein Geldbetrag um.
	 * 
	 * @param eurocentString String der umgewandelt werden soll
	 * @return ein Geldbetrag objekt
	 * 
	 * @require eurocentString != null && !eurocentString.isEmpty()
	 * @ensure result != null
	 */
	public static Geldbetrag get(String eurocentString)
	{
		assert eurocentString != null : "Vorbedingung verletzt: null";
		assert !eurocentString.isEmpty() : "Vorbedingung verletzt: ist leer";
		
		Matcher matcher = Pattern.compile("(-?)(\\d{1,8})(,(\\d{1,2})?)?").matcher(eurocentString);
		if(!matcher.matches()) {
			throw new IllegalArgumentException();
		}
		int eurocent = Integer.parseInt(matcher.group(2)) * 100;
		if (matcher.group(4) != null) 
		{
			eurocent += Integer.parseInt(matcher.group(4));	
		}
		
		if("-".equals(matcher.group(1)))
		{
			eurocent *= -1;
		}
		
		return new Geldbetrag(eurocent);
	}
	
	/**
	 * Testet ob die Multiplikation des Faktors mit diesem Geldbetrag
	 * ein gültiges Ergebnis liefert.
	 * 
	 * @param faktor Faktor für die Multiplikation
	 * @return true, falls die Multiplikation ein gültiges Ergebnis liefern würde
	 */
	public boolean multiplizierbar(int faktor)
	{
		return ((long) faktor * (long)_eurocent) == faktor*_eurocent;
	}
	
	
	/**
	 * Testet ob die Addition des Summanden mit diesem Geldbetrag 
	 * ein gültiges Ergebnis liefert.
	 * 
	 * @param geldbetrag Summand für die Addition
	 * @return true, falls die Addition ein gültiges Ergebnis liefern würde
	 * 
	 * @require geldbetrag != null
	 */
	public boolean addierbar(Geldbetrag geldbetrag)
	{
		assert geldbetrag != null : "Vorbedingung verletzt: null";
		int a = geldbetrag._eurocent;
		int b = _eurocent;
		return a > 0 != b > 0 || a + b > 0 == a > 0;
	}
	
	
	/**
	 * Testet ob die Subtraktion dieses Geldbetrags
	 * mit dem Minuenden ein gültiges Ergebnis liefert.
	 * 
	 * @param geldbetrag Minuend für die Subtraktion
	 * @return true, falls die Subtraktion ein gültiges Ergebnis liefern würde
	 * 
	 * @require geldbetrag != null
	 */
	public boolean subtrahierbar(Geldbetrag geldbetrag)
	{
		assert geldbetrag != null : "Vorbedingung verletzt: null";
		int a = geldbetrag._eurocent;
		int b = _eurocent;
		return (a-b) < a == (b > 0);
	}
	
	
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Geldbetrag))
		{
			return false;
		}
		return this._eurocent == ((Geldbetrag)obj)._eurocent;
	}
	
	
	public int hashCode()
	{
		return _eurocent;
	}
}
