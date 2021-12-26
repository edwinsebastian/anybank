package calderon.edwin.anybank.service;

import calderon.edwin.anybank.model.Model;

import java.util.List;
import java.util.UUID;

public interface ICrudService<T extends Model> {

    T createEntity(T model);

    T getEntity(UUID id);

    List<T> getEntities();

    UUID updateEntity(UUID id, T model);

    UUID deleteEntity(UUID id);
}
