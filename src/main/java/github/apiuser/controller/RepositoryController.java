package github.apiuser.controller;

import github.apiuser.model.RepositoryDetails;
import github.apiuser.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/repositories")
@RequiredArgsConstructor
public class RepositoryController {
	private final RepositoryService repositoryService;

	@GetMapping("/{owner}/{name}")
	public ResponseEntity<RepositoryDetails> getRepositoryDetails(
			@PathVariable String owner,
			@PathVariable String name) {
		RepositoryDetails details = repositoryService.getRepositoryDetails(owner, name);
		return ResponseEntity.ok(details);
	}

	@ExceptionHandler(HttpClientErrorException.NotFound.class)
	public ResponseEntity<String> handleNotFound() {
		return ResponseEntity.notFound().build();
	}
}

