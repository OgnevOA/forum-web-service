package telran.b7a.security.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import telran.b7a.accounting.dao.AccountingMongoRepository;
import telran.b7a.accounting.model.User;
import telran.b7a.security.SecurityContext;
import telran.b7a.security.SessionService;
import telran.b7a.security.UserProfile;

@Service
@Order(10)
public class AuthenticationFilter implements Filter {

	AccountingMongoRepository repository;
	SecurityContext securityContext;
	SessionService sessionService;

	@Autowired
	public AuthenticationFilter(AccountingMongoRepository repository, SecurityContext securityContext,
			SessionService sessionService) {
		this.repository = repository;
		this.securityContext = securityContext;
		this.sessionService = sessionService;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (checkEndPoints(request.getServletPath(), request.getMethod())) {
			String token = request.getHeader("Authorization");
			String sessionId = request.getSession().getId();
			User userAccount = sessionService.getUser(sessionId);
			if (token == null && userAccount == null) {
				response.sendError(401, "Header Authorization not found");
				return;
			}
			if (token != null) {
				String[] credentials = getCredentials(token).orElse(null);
				if (credentials == null || credentials.length < 2) {
					response.sendError(401, "Token not valid");
					return;
				}
				userAccount = repository.findById(credentials[0]).orElse(null);
				if (userAccount == null) {
					response.sendError(401, "User not found");
					return;
				}
				if (!BCrypt.checkpw(credentials[1], userAccount.getPassword())) {
					response.sendError(401, "User or password not valid");
					return;
				}
				sessionService.addUser(sessionId, userAccount);
			}
			request = new WrappedRequest(request, userAccount.getLogin());
			UserProfile user = UserProfile.builder()
						.login(userAccount.getLogin())
						.password(userAccount.getPassword())
						.roles(userAccount.getRoles())
						.build();
			securityContext.addUser(user);
		}
		chain.doFilter(request, response);

	}

	private boolean checkEndPoints(String path, String method) {
		return !(("POST".equalsIgnoreCase(method) && path.matches("[/]account[/]register[/]?"))
				|| (path.matches("[/]forum[/]posts([/]\\w+)+[/]?")));
	}

	private class WrappedRequest extends HttpServletRequestWrapper {
		String login;

		public WrappedRequest(HttpServletRequest request, String login) {
			super(request);
			this.login = login;
		}

		@Override
		public Principal getUserPrincipal() {
			return () -> login;
		}
	}

	private Optional<String[]> getCredentials(String token) {
		String[] res = null;
		try {
			token = token.split(" ")[1];
			byte[] bytesDecode = Base64.getDecoder().decode(token);
			token = new String(bytesDecode);
			res = token.split(":");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(res);
	}

}
