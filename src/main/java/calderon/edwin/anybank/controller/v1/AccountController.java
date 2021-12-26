package calderon.edwin.anybank.controller.v1;

import calderon.edwin.anybank.controller.ICrudController;
import calderon.edwin.anybank.dto.AccountDto;
import calderon.edwin.anybank.model.AccountModel;
import calderon.edwin.anybank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/account")
public class AccountController implements ICrudController<AccountModel, AccountDto> {
    private final AccountService accountService;

    @Override
    public ResponseEntity<AccountDto> createResource(AccountDto accountDto) {
        AccountModel accountModel = accountService.createEntity(AccountDto.toAccountModel(accountDto));
        return ResponseEntity.ok(new AccountDto(accountModel));
    }

    @Override
    public ResponseEntity<AccountModel> getResource(UUID id) {
        return ResponseEntity.ok(accountService.getEntity(id));
    }

    @Override
    public ResponseEntity<List<AccountModel>> getResources() {
        return ResponseEntity.ok(accountService.getEntities());
    }

    @Override
    public ResponseEntity<AccountDto> updateResource(UUID id, AccountDto updatedDto) {
        return null;
    }

    @Override
    public ResponseEntity<AccountDto> deleteResource(UUID id) {
        return null;
    }
}
