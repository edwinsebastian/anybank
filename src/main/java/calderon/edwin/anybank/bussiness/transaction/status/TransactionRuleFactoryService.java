package calderon.edwin.anybank.bussiness.transaction.status;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TransactionRuleFactoryService {
    private final List<ITransactionStatusRule> transactionStatusRules;

    private static final Map<String, ITransactionStatusRule> transactionStatusRuleCache = new HashMap<>();

    public static final String ruleFormat = "%sDateAnd%sChannelRule";

    @PostConstruct
    public void initMyServiceCache() {
        for(ITransactionStatusRule service : transactionStatusRules) {
            transactionStatusRuleCache.put(service.getType(), service);
        }
    }

    public static ITransactionStatusRule getService(String type) {
        ITransactionStatusRule service = transactionStatusRuleCache.get(type);
        if(service == null) throw new RuntimeException("Unknown service type: " + type);

        return service;
    }
}
