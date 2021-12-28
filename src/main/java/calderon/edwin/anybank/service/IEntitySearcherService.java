package calderon.edwin.anybank.service;

import calderon.edwin.anybank.model.Model;

import java.util.List;
import java.util.UUID;

public interface IEntitySearcherService<T extends Model> {
    List<T> getEntitiesByFkey(UUID id, String sortBy, String order);
}
