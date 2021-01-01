package firm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import roles.Investor;
import roles.InvestorHoldings;

public class VCFirm {
    @Getter
    private UUID companyId;

    @Getter
    private String name;

    @Getter
    private Map<Investor, InvestorHoldings> investorHoldingsMap;

    public VCFirm(String name) {
        this.name = name;
        this.investorHoldingsMap = new HashMap<>();
    }

    public void addInvestorHoldings(Investor investor, InvestorHoldings newHoldings) {
        if(investorHoldingsMap.containsKey(investor)) {
            InvestorHoldings holdings = investorHoldingsMap.get(investor);
            holdings.getInvestorHoldingList().addAll(newHoldings.getInvestorHoldingList());
        } else {
            investorHoldingsMap.put(investor, newHoldings);
        }
    }

}
