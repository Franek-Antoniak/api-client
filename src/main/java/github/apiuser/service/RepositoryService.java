package github.apiuser.service;

import github.apiuser.model.RepositoryDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RepositoryService {
	private final RestTemplate restTemplate;
	@Value("${github.api.base.url}")
	private String GITHUB_API_BASE_URL;

	public RepositoryDetails getRepositoryDetails(String owner, String name) {
		String url = GITHUB_API_BASE_URL + "/repos/" + owner + "/" + name;
		return restTemplate.getForObject(url, RepositoryDetails.class);
	}
}
