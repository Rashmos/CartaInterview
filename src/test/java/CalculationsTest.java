import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import roles.Investor;
import shareclass.ShareClass;
import utils.InvestorUtils;
import utils.VCFirmUtils;

public class CalculationsTest {
    private Calculations KVenturesCalculations;

    @Before
    public void setUp() {
        this.KVenturesCalculations = new Calculations(VCFirmUtils.KVentures());
    }

    @Test
    public void testCalculatePayoutPerInvestor() {
        Map<Investor, Double> result = this.KVenturesCalculations
                .calculatePayoutPerInvestor(1000.0);
        assertThat(result.entrySet().size(), is(3));
        for (Map.Entry<Investor, Double> entry : result.entrySet()) {
            Investor investor = entry.getKey();
            Double payout = entry.getValue();
            if(investor.equals(InvestorUtils.investorAlex())) {
                assertThat(payout, is(392.86));
            }
            if(investor.equals(InvestorUtils.investorBecky())) {
                assertThat(payout, is(464.28));
            }
            if(investor.equals(InvestorUtils.investorDavid())) {
                assertThat(payout, is(142.85));
            }
        }
    }

    @Test
    public void testCalculatePayoutPerShareClass() {
        Map<ShareClass, Double> result = this.KVenturesCalculations
                .calculatePayoutPerShareClass(1000.0);
        for (Map.Entry<ShareClass, Double> entry : result.entrySet()) {
            ShareClass shareClass = entry.getKey();
            Double payout = entry.getValue();
            if(shareClass == ShareClass.CLASS_A) {
                assertThat(payout, is(142.86));
            }
            if(shareClass == ShareClass.CLASS_B) {
                assertThat(payout, is(785.72));
            }
            if(shareClass == ShareClass.CLASS_C) {
                assertThat(payout, is(71.43));
            }
        }
    }
}
