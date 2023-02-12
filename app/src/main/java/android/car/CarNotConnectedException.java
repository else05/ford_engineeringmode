package android.car;

/* loaded from: classes2.dex */
public class CarNotConnectedException extends Exception {
    private static final long serialVersionUID = -5629175439268813047L;

    public CarNotConnectedException() {
    }

    public CarNotConnectedException(String name) {
        super(name);
    }

    public CarNotConnectedException(String name, Throwable cause) {
        super(name, cause);
    }

    public CarNotConnectedException(Exception cause) {
        super(cause);
    }
}
