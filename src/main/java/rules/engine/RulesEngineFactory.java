package rules.engine;

public class RulesEngineFactory {
    public RulesEngine getRulesEngine(String firmName) {
        if(firmName == "Krakatoa Ventures"){
            return new KrakatoaVenturesRuleEngine();
        }
        throw new RuntimeException("No Rules Engine found for vs firm: " + firmName);
    }
}
