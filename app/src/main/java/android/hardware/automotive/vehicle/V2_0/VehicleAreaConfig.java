package android.hardware.automotive.vehicle.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class VehicleAreaConfig {
    public int areaId;
    public float maxFloatValue;
    public int maxInt32Value;
    public long maxInt64Value;
    public float minFloatValue;
    public int minInt32Value;
    public long minInt64Value;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != VehicleAreaConfig.class) {
            return false;
        }
        VehicleAreaConfig other = (VehicleAreaConfig) otherObject;
        if (this.areaId == other.areaId && this.minInt32Value == other.minInt32Value && this.maxInt32Value == other.maxInt32Value && this.minInt64Value == other.minInt64Value && this.maxInt64Value == other.maxInt64Value && this.minFloatValue == other.minFloatValue && this.maxFloatValue == other.maxFloatValue) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.areaId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.minInt32Value))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxInt32Value))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.minInt64Value))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.maxInt64Value))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.minFloatValue))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.maxFloatValue))));
    }

    public final String toString() {
        return "{.areaId = " + this.areaId + ", .minInt32Value = " + this.minInt32Value + ", .maxInt32Value = " + this.maxInt32Value + ", .minInt64Value = " + this.minInt64Value + ", .maxInt64Value = " + this.maxInt64Value + ", .minFloatValue = " + this.minFloatValue + ", .maxFloatValue = " + this.maxFloatValue + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(40L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<VehicleAreaConfig> readVectorFromParcel(HwParcel parcel) {
        ArrayList<VehicleAreaConfig> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 40, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            VehicleAreaConfig _hidl_vec_element = new VehicleAreaConfig();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 40);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.areaId = _hidl_blob.getInt32(0 + _hidl_offset);
        this.minInt32Value = _hidl_blob.getInt32(4 + _hidl_offset);
        this.maxInt32Value = _hidl_blob.getInt32(8 + _hidl_offset);
        this.minInt64Value = _hidl_blob.getInt64(16 + _hidl_offset);
        this.maxInt64Value = _hidl_blob.getInt64(24 + _hidl_offset);
        this.minFloatValue = _hidl_blob.getFloat(32 + _hidl_offset);
        this.maxFloatValue = _hidl_blob.getFloat(36 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(40);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<VehicleAreaConfig> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 40);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 40);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.areaId);
        _hidl_blob.putInt32(4 + _hidl_offset, this.minInt32Value);
        _hidl_blob.putInt32(8 + _hidl_offset, this.maxInt32Value);
        _hidl_blob.putInt64(16 + _hidl_offset, this.minInt64Value);
        _hidl_blob.putInt64(24 + _hidl_offset, this.maxInt64Value);
        _hidl_blob.putFloat(32 + _hidl_offset, this.minFloatValue);
        _hidl_blob.putFloat(36 + _hidl_offset, this.maxFloatValue);
    }
}
