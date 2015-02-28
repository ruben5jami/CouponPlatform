package il.ac.shenkar.tags;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
public class Copyright extends SimpleTagSupport{
	
private String firstName;

	public void setFirstName(String str){
		firstName = str;
	}
	
	
	public void doTag() throws JspException, IOException{
		JspWriter out = getJspContext().getOut();
		out.print("<h3>&copy; "+firstName +"</h3>");
	}
	
}