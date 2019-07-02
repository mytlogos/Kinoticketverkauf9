package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

/**
 * Fachwert für Geldbeträge
 *
 */
public class Geldbetrag {

	private int _geldbetrag;
	
	private Geldbetrag(int euroCent)
	{
		_geldbetrag = euroCent;
	}
	
	
	public int getEuro()
	{
		return _geldbetrag / 100;
	}
	
	
	public int getCent()
	{
		return _geldbetrag % 100;
	}
	
	
	public getFormatierterString()
	{
		return getEuro() + "," + getCent() + "€";
	}
	
	
	public addiere(Geldbetrag summand)
	{
		return new Geldbetrag(this._geldbetrag + summand._geldbetrag);
	}
	
	
	/**
	 * @param subtrahend subtrahend wird vom eigentlichen Geldbetrag abgezogen
	 */
	public subtrahiere(Geldbetrag subtrahend)
	{
		return new Geldbetrag(this._geldbetrag - summand._geldbetrag);
	}
	
	
	public mulipliziere(int faktor)
	
		return new Geldbetrag(this._geldbetrag * faktor);
	}
	
	
	public get
}
