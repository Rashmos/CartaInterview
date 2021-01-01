package roles;

import java.util.UUID;
import lombok.Getter;

public class Investor {
    @Getter
    private UUID investorId;

    @Getter
    private String name;

    public Investor(String name) {
        this.investorId = UUID.randomUUID();
        this.name = name;
    }
}
