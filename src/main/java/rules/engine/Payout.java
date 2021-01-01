package rules.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shareclass.ShareClass;

@AllArgsConstructor
public class Payout {
    @Getter
    private ShareClass shareClass;

    @Getter
    private double amount;
}
