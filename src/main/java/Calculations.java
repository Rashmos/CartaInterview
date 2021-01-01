import firm.VCFirm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import roles.Investor;
import rules.engine.Payout;
import rules.engine.RulesEngine;
import rules.engine.RulesEngineFactory;
import shareclass.ShareClass;

public class Calculations {
    private VCFirm firm;
    RulesEngine rulesEngine;

    public Calculations(VCFirm firm){
        this.firm = firm;
        this.rulesEngine = new RulesEngineFactory().getRulesEngine(firm.getName());

    }

    public Map<Investor, Double> calculatePayoutPerInvestor(double distributionAmount) {
        Map<Investor, Double> result = new HashMap<>();
        Map<Investor, List<Payout>> engineOutput = this.rulesEngine.execute(distributionAmount,
                this.firm.getInvestorHoldingsMap());
        for (Map.Entry<Investor, List<Payout>> entry : engineOutput.entrySet()) {
            Investor investor = entry.getKey();
            double totalAmount = this.round(entry.getValue().stream().mapToDouble(Payout::getAmount).sum());
            result.put(investor, totalAmount);
        }

        return result;
    }

    public Map<ShareClass, Double> calculatePayoutPerShareClass(double distributionAmount) {
        Map<ShareClass, Double> result = new HashMap<>();
        Map<Investor, List<Payout>> engineOutput = this.rulesEngine.execute(distributionAmount,
                this.firm.getInvestorHoldingsMap());
        for (Map.Entry<Investor, List<Payout>> entry : engineOutput.entrySet()) {
            Investor investor = entry.getKey();
            List<Payout> payouts = entry.getValue();
            for (Payout payout: payouts) {
                if(result.containsKey(payout.getShareClass())) {
                    double oldValue = result.get(payout.getShareClass());
                    double newValue = this.round(oldValue + payout.getAmount());
                    result.put(payout.getShareClass(), newValue);
                } else {
                    result.put(payout.getShareClass(), payout.getAmount());
                }
            }
        }

        return result;
    }

    public double round(double value){
        value = value*100;
        value = Math.round(value);
        return value /100;
    }
}
