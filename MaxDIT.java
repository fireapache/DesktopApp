
public class MaxDIT extends Metric
{

	MaxDIT()
	{
		super();
		result.metric = "MaxDIT";
	}

	public void calculate()
	{
		if (project != null)
		{
			int counter = 0;
			int localCounter;

			JavaClass parent;

			for (int i = 0; i < project.classes.size(); i++)
			{
				parent = project.classes.get(i).getSuperClass();

				localCounter = 0;

				while (parent != null)
				{
					localCounter++;
					parent = parent.getSuperClass();
				}

				if (localCounter > counter) counter = localCounter;
			}

			result.value = counter;
		}
	}
}