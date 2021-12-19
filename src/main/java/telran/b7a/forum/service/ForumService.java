package telran.b7a.forum.service;

import java.util.List;

import telran.b7a.forum.dto.ContentDto;
import telran.b7a.forum.dto.DateRangeDto;
import telran.b7a.forum.dto.MessageDto;
import telran.b7a.forum.dto.PostBodyDto;

public interface ForumService {
	ContentDto addNewPost(String author, PostBodyDto post);

	ContentDto getPost(String id);

	ContentDto removePost(String id);

	ContentDto updatePost(PostBodyDto postBody, String id);

	void addLike(String id);

	ContentDto addComent(String author, MessageDto message, String id);

	List<ContentDto> findPostsByAuthor(String author);
	
	List<ContentDto> findPostsByTags(List<String> tags);
	
	List<ContentDto> findPostsByPeriod(DateRangeDto data);

}
