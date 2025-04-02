package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import exercise.model.Post;
import exercise.repository.PostRepository;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Post> getFull() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable long id) {
        Post checkPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        return checkPost;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post addNewPost (@RequestBody Post post) {
        postRepository.save(post);

        return post;
    }

    @PutMapping("/{id}")
    public Post editPost(@PathVariable long id,
                         @RequestBody Post post) {
        Post maybePost = postRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        maybePost.setTitle(post.getTitle());
        maybePost.setBody(post.getBody());

        return maybePost;
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable long id) {
        Optional<Post> maybePost = postRepository.findById(id);

        if (maybePost.isPresent()) {
            postRepository.deleteById(id);
            commentRepository.deleteByPostId(id);
        }
    }
}
// END
