
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
			float deviation = 0;

			for (int i = 0; i < project.classes.size(); i++)
			{
				counter += project.classes.get(i).metrics.nAtributes;
			}

			//MeanResult newResult = new MeanResult();

			result.value = counter / (float)project.classes.size();

			for (int i = 0; i < project.classes.size(); i++)
			{
				deviation += Math.pow((project.classes.get(i).metrics.nAtributes - result.value), 2.0);
			}

			deviation = (float)Math.sqrt(deviation / (float)project.classes.size());

			//newResult.stdDeviation = deviation;

			//result = newResult;

			System.out.println("==== NMethods");
			System.out.println("Result: " + result.value);
			System.out.println("Deviation: " + deviation);
		}
	}
}