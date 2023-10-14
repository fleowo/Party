package fleowo.main.mechanics.party_value;

import java.util.HashMap;

public class PartyMemberValueManager {
    private static PartyMemberValueManager instance = new PartyMemberValueManager();

    private HashMap<String, PartyValue> values;

    public static PartyMemberValueManager getInstance() {
        return instance;
    }

    private PartyMemberValueManager() {
        this.values = new HashMap<>();
    }

    public HashMap<String, PartyValue> getValues() {
        return this.values;
    }

    public void registerNewValue(String valueName, PartyValue value) throws PartyValueException {
        if (this.values.containsKey(valueName))
            throw new PartyValueException("Already registered value :" + valueName);
        this.values.put(valueName, value);
    }
}
