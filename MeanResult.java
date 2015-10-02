
public class MeanResult extends Result
{
	public float stdDeviation;

	public String getText()
	{
		return metric + ": " + value + " --- deviation: " + stdDeviation;
	}
}