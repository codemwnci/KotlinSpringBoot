package codemwnci.bootdemo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import java.time.Instant
import javax.persistence.*

interface TodoRepository: JpaRepository<Todo, Long>

@RestController @RequestMapping(value = "/todo") @EnableWebMvc
class TodoResource(val todoRepo: TodoRepository) {

    @GetMapping(value = "/")
    fun getAll() = todoRepo.findAll()

    @GetMapping(value = "/{id}")
    fun getOne(@PathVariable id: Long) = todoRepo.findById(id)

    @PostMapping(value = "/")
    fun new(@RequestBody text: String) = todoRepo.save(Todo(text = text))

    @DeleteMapping(value = "/{id}")
    fun delete(@PathVariable id: Long) = todoRepo.deleteById(id)

    @PutMapping(value = "/{id}")
    fun update(@PathVariable id: Long, @RequestBody todo: Todo): Todo {
        val toUpdate: Todo = todoRepo.findById(id).orElseThrow { Exception("server error") }
        toUpdate.text = todo.text
        toUpdate.done = todo.done
        return todoRepo.save(toUpdate)
    }
}

@Entity
class Todo(@Id @GeneratedValue(strategy = GenerationType.AUTO)
        val Id: Long = 0, var text: String = "", var done: Boolean = false, val createdAt: Instant = Instant.now())