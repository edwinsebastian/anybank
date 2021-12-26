package calderon.edwin.anybank.controller;

import calderon.edwin.anybank.model.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ICrudController<T extends Model, R> {

    @PostMapping
    ResponseEntity<R> createResource(@RequestBody R dto);

    @GetMapping("/{id}")
    ResponseEntity<T> getResource(@PathVariable UUID id);

    @GetMapping
    ResponseEntity<List<T>> getResources();

    @PutMapping("/{id}")
    ResponseEntity<R> updateResource(@PathVariable UUID id, @RequestBody R updatedDto);

    @DeleteMapping("/{id}")
    ResponseEntity<R> deleteResource(@PathVariable UUID id);
}