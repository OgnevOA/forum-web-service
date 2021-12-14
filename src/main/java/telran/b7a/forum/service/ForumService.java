package telran.b7a.forum.service;

import java.util.List;

import telran.b7a.forum.dto.ContentDto;
import telran.b7a.forum.dto.MessageDto;
import telran.b7a.forum.dto.PostBodyDto;

public interface ForumService {
	ContentDto addNewPost(String author, PostBodyDto post);

	ContentDto getPost(String id);

	ContentDto removePost(String id);

	ContentDto updatePost(String id, PostBodyDto postBody);

	void addLike(String id);

	ContentDto addComent(String id, String author, MessageDto message);

	List<ContentDto> findPostsByAuthor(String author);

}
