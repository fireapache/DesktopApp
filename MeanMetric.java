
public abstract class MeanMetric extends Metric
{
	protected NClasses nClasses;

	MeanMetric()
	{
		nClasses = new NClasses();
		nClasses.setProject(project);
		nClasses.calculate();
	}

	public abstract void calculate();

}