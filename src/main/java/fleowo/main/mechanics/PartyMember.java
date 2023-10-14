package fleowo.main.mechanics;

import java.util.HashMap;
import java.util.Objects;

import fleowo.main.mechanics.party_value.PartyMemberValueManager;
import fleowo.main.mechanics.party_value.PartyValue;
import fleowo.main.mechanics.party_value.PartyValueException;
import org.bukkit.OfflinePlayer;

public class PartyMember {
    private OfflinePlayer offlinePlayer;

    private HashMap<String, PartyValue> map;

    public PartyMember(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
        this.map = new HashMap<>(PartyMemberValueManager.getInstance().getValues());
    }

    public OfflinePlayer getOfflinePlayer() {
        return this.offlinePlayer;
    }

    public <T> void setValue(String key, T value) throws PartyValueException {
        try {
            PartyValue<T> value1 = this.map.get(key);
            value1.setValue(value);
        } catch (Exception e) {
            throw new PartyValueException("Error setting value for key : " + key);
        }
    }

    public <T> PartyValue<T> getValue(String key) {
        return this.map.get(key);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PartyMember that = (PartyMember)o;
        return this.offlinePlayer.getUniqueId().equals(that.offlinePlayer.getUniqueId());
    }

    public int hashCode() {
        return Objects.hash(new Object[] { this.offlinePlayer.getUniqueId() });
    }
}
