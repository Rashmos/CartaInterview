package rules.engine;

import java.util.List;
import java.util.Map;
import roles.Investor;
import roles.InvestorHoldings;

public interface RulesEngine {
    Map<Investor, List<Payout>> execute(double distributionAmount,
            Map<Investor, InvestorHoldings> investorInvestorHoldingsMap);
}
