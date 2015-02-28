package il.ac.shenkar.filter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


/**
 * web filter for calculating response time int the controller
 * and the model
 * get the current time before the request 
 * get the time after the request, substract the two 
 * @author ruben5jami
 *
 */
@WebFilter(filterName="responseTime", urlPatterns="/controller/*")
public class ResponseTimeFilter implements Filter {
	protected FilterConfig config;
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.config = config;
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		      throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	    long startTime = System.currentTimeMillis();
	    chain.doFilter(request, response);
	    long elapsed = System.currentTimeMillis() - startTime;

	    out.println("<h6 class='center'>Response Time is: " + elapsed + " ms</h6>");
	  }
	}



