package rules.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import roles.Investor;
import roles.InvestorHoldings;
import rules.list.ProportionalPayoutByShareClassRulesEngine;
import rules.list.ProportionalPayoutGeneral;

public class KrakatoaVenturesRuleEngine implements RulesEngine {
    List<RuleLogic> rules = new ArrayList<>();
    public KrakatoaVenturesRuleEngine() {
        rules.add(new ProportionalPayoutByShareClassRulesEngine());
        rules.add(new ProportionalPayoutGeneral());
    }

    @Override
    public  Map<Investor, List<Payout>>  execute(double distributionAmount, Map<Investor, InvestorHoldings> investorInvestorHoldingsMap) {
        Map<Investor, List<Payout>>  payoutMap = new HashMap<>();
        for (RuleLogic rule : this.rules) {
            Map<Investor, List<Payout>> ruleOutput = rule.apply(distributionAmount, investorInvestorHoldingsMap);
            distributionAmount -= rule.geRulePayout();
            for (Map.Entry<Investor, List<Payout>> entry : ruleOutput.entrySet()) {
                Investor investor = entry.getKey();
                List<Payout> newlyComputedPayouts = entry.getValue();

                if(payoutMap.containsKey(investor)) {
                    List<Payout> existingPayouts = payoutMap.get(investor);
                    existingPayouts.addAll(newlyComputedPayouts);
                    payoutMap.put(investor, existingPayouts);
                } else {
                    payoutMap.put(investor, newlyComputedPayouts);
                }
            }
        }
        return payoutMap;
    }
}
