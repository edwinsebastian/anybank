package calderon.edwin.anybank.service;

import calderon.edwin.anybank.dto.IDto;
import calderon.edwin.anybank.model.Model;

import java.util.List;
import java.util.UUID;

public interface ICrudService<T extends Model, U extends IDto<T>> {

    T createEntity(U dto);

    T getEntity(UUID id);

    List<T> getEntities();

    UUID updateEntity(T model);

    UUID deleteEntity(UUID id);
}
