package github.apiuser.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryDetails {
	@JsonAlias("full_name")
	private String fullName;
	private String description;
	@JsonAlias("clone_url")
	private String cloneUrl;
	@JsonAlias("stargazers_count")
	private int stars;
	@JsonAlias("created_at")
	private String createdAt;
}