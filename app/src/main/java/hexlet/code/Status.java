package hexlet.code;

public final class Status {
    public static final String ADDED = "added";
    public static final String REMOVED = "removed";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private final String name;
    private final Object value;
    private Object newValue;

    Status(String statusName, Object value, Object newValue) {
        this.name = statusName;
        this.value = value;
        this.newValue = newValue;
    }

    Status(String statusName, Object value) {
        this.name = statusName;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

    public Object getNewValue() {
        return this.newValue;
    }
}
