package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/users")
public class PostsController{
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts") // Список всех постов, написанных пользователем
    public List<Post> index(@PathVariable Integer id) {
        return posts.stream().filter(p -> p.getUserId() == id).toList();
    }

    @PostMapping("/users/{id}/posts") // Создание поста, привязанного к пользователю
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable Integer id, @RequestBody Post post) {
        post.setUserId(id);

        posts.add(post);
        return post;
    }
}
// END
