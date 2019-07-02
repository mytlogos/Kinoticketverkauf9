package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fachwert für Geldbeträge
 *
 */
public final class Geldbetrag {

	private final int _eurocent;
	
	private Geldbetrag(int euroCent)
	{
		_eurocent = euroCent;
	}
	
	
	public int getEuro()
	{
		return _eurocent / 100;
	}
	
	
	public int getCent()
	{
		return _eurocent % 100;
	}
	
	
	public String getFormatierterString()
	{
		return getEuro() + "," + getCent() + "€";
	}
	
	
	public Geldbetrag addiere(Geldbetrag summand)
	{
		assert addierbar(summand):"Integer-overflow, vorbedingung verletzt";
		return new Geldbetrag(this._eurocent + summand._eurocent);
	}
	
	
	/**
	 * @param subtrahend subtrahend wird vom eigentlichen Geldbetrag abgezogen
	 */
	public Geldbetrag subtrahiere(Geldbetrag subtrahend)
	{
		assert subtrahierbar(subtrahend):"Integer-overflow, vorbedingung verletzt";
		return new Geldbetrag(this._eurocent - subtrahend._eurocent);
	}
	
	
	public Geldbetrag multipliziere(int faktor)
	{
		assert multiplizierbar(faktor):"Integer-overflow, vorbedingung verletzt";
		return new Geldbetrag(this._eurocent * faktor);
	}
	
	
	public static Geldbetrag get(int eurocent)
	{
		return new Geldbetrag(eurocent);
	}
	
	
	public static Geldbetrag get(String eurocentString)
	{
		Matcher matcher = Pattern.compile("(-?)(\\d{1,8})(,(\\d\\d))?").matcher(eurocentString);
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
	
	
	public boolean multiplizierbar(int faktor)
	{
		if(Integer.MAX_VALUE / faktor > this._eurocent)
		{
			return true;
		}
		if(Integer.MIN_VALUE / faktor < this._eurocent && faktor < 0)
		{
			return true;
		}
		
		return false;
	}
	
	
	public boolean addierbar(Geldbetrag geldbetrag)
	{
		return Integer.MAX_VALUE - geldbetrag._eurocent <= _eurocent;
	}
	
	
	public boolean subtrahierbar(Geldbetrag geldbetrag)
	{
		return Integer.MAX_VALUE + geldbetrag._eurocent <= _eurocent;
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
