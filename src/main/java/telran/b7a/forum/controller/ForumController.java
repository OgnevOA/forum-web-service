package telran.b7a.forum.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.forum.dto.ContentDto;
import telran.b7a.forum.dto.PostBodyDto;
import telran.b7a.forum.service.ForumService;

@RestController
public class ForumController {
	ForumService forumService;
	
	@PostMapping("/forum/post/{author}")
	public ContentDto addPost(@PathVariable String author,@RequestBody PostBodyDto post) {
		return forumService.addPost(author, post);
	}
	
	@GetMapping("forum/post/{id}")
	public ContentDto findPostById(@PathVariable String id) {
		return forumService.findPost(id);
	}
	
	@DeleteMapping("forum/post/{id}")
	public ContentDto deletePost(@PathVariable String id) {
		return forumService.deletePost(id);
	}
	
	@PutMapping("forum/post/{id}")
	public ContentDto updatePost(@PathVariable String id,@RequestBody PostBodyDto postBody) {
		return forumService.updatePost(id, postBody);
	}
	
	@PutMapping("/forum/post/{id}/like")
	public void addLikeToPost(@PathVariable String id) {
		forumService.addLike(id);
	}
	
	@PutMapping("/forum/post/{id}/comment/{author}")
	public ContentDto addComent(@PathVariable String id,@PathVariable String author,@RequestBody String message) {
		return forumService.addComent(id, author, message);
	}
	
	@GetMapping("/forum/posts/author/{author}")
	public List<ContentDto> FindPostsByAuthor(@PathVariable String author) {
		return forumService.findPostsByAuthor(author);
	}
	
}
