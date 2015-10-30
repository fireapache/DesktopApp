
public class NAtributes extends MeanMetric
{

	NAtributes()
	{
		super();
		result.metric = "NAtributes";
	}

	public void calculate()
	{
		if (project != null)
		{
			int counter = 0;
			float variation = 0;
			float deviation = 0;

			for (int i = 0; i < project.classes.size(); i++)
			{
				counter += project.classes.get(i).metrics.nAtributes;
			}

			result.value = (float)counter / (float)project.classes.size();

			for (int i = 0; i < project.classes.size(); i++)
			{
				variation += Math.pow((project.classes.get(i).metrics.nAtributes - result.value), 2.0);
			}

			variation = variation / (float)project.classes.size();
			deviation = (float)Math.sqrt(variation);

			try
			{
				((MeanResult)result).stdDeviation = deviation;
			}
			catch (Exception e)
			{

			}
		}
	}
}