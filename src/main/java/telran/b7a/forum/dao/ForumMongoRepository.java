package telran.b7a.forum.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.b7a.forum.dto.ContentDto;
import telran.b7a.forum.model.Post;

public interface ForumMongoRepository extends MongoRepository<Post, String> {
	
	public List<ContentDto> findPostsByAuthor(String author);

}
