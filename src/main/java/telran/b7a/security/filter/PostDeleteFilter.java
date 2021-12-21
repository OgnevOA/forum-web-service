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

import telran.b7a.forum.dao.ForumMongoRepository;
import telran.b7a.forum.model.Post;
import telran.b7a.security.SecurityContext;
import telran.b7a.security.UserProfile;

@Service
@Order(40)
public class PostDeleteFilter implements Filter {

	ForumMongoRepository forumRepository;
	SecurityContext securityContext;

	@Autowired
	public PostDeleteFilter(ForumMongoRepository forumRepository, SecurityContext securityContext) {
		this.forumRepository = forumRepository;
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
				String[] uriStrings = request.getRequestURI().split("/");
				String postId = uriStrings[uriStrings.length-1];
				Post post = forumRepository.findById(postId).orElse(null);
				if (post == null) {
					response.sendError(404, "Post not found");
					return;
				}
				if (!((post.getAuthor().equals(user.getLogin())) || user.getRoles().contains("MODERATOR"))) {
					response.sendError(403, "You can not delete this post");
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}
	
	private boolean checkEndPoints(String path, String method) {
		return method.equalsIgnoreCase("Delete") && path.matches("[/]forum[/]post[/]\\w+[/]?");
	}

}
