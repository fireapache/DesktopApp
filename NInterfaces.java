
public class NInterfaces extends Metric
{

	public NInterfaces()
	{
		super();
		result.metric = "NInterfaces";
	}

	public void calculate()
	{
		if (project != null)
		{
			int counter = 0;

			for (int i = 0; i < project.classes.size(); i++)
			{
				if (project.classes.get(i).getIsInterface())
				{
					counter++;
				}
			}

			result.value = counter;
		}
	}
}