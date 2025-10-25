package project.api.core

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.api.app.candidates.data.Candidate

abstract class CrudController<T : Any>(
    open val service: CrudService<T>
) {
    @GetMapping
    fun findAll(
        @RequestParam(required = false) example: T?,
        @RequestParam(required = false, defaultValue = "true") ignoreCase: Boolean
    ): ResponseEntity<List<T>> {
        val list = service.findAll(example, ignoreCase);

        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<T> {
        val entity = service.findById(id);

        return ResponseEntity.status(200).body(entity);
    }

    @PostMapping
    fun insert(@RequestBody dto: T): ResponseEntity<T> {
        val insert = service.insert(dto, null);

        return ResponseEntity.status(201).body(insert);
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody dto: T): ResponseEntity<T> {
        val updated = service.update(id, dto);

        return ResponseEntity.status(200).body(updated);
    }

    @DeleteMapping("/{id}")
    open fun delete(@PathVariable id: Int): ResponseEntity<T?> {
        val deleted = service.delete(id);

        return ResponseEntity.status(200).body(deleted);
    }
}
