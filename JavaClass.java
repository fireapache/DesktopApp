
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.Token;

public class JavaClass
{
	private JavaClass inheritance;
	private boolean bIsAbstract;
	private boolean bIsInterface;
	private String name;
	private String parentName;
	private String filePath;

	public ClassMetrics metrics;

	// Parser atributes
	private JavaLexer lexer;
	private CommonTokenStream tokens;
	private JavaParser parser;
	private ParserRuleContext tree;
	private ParseTreeWalker walker;

	JavaClass()
	{
		name = "None";
		parentName = "None";
		bIsAbstract = false;
		bIsInterface = false;
		inheritance = null;
	}

	JavaClass(String path)
	{
		name = "None";
		parentName = "None";
		bIsAbstract = false;
		bIsInterface = false;
		inheritance = null;
		filePath = path;
	}

	// Setters

	public void setIsAbstract(boolean value) 	{ bIsAbstract = value; }
	public void setName(String newName) 		{ name = newName; }
	public void setSuperClass(JavaClass mother)	{ inheritance = mother; }
	public void setIsInterface(boolean value)	{ bIsInterface = value; }
	public void setFilePath(String path)		{ filePath = path; }
	public void setParentName(String parent)	{ parentName = parent; }

	// Getters

	public boolean getIsAbstract()		{ return bIsAbstract; }
	public String getName()				{ return name; }
	public JavaClass getSuperClass()	{ return inheritance; }
	public boolean getIsInterface()		{ return bIsInterface; }
	public String getFilePath()			{ return filePath; }
	public String getParentName()		{ return parentName; }

	// Other procedures

	public void parse()
	{
		try
		{
			lexer = new JavaLexer(new ANTLRFileStream(filePath));
		}
		catch (IOException e)
		{
			System.out.println(e.toString());
			return;
		}

		tokens = new CommonTokenStream(lexer);
		parser = new JavaParser(tokens);
		tree = parser.compilationUnit();
		walker = new ParseTreeWalker();
		metrics = new ClassMetrics(this, parser);
		metrics.bDebug = true;
		walker.walk(metrics, tree);

		tokens = null;
		parser = null;
		tree = null;
		walker = null;
	}

}