package by.oxagile.guardian.managers;

public enum Wait {
    FOR_INCOMING_CALL(20),
    FOR_SESSION_CONNECTION(5),
    FOR_CALL_SET_UP(7),
    FOR_CALL_TO_TIMEOUT(70),
    FOR_UBER_PAGE(10),
    FOR_ELEMENT_PRESENT(10);

    private final int value;

    Wait(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
