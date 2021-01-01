package rules.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import roles.HoldingDetails;
import roles.Investor;
import roles.InvestorHoldings;
import rules.engine.Payout;
import rules.engine.RuleLogic;
import shareclass.ShareClass;

public class ProportionalPayoutByShareClassRulesEngine implements RuleLogic {

    private double rulePayout;

    @Override
    public Map<Investor, List<Payout>> apply(double distributionAmount, Map<Investor,InvestorHoldings> investorHoldingsMap) {
        if(distributionAmount == 0.0) {
            return Collections.emptyMap();
        }

        Map<Investor, List<Payout>> payoutsByInvestor = new HashMap<>();
        Map<Investor, HoldingDetails> investorsWithClassBHoldings = new HashMap<>();
        int totalClassBHoldings = 0;
        double maxAmountToPay = 0.0;

        for (Map.Entry<Investor,InvestorHoldings> entry : investorHoldingsMap.entrySet()) {
            Investor investor = entry.getKey();
            Optional<HoldingDetails> classBHoldings = entry.getValue().getInvestorHoldingList()
                    .stream()
                    .filter(holding -> holding.getShareClass() == ShareClass.CLASS_B)
                    .findAny();
            if(classBHoldings.isPresent()) {
                totalClassBHoldings += classBHoldings.get().getNumberOfUnits();
                Payout dummyPayout = new Payout(ShareClass.CLASS_B, 0.0);
                List<Payout> payoutList = new ArrayList<>();
                payoutList.add(dummyPayout);
                payoutsByInvestor.put(investor, payoutList);
                maxAmountToPay += classBHoldings.get().getAmountInvested();
                investorsWithClassBHoldings.put(investor,classBHoldings.get());
            }
        }

        while(maxAmountToPay != 0) {
            for (Map.Entry<Investor,HoldingDetails> entry : investorsWithClassBHoldings.entrySet()) {
                Investor investor = entry.getKey();
                HoldingDetails holdingDetails = entry.getValue();
                double proportion = (double) holdingDetails.getNumberOfUnits()/totalClassBHoldings;
                double payoutThusFar = payoutsByInvestor.get(investor).stream()
                        .mapToDouble(Payout::getAmount)
                        .sum();
                if( !( payoutThusFar == holdingDetails.getAmountInvested()) ) {
                    double newPayoutAmount = Math
                            .min(holdingDetails.getAmountInvested(), distributionAmount*proportion);
                    this.rulePayout += newPayoutAmount;
                    distributionAmount -= newPayoutAmount;
                    maxAmountToPay -= newPayoutAmount;
                    Payout newPayout = new Payout(ShareClass.CLASS_B,
                            newPayoutAmount + payoutThusFar);
                    List<Payout> existingPayouts = payoutsByInvestor.get(investor);
                    existingPayouts.add(newPayout);
                    payoutsByInvestor.put(investor, existingPayouts);
                }
            }
        }

        return payoutsByInvestor;
    }

    @Override
    public double geRulePayout() {
        return this.rulePayout;
    }
}
