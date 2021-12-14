package telran.b7a.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.forum.dto.ContentDto;
import telran.b7a.forum.dto.DateRangeDto;
import telran.b7a.forum.dto.MessageDto;
import telran.b7a.forum.dto.PostBodyDto;
import telran.b7a.forum.service.ForumService;

@RestController
@RequestMapping("/forum")
public class ForumController {
	ForumService forumService;
	
	@Autowired
	public ForumController(ForumService forumService) {
		this.forumService = forumService;
	}

	@PostMapping("/post/{author}")
	public ContentDto addPost(@PathVariable String author,@RequestBody PostBodyDto post) {
		return forumService.addNewPost(author, post);
	}
	
	@GetMapping("/post/{id}")
	public ContentDto findPostById(@PathVariable String id) {
		return forumService.getPost(id);
	}
	
	@DeleteMapping("/post/{id}")
	public ContentDto deletePost(@PathVariable String id) {
		return forumService.removePost(id);
	}
	
	@PutMapping("/post/{id}")
	public ContentDto updatePost(@PathVariable String id,@RequestBody PostBodyDto postBody) {
		return forumService.updatePost(id, postBody);
	}
	
	@PutMapping("/post/{id}/like")
	public void addLikeToPost(@PathVariable String id) {
		forumService.addLike(id);
	}
	
	@PutMapping("/post/{id}/comment/{author}")
	public ContentDto addComent(@PathVariable String id,@PathVariable String author,@RequestBody MessageDto message) {
		return forumService.addComent(id, author, message);
	}
	
	@GetMapping("/posts/author/{author}")
	public List<ContentDto> FindPostsByAuthor(@PathVariable String author) {
		return forumService.findPostsByAuthor(author);
	}
	
	@PostMapping("/posts/tags")
	public List<ContentDto> findPostsByTags(@RequestBody List<String> tags) {
		return forumService.findPostsByTags(tags);
	}
	
	@PostMapping("/posts/period")
	public List<ContentDto> findPostsByPeriod(@RequestBody DateRangeDto dates) {
		return forumService.findPostsByPeriod(dates);
	}
	
}
