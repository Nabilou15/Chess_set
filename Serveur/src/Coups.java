public class Coups
{
	private COUPS_SPECIAL Coups ;

	public Coups ()
	{
		this.Coups = COUPS_SPECIAL.NORMAL ;
	}

	public Coups (COUPS_SPECIAL coup)
	{
		this.Coups = coup ;
	}

	public COUPS_SPECIAL get_Coups() 
	{
		return this.Coups ;
	}
}
