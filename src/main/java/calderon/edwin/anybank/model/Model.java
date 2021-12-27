package calderon.edwin.anybank.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@Data
@MappedSuperclass
public class Model implements Serializable {
    @Id
    private UUID id;
}
