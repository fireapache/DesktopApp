
public class NCodeLines extends MeanMetric
{

	NCodeLines()
	{
		super();
		result.metric = "NCodeLines";
	}

	public void calculate()
	{
		if (project != null)
		{
			int counter = 0;
			float deviation = 0;

			for (int i = 0; i < project.classes.size(); i++)
			{
				counter += project.classes.get(i).metrics.nCodeLines;
			}

			//MeanResult newResult = new MeanResult();

			result.value = (float)counter / (float)project.classes.size();

			for (int i = 0; i < project.classes.size(); i++)
			{
				deviation += Math.pow((project.classes.get(i).metrics.nCodeLines - result.value), 2.0);
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