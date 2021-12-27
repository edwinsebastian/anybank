package calderon.edwin.anybank.dto;

import calderon.edwin.anybank.model.Model;

public interface IDto<T extends Model> {
    T toModel();
}
