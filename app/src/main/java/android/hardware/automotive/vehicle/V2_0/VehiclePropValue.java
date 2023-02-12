package android.hardware.automotive.vehicle.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import com.yfve.engineeringmode.R;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class VehiclePropValue {
    public int areaId;
    public int prop;
    public long timestamp;
    public final RawValue value = new RawValue();

    /* loaded from: classes2.dex */
    public static final class RawValue {
        public final ArrayList<Integer> int32Values = new ArrayList<>();
        public final ArrayList<Float> floatValues = new ArrayList<>();
        public final ArrayList<Long> int64Values = new ArrayList<>();
        public final ArrayList<Byte> bytes = new ArrayList<>();
        public String stringValue = new String();

        public final boolean equals(Object otherObject) {
            if (this == otherObject) {
                return true;
            }
            if (otherObject == null || otherObject.getClass() != RawValue.class) {
                return false;
            }
            RawValue other = (RawValue) otherObject;
            if (HidlSupport.deepEquals(this.int32Values, other.int32Values) && HidlSupport.deepEquals(this.floatValues, other.floatValues) && HidlSupport.deepEquals(this.int64Values, other.int64Values) && HidlSupport.deepEquals(this.bytes, other.bytes) && HidlSupport.deepEquals(this.stringValue, other.stringValue)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.int32Values)), Integer.valueOf(HidlSupport.deepHashCode(this.floatValues)), Integer.valueOf(HidlSupport.deepHashCode(this.int64Values)), Integer.valueOf(HidlSupport.deepHashCode(this.bytes)), Integer.valueOf(HidlSupport.deepHashCode(this.stringValue)));
        }

        public final String toString() {
            return "{.int32Values = " + this.int32Values + ", .floatValues = " + this.floatValues + ", .int64Values = " + this.int64Values + ", .bytes = " + this.bytes + ", .stringValue = " + this.stringValue + "}";
        }

        public final void readFromParcel(HwParcel parcel) {
            HwBlob blob = parcel.readBuffer(80L);
            readEmbeddedFromParcel(parcel, blob, 0L);
        }

        public static final ArrayList<RawValue> readVectorFromParcel(HwParcel parcel) {
            ArrayList<RawValue> _hidl_vec = new ArrayList<>();
            HwBlob _hidl_blob = parcel.readBuffer(16L);
            int _hidl_vec_size = _hidl_blob.getInt32(8L);
            HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 80, _hidl_blob.handle(), 0L, true);
            _hidl_vec.clear();
            for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                RawValue _hidl_vec_element = new RawValue();
                _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 80);
                _hidl_vec.add(_hidl_vec_element);
            }
            return _hidl_vec;
        }

        public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
            int _hidl_vec_size = _hidl_blob.getInt32(_hidl_offset + 0 + 8);
            HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 4, _hidl_blob.handle(), _hidl_offset + 0 + 0, true);
            this.int32Values.clear();
            int _hidl_index_0 = 0;
            for (int _hidl_index_02 = 0; _hidl_index_02 < _hidl_vec_size; _hidl_index_02++) {
                int _hidl_vec_element = childBlob.getInt32(_hidl_index_02 * 4);
                this.int32Values.add(Integer.valueOf(_hidl_vec_element));
            }
            int _hidl_vec_size2 = _hidl_blob.getInt32(_hidl_offset + 16 + 8);
            HwBlob childBlob2 = parcel.readEmbeddedBuffer(_hidl_vec_size2 * 4, _hidl_blob.handle(), _hidl_offset + 16 + 0, true);
            this.floatValues.clear();
            for (int _hidl_index_03 = 0; _hidl_index_03 < _hidl_vec_size2; _hidl_index_03++) {
                float _hidl_vec_element2 = childBlob2.getFloat(_hidl_index_03 * 4);
                this.floatValues.add(Float.valueOf(_hidl_vec_element2));
            }
            int _hidl_vec_size3 = _hidl_blob.getInt32(_hidl_offset + 32 + 8);
            HwBlob childBlob3 = parcel.readEmbeddedBuffer(_hidl_vec_size3 * 8, _hidl_blob.handle(), _hidl_offset + 32 + 0, true);
            this.int64Values.clear();
            for (int _hidl_index_04 = 0; _hidl_index_04 < _hidl_vec_size3; _hidl_index_04++) {
                long _hidl_vec_element3 = childBlob3.getInt64(_hidl_index_04 * 8);
                this.int64Values.add(Long.valueOf(_hidl_vec_element3));
            }
            int _hidl_vec_size4 = _hidl_blob.getInt32(_hidl_offset + 48 + 8);
            HwBlob childBlob4 = parcel.readEmbeddedBuffer(_hidl_vec_size4 * 1, _hidl_blob.handle(), _hidl_offset + 48 + 0, true);
            this.bytes.clear();
            while (true) {
                int _hidl_index_05 = _hidl_index_0;
                if (_hidl_index_05 >= _hidl_vec_size4) {
                    this.stringValue = _hidl_blob.getString(_hidl_offset + 64);
                    parcel.readEmbeddedBuffer(this.stringValue.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 64 + 0, false);
                    return;
                }
                byte _hidl_vec_element4 = childBlob4.getInt8(_hidl_index_05 * 1);
                this.bytes.add(Byte.valueOf(_hidl_vec_element4));
                _hidl_index_0 = _hidl_index_05 + 1;
            }
        }

        public final void writeToParcel(HwParcel parcel) {
            HwBlob _hidl_blob = new HwBlob(80);
            writeEmbeddedToBlob(_hidl_blob, 0L);
            parcel.writeBuffer(_hidl_blob);
        }

        public static final void writeVectorToParcel(HwParcel parcel, ArrayList<RawValue> _hidl_vec) {
            HwBlob _hidl_blob = new HwBlob(16);
            int _hidl_vec_size = _hidl_vec.size();
            _hidl_blob.putInt32(8L, _hidl_vec_size);
            _hidl_blob.putBool(12L, false);
            HwBlob childBlob = new HwBlob(_hidl_vec_size * 80);
            for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 80);
            }
            _hidl_blob.putBlob(0L, childBlob);
            parcel.writeBuffer(_hidl_blob);
        }

        public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
            int _hidl_vec_size = this.int32Values.size();
            _hidl_blob.putInt32(_hidl_offset + 0 + 8, _hidl_vec_size);
            _hidl_blob.putBool(_hidl_offset + 0 + 12, false);
            HwBlob childBlob = new HwBlob(_hidl_vec_size * 4);
            for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                childBlob.putInt32(_hidl_index_0 * 4, this.int32Values.get(_hidl_index_0).intValue());
            }
            _hidl_blob.putBlob(_hidl_offset + 0 + 0, childBlob);
            int _hidl_vec_size2 = this.floatValues.size();
            _hidl_blob.putInt32(_hidl_offset + 16 + 8, _hidl_vec_size2);
            _hidl_blob.putBool(_hidl_offset + 16 + 12, false);
            HwBlob childBlob2 = new HwBlob(_hidl_vec_size2 * 4);
            for (int _hidl_index_02 = 0; _hidl_index_02 < _hidl_vec_size2; _hidl_index_02++) {
                childBlob2.putFloat(_hidl_index_02 * 4, this.floatValues.get(_hidl_index_02).floatValue());
            }
            _hidl_blob.putBlob(_hidl_offset + 16 + 0, childBlob2);
            int _hidl_vec_size3 = this.int64Values.size();
            _hidl_blob.putInt32(_hidl_offset + 32 + 8, _hidl_vec_size3);
            _hidl_blob.putBool(_hidl_offset + 32 + 12, false);
            HwBlob childBlob3 = new HwBlob(_hidl_vec_size3 * 8);
            for (int _hidl_index_03 = 0; _hidl_index_03 < _hidl_vec_size3; _hidl_index_03++) {
                childBlob3.putInt64(_hidl_index_03 * 8, this.int64Values.get(_hidl_index_03).longValue());
            }
            _hidl_blob.putBlob(_hidl_offset + 32 + 0, childBlob3);
            int _hidl_vec_size4 = this.bytes.size();
            _hidl_blob.putInt32(_hidl_offset + 48 + 8, _hidl_vec_size4);
            int _hidl_index_04 = 0;
            _hidl_blob.putBool(_hidl_offset + 48 + 12, false);
            HwBlob childBlob4 = new HwBlob(_hidl_vec_size4 * 1);
            while (true) {
                int _hidl_index_05 = _hidl_index_04;
                if (_hidl_index_05 < _hidl_vec_size4) {
                    childBlob4.putInt8(_hidl_index_05 * 1, this.bytes.get(_hidl_index_05).byteValue());
                    _hidl_index_04 = _hidl_index_05 + 1;
                } else {
                    _hidl_blob.putBlob(_hidl_offset + 48 + 0, childBlob4);
                    _hidl_blob.putString(_hidl_offset + 64, this.stringValue);
                    return;
                }
            }
        }
    }

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != VehiclePropValue.class) {
            return false;
        }
        VehiclePropValue other = (VehiclePropValue) otherObject;
        if (this.prop == other.prop && this.timestamp == other.timestamp && this.areaId == other.areaId && HidlSupport.deepEquals(this.value, other.value)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.prop))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.timestamp))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.areaId))), Integer.valueOf(HidlSupport.deepHashCode(this.value)));
    }

    public final String toString() {
        return "{.prop = " + this.prop + ", .timestamp = " + this.timestamp + ", .areaId = " + this.areaId + ", .value = " + this.value + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(104L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<VehiclePropValue> readVectorFromParcel(HwParcel parcel) {
        ArrayList<VehiclePropValue> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * R.styleable.AppCompatTheme_textColorSearchUrl, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            VehiclePropValue _hidl_vec_element = new VehiclePropValue();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * R.styleable.AppCompatTheme_textColorSearchUrl);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.prop = _hidl_blob.getInt32(0 + _hidl_offset);
        this.timestamp = _hidl_blob.getInt64(8 + _hidl_offset);
        this.areaId = _hidl_blob.getInt32(16 + _hidl_offset);
        this.value.readEmbeddedFromParcel(parcel, _hidl_blob, 24 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob((int) R.styleable.AppCompatTheme_textColorSearchUrl);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<VehiclePropValue> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * R.styleable.AppCompatTheme_textColorSearchUrl);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * R.styleable.AppCompatTheme_textColorSearchUrl);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.prop);
        _hidl_blob.putInt64(8 + _hidl_offset, this.timestamp);
        _hidl_blob.putInt32(16 + _hidl_offset, this.areaId);
        this.value.writeEmbeddedToBlob(_hidl_blob, 24 + _hidl_offset);
    }
}
