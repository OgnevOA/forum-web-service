package telran.b7a.forum.dto;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostBodyDto {
	
	String title;
	String content;
	Set<String> tags;

}
