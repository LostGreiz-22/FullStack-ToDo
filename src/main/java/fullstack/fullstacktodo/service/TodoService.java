package fullstack.fullstacktodo.service;

import fullstack.fullstacktodo.dto.TodoDTO;
import fullstack.fullstacktodo.entity.TodoListEntity;
import fullstack.fullstacktodo.model.Todo;
import fullstack.fullstacktodo.repository.TodoRepository;
import fullstack.fullstacktodo.util.DateTimeUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoService {

    private final DateTimeUtil dataTimeUtil;
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public List<TodoDTO> addTodo(@NonNull String newTodo) {

        //Initialize new todo
        DateTimeUtil dateTimeUtil;
        TodoListEntity todo = TodoListEntity.builder()
                .todoId(UUID.randomUUID())
                .todo(newTodo)
                .createdData(dateTimeUtil.currentDate())
                .modifiedDate(dateTimeUtil.currentDate())
                .build();

        // save to database
        todoRepository.save(todo);

        return getAllTodos();
        public List<TodoDTO> updateTodo(@NonNull Todo updatedTodo) {

            // Get Todo Entity
            TodoListEntity oldTodo = todoRepository.findByTodoId(updatedTodo.getTodoId());

            // Check if todoId exist
            if (oldTodo == null) throw new RuntimeException("Todo doesn't exist.");

            // Update Todo
            TodoListEntity newTodo = TodoListEntity.builder()
                    .todoId(oldTodo.getTodoId())
                    .todo(updatedTodo.getTodo())
                    .createdDate(oldTodo.getCreatedDate())
                    .modifiedDate(dateTimeUtil.currentDate())
                    .build();

            // save to database
            todoRepository.save(newTodo);

            return getAllTodos();

        }


    }

    private  List<TodoDTO> getAllTodos() {
        List<TodoListEntity> allTodos = todoRepository.findAll();
        List<TodoDTO> updatedList = new ArrayList<>();

        allTodos.forEach(data ->{
            updatedList.add(modelMapper.map(data, TodoDTO.class));
        });

        return updatedList;
    }
}
