
public abstract class Metric
{
	protected Project project;

	public Result result;

	public Metric()
	{
		result = new Result();
	}

	public abstract void calculate();

	public void setProject(Project proj)
	{
		project = proj;
	}
}