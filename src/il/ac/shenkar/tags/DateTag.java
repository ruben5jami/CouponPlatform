package il.ac.shenkar.tags;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTag extends SimpleTagSupport
{
 public void doTag() throws JspException, IOException
 {
 JspWriter out = getJspContext().getOut();
 String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
 out.print("<h3>"+ timeStamp +"</h3>");
 }
}
