package telran.b7a.forum.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.b7a.forum.model.Post;

public interface ForumMongoRepository extends MongoRepository<Post, String> {
	
	public Stream<Post> findPostsByAuthor(String author);
	
	public Stream<Post> findByTagsIn(List<String> tags);
	
	public Stream<Post> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);

}
