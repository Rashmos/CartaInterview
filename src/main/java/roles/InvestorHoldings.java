package roles;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class InvestorHoldings {
    @Getter
    private List<HoldingDetails> investorHoldingList;

    public InvestorHoldings() {
        this.investorHoldingList = new ArrayList<>();
    }
}
