package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<PostDTO> showAll() {
        List<Post> postsRepo = postRepository.findAll();
        List<PostDTO> result = postsRepo.stream()
                                .map(this::postToDTO)
                                .toList();

        return result;
    }

    @GetMapping("/{id}")
    public PostDTO showPost(@PathVariable long id) {
        Post post = postRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        PostDTO result = postToDTO(post);

        return result;
    }

    private PostDTO postToDTO(Post post) {
        PostDTO result = new PostDTO();

        result.setId(post.getId());
        result.setTitle(post.getTitle());
        result.setBody(post.getBody());

        List<Comment> postComments = commentRepository.findByPostId(post.getId());
        result.setComments(postComments.stream()
                            .map(this::commentToDTO)
                            .toList());

        return result;
    }

    private CommentDTO commentToDTO(Comment comment) {
        CommentDTO result = new CommentDTO();

        result.setId(comment.getId());
        result.setBody(comment.getBody());

        return result;
    }
}
// END
