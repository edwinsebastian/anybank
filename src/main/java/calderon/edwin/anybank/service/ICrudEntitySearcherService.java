package calderon.edwin.anybank.service;

import calderon.edwin.anybank.dto.IDto;
import calderon.edwin.anybank.model.Model;

public interface ICrudEntitySearcherService<T extends Model, U extends IDto<T>> extends ICrudService<T,U>, IEntitySearcherService<T>{
}
