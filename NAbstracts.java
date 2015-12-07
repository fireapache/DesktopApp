
public class NAbstracts extends Metric
{

	NAbstracts()
	{
		result.metric = "abstracts";
	}

	public void calculate()
	{
		if (project != null)
		{
			int counter = 0;

			for (int i = 0; i < project.classes.size(); i++)
			{
				if (project.classes.get(i).getIsAbstract())
				{
					counter++;
				}
			}

			result.value = counter;
		}
	}
}