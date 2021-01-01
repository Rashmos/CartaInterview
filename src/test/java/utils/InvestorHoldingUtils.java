package utils;

import roles.HoldingDetails;
import roles.InvestorHoldings;
import shareclass.ShareClass;

public class InvestorHoldingUtils {
    public static InvestorHoldings beckyHoldings() {
        HoldingDetails one = new HoldingDetails(10, 250.0, ShareClass.CLASS_B);
        HoldingDetails two = new HoldingDetails(5, 0.0, ShareClass.CLASS_C);

        InvestorHoldings holdings = new InvestorHoldings();
        holdings.getInvestorHoldingList().add(one);
        holdings.getInvestorHoldingList().add(two);

        return holdings;
    }

    public static InvestorHoldings alexHoldings() {
        HoldingDetails one = new HoldingDetails(10, 250.0, ShareClass.CLASS_B);

        InvestorHoldings holdings = new InvestorHoldings();
        holdings.getInvestorHoldingList().add(one);

        return holdings;
    }

    public static InvestorHoldings davidHoldings() {
        HoldingDetails one = new HoldingDetails(10, 0.0, ShareClass.CLASS_A);

        InvestorHoldings holdings = new InvestorHoldings();
        holdings.getInvestorHoldingList().add(one);

        return holdings;
    }

}
