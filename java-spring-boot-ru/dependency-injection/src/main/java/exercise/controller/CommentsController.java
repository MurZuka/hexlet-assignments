package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment getCommentById (@PathVariable long id) {
        Comment checkComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));

        return checkComment;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment addNewComment (@RequestBody Comment comment) {
        commentRepository.save(comment);

        return comment;
    }

    @PutMapping("/{id}")
    public Comment editCommentById(@PathVariable long id,
                                   @RequestBody Comment body) {
        Comment checkComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));

        checkComment.setBody(body.getBody());

        return checkComment;
    }

    @DeleteMapping("/{id}")
    public void deleteComment (@PathVariable long id) {
        commentRepository.deleteById(id);
    }
}
// END
