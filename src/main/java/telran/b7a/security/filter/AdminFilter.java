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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import telran.b7a.accounting.dao.AccountingMongoRepository;
import telran.b7a.security.SecurityContext;
import telran.b7a.security.UserProfile;

@Service
@Order(20)
public class AdminFilter implements Filter {
	AccountingMongoRepository repository;
	SecurityContext securityContext;

	@Autowired
	public AdminFilter(AccountingMongoRepository repository, SecurityContext securityContext) {
		this.repository = repository;
		this.securityContext = securityContext;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			UserProfile user = securityContext.getUser(principal.getName());
			if (checkEndPoints(request.getServletPath(), request.getMethod())) {
				if (!user.getRoles().contains("Administrator".toUpperCase())) {
					response.sendError(403);
					return;
				}
			}
		}
		chain.doFilter(request, response);

	}

	private boolean checkEndPoints(String path, String method) {
		return path.matches("[/]account[/]user[/]\\w+[/]role[/]\\w+[/]?");
	}

}