package utils;

import firm.VCFirm;

public class VCFirmUtils {
    public static VCFirm KVentures() {
        VCFirm firm = new VCFirm("Krakatoa Ventures");
        firm.addInvestorHoldings(InvestorUtils.investorBecky(), InvestorHoldingUtils.beckyHoldings());
        firm.addInvestorHoldings(InvestorUtils.investorAlex(), InvestorHoldingUtils.alexHoldings());
        firm.addInvestorHoldings(InvestorUtils.investorDavid(), InvestorHoldingUtils.davidHoldings());

        return firm;
    }

}
