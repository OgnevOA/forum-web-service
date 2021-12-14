package telran.b7a.forum.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.forum.dao.ForumMongoRepository;
import telran.b7a.forum.dto.ContentDto;
import telran.b7a.forum.dto.DateRangeDto;
import telran.b7a.forum.dto.MessageDto;
import telran.b7a.forum.dto.PostBodyDto;
import telran.b7a.forum.dto.exception.PostNotFoundException;
import telran.b7a.forum.model.Comment;
import telran.b7a.forum.model.Post;

@Service
public class ForumServiceImpl implements ForumService {
	
	ForumMongoRepository forumRepository;
	ModelMapper modelMapper;

	@Autowired
	public ForumServiceImpl(ForumMongoRepository forumRepository, ModelMapper modelMapper) {
		this.forumRepository = forumRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ContentDto addNewPost(String author, PostBodyDto postBody) {
		Post post = modelMapper.map(postBody, Post.class);
		post.setAuthor(author);
		post.setDateCreated(LocalDateTime.now());
		forumRepository.save(post);
		return modelMapper.map(post, ContentDto.class);
	}

	@Override
	public ContentDto getPost(String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		return modelMapper.map(post, ContentDto.class);
	}

	@Override
	public ContentDto removePost(String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		forumRepository.deleteById(id);
		return modelMapper.map(post, ContentDto.class);
	}

	@Override
	public ContentDto updatePost(String id, PostBodyDto postBody) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		post.setTitle(postBody.getTitle());
		post.setContent(postBody.getContent());
		post.addTags(postBody.getTags());
		forumRepository.save(post);
		return modelMapper.map(post, ContentDto.class);
	}

	@Override
	public void addLike(String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		post.addLike();
		forumRepository.save(post);
	}

	@Override
	public ContentDto addComent(String id, String author, MessageDto message) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		Comment comment = modelMapper.map(message, Comment.class);
		comment.setUser(author);
		comment.setDateCreated(LocalDateTime.now());
		post.addComment(comment);
		forumRepository.save(post);
		return modelMapper.map(post, ContentDto.class);
	}

	@Override
	public List<ContentDto> findPostsByAuthor(String author) {
		return forumRepository.findPostsByAuthor(author)
								.map(p -> modelMapper.map(p, ContentDto.class))
								.collect(Collectors.toList());
	}

	@Override
	public List<ContentDto> findPostsByTags(List<String> tags) {
		return forumRepository.findPostsByTags(tags)
								.map(p -> modelMapper.map(p, ContentDto.class))
								.collect(Collectors.toList());
	}

	@Override
	public List<ContentDto> findPostsByPeriod(DateRangeDto date) {
		return forumRepository.findByDateCreatedBetween(date.dateTimeFrom(), date.dateTimeTo())
								.map(p -> modelMapper.map(p, ContentDto.class))
								.collect(Collectors.toList());
	}
	

}
