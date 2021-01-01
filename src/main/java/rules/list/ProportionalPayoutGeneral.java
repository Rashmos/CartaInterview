package rules.list;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import roles.HoldingDetails;
import roles.Investor;
import roles.InvestorHoldings;
import rules.engine.Payout;
import rules.engine.RuleLogic;

public class ProportionalPayoutGeneral implements RuleLogic {

    private double rulePayout;

    @Override
    public Map<Investor, List<Payout>> apply(double distributionAmount, Map<Investor,InvestorHoldings> investorHoldingsMap) {
        if(distributionAmount == 0.0) {
            return Collections.emptyMap();
        }
        Map<Investor, List<Payout>> payoutsByInvestor = new HashMap<>();
        int totalHoldingsAcrossShareClasses = 0;

        for (Map.Entry<Investor, InvestorHoldings> entry : investorHoldingsMap
                .entrySet()) {
            Investor investor = entry.getKey();
            int totalUnits = entry.getValue().getInvestorHoldingList()
                    .stream().mapToInt(HoldingDetails::getNumberOfUnits).sum();
            totalHoldingsAcrossShareClasses += totalUnits;
            List<Payout> dummyPayouts =entry.getValue().getInvestorHoldingList()
                    .stream()
                    .map(e -> new Payout(e.getShareClass(), 0.0)).collect(Collectors.toList())
            ;
            payoutsByInvestor.put(investor, dummyPayouts);
        }

        for (Map.Entry<Investor, InvestorHoldings> entry : investorHoldingsMap
                .entrySet()) {
            Investor investor = entry.getKey();
            for (HoldingDetails holdingDetails: entry.getValue().getInvestorHoldingList()) {
                double newPayoutAmount = ((double)holdingDetails.getNumberOfUnits()/totalHoldingsAcrossShareClasses)
                        *
                        distributionAmount;
                this.rulePayout += newPayoutAmount;
                Payout newPayout = new Payout(holdingDetails.getShareClass(), newPayoutAmount);
                List<Payout> existingPayouts = payoutsByInvestor.get(investor);
                existingPayouts.add(newPayout);
                payoutsByInvestor.put(investor, existingPayouts);
            }
        }

        return payoutsByInvestor;
    }

    @Override
    public double geRulePayout() {
        return this.rulePayout;
    }
}
