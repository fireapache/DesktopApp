
import org.antlr.v4.runtime.tree.ParseTree;

public class ClassMetrics extends JavaBaseListener
{
	private JavaClass _class;
	private JavaParser parser;

	private boolean bReadingClass;
	public  boolean bDebug;

	public int nMethods;
	public int nAtributes;
	public int nCodeLines;

//#region CONSTRUCTORS

	//
	//	Constructors
	//

	ClassMetrics()
	{
		nMethods = 0;
		nAtributes = 0;
		nCodeLines = 0;
		_class = null;
		bReadingClass = true;
		parser = null;
	}

	ClassMetrics(JavaClass origin)
	{
		nMethods = 0;
		nAtributes = 0;
		nCodeLines = 0;
		_class = origin;
		bReadingClass = true;
		parser = null;
	}

	ClassMetrics(JavaClass origin, JavaParser javaParser)
	{
		nMethods = 0;
		nAtributes = 0;
		nCodeLines = 0;
		_class = origin;
		bReadingClass = true;
		parser = javaParser;
	}
//#endregion

//#region METRICS

	//
	//	Class metrics
	//

	/**
	 *	Used to count class atributes.
	 */
	public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx)
	{
		nAtributes++;
	}

	/**
	 *	Used to count class methods.
	 */
	public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx)
	{
		nMethods++;
	}

	/**
	 *	Used to count lines of code.
	 */
	public void enterStatement(JavaParser.StatementContext ctx)
	{
		nCodeLines++;
	}
//#endregion

//#region CLASS CONFIGURATION

	//
	//	Class basic configurations
	//

	/**
	 *	See if it's an abstract class.
	 */
	public void enterClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx)
	{
		if (!bReadingClass) return;

		String context = ctx.getRuleContext().getText();

		if (context.contains("abstract") && _class != null)
		{
			_class.setIsAbstract(true);
			if (bDebug) System.out.println("Abstract: " + _class.getIsAbstract());
		}

	}

	/**
	 *	Gets class and it's super class name.
	 */
	public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx)
	{
		if (!bReadingClass) return;

		if (_class != null)
		{
			_class.setName(ctx.Identifier().getText());	
			if (bDebug) System.out.println("Class: " + _class.getName());
		}

		ParseTree pt;

		for (int i = 0; i < ctx.getChildCount() - 1; i++)
		{
			pt = ctx.getChild(i);

			if (pt.getText().contains("extends"))
			{
				pt = ctx.getChild(i + 1);
				if (bDebug) System.out.println("Super Class: " + pt.getText());

				if (_class != null) _class.setParentName(pt.getText());

				break;
			}
		}

		bReadingClass = false;

	}

	/**
	 *	For debuging porpuses.
	 */
	private void endedReading()
	{
		System.out.println("===== Ended!");

		System.out.println("Number of methods: " + nMethods);
		System.out.println("Number of atributes: " + nAtributes);
		System.out.println("Lines of code: " + nCodeLines);
	}

	/**
	 *	Happens when class reading ends.
	 */
	public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx)
	{
		//if (bDebug) endedReading();
	}

	/**
	 *	Happens when interface reading ends.
	 */
	public void exitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx)
	{
		//if (bDebug) endedReading();
	}

	public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx)
	{
		if (!bReadingClass) return;

		if (_class != null)
		{
			_class.setName(ctx.Identifier().getText());
			_class.setIsInterface(true);
			if (bDebug) System.out.println("Interface: " + _class.getName());
		}

		bReadingClass = false;
	}
//#endregion

}