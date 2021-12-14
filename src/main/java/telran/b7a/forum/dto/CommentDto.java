package telran.b7a.forum.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {
	String user;
	String message;
	LocalDateTime dateCreated;
	Integer likes;
}
