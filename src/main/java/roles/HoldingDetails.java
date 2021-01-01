package roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shareclass.ShareClass;

@AllArgsConstructor
public class HoldingDetails {
    @Getter
    int numberOfUnits;

    @Getter
    double amountInvested;

    @Getter
    ShareClass shareClass;
}
