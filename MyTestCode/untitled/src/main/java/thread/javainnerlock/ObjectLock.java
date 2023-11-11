package thread.javainnerlock;

import org.apache.commons.compress.utils.ByteUtils;

public class ObjectLock {

    private Integer amount = 0;
    public void increase() {
        synchronized (this) {
            amount++;
        }
    }
    public String hexHash() {
        int hashCode = this.hashCode();
        byte[] hashcode_LE = new byte[64];

        // not the same as JUC page 127 ByteUtil class, TODO: check which class it is in book
        ByteUtils.toLittleEndian(hashcode_LE, hashCode, 0, 64);
        return null;
    }


}
