
public class NClasses extends Metric
{

	public NClasses()
	{
		super();
		result.metric = "classes";
	}

	public void calculate()
	{
		if (project != null)
		{
			result.value = project.classes.size();
		}
	}
}