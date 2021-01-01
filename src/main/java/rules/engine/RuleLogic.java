package rules.engine;

import java.util.List;
import java.util.Map;
import roles.Investor;
import roles.InvestorHoldings;

public interface RuleLogic {
    Map<Investor, List<Payout>> apply(double distributionAmount, Map<Investor, InvestorHoldings> investorHoldingsMap);

    double geRulePayout();
}
