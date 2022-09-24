package fullstack.fullstacktodo.controller;

import fullstack.fullstacktodo.dto.TodoDTO;
import lombok.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/todo")
public class TodoController {

    @PutMapping("/addTodo/{newTodo}")
    public List<TodoDTO> addTodo(@PathVariable @NonNull String newTodo) {
        return null;
    }
}

