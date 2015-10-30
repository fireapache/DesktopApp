
public abstract class MeanMetric extends Metric
{
	protected NClasses nClasses;

	MeanMetric()
	{
		nClasses = new NClasses();
		result = new MeanResult();
	}

	public abstract void calculate();

	public void setProject(Project proj)
	{
		project = proj;
		nClasses.setProject(project);
		nClasses.calculate();
	}

}