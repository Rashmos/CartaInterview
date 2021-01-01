package rules.engine;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import roles.Investor;
import roles.InvestorHoldings;

public class RulesEngineBase {
    @Getter
    @Setter
    private Map<Investor,InvestorHoldings> investorHoldingsMap;
}
