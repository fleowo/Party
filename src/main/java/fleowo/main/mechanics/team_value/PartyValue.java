package fleowo.main.mechanics.party_value;

public abstract class PartyValue<T> {
    private T value;

    private boolean visible;

    public PartyValue(T defaultValue) {
        this.value = defaultValue;
        this.visible = true;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public final String getText() {
        return getTextToShow(this.value);
    }

    public abstract String getTextToShow(T paramT);
}
