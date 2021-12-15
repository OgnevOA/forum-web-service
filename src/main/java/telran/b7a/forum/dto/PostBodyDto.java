package telran.b7a.forum.dto;

import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostBodyDto {
	
	String title;
	String content;
	Set<String> tags;
	
	@JsonIgnore
    public Optional<String> getTitleOptional() {
        return Optional.ofNullable(title);
    }
	
	@JsonIgnore
    public Optional<String> getContentOptional() {
        return Optional.ofNullable(content);
    }
	
	@JsonIgnore
    public Optional<Set<String>> getTagsOptional() {
        return Optional.ofNullable(tags);
    }

}
