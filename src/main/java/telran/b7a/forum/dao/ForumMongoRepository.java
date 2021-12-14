package telran.b7a.forum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.b7a.forum.model.Post;

public interface ForumMongoRepository extends MongoRepository<Post, String> {

}
