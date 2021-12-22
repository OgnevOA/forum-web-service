package telran.b7a.security.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(30)
public class OwnerLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			if (checkEndPoints(request.getServletPath(), request.getMethod())) {
				String[] uriStrings = request.getRequestURI().split("/");
				String name = uriStrings[uriStrings.length-1];
				if (!(principal.getName().equalsIgnoreCase(name))) {
					response.sendError(403);
					return;
				}
			}
		}
		chain.doFilter(request, response);

	}

	private boolean checkEndPoints(String path, String method) {
		return 		path.matches("[/]account[/]user[/]\\w+[/]?") 
				|| (path.matches("[/]forum[/]post[/]\\w+[/]?") && "Post".equalsIgnoreCase(method))
				||	path.matches("[/]forum[/]post[/]\\w+[/]comment[/]\\w+[/]?");
	}

}
