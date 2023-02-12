package android.car.hardware;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class BackupIgnitionRspObject {
    private byte VariableData;
    private byte signalId = 0;
    private byte Utilization = 0;
    private byte Ces = 0;
    private byte CharacterCoding = 0;
    private byte RspCode = 0;
    private byte RspStatus = 0;
    private byte[] ValetPassword = new byte[4];
    private byte[] ChallengeNonce = new byte[32];
    private byte[] Salt1 = new byte[16];
    private byte[] Salt2 = new byte[16];
    private byte numberOfItems = 0;
    private ArrayList<String> vector = new ArrayList<>();

    public void setSignalId(byte signalId) {
        this.signalId = signalId;
    }

    public void setUtilization(byte utilization) {
        this.Utilization = utilization;
    }

    public void setCes(byte ces) {
        this.Ces = ces;
    }

    public void setCharacterCoding(byte characterCoding) {
        this.CharacterCoding = characterCoding;
    }

    public void setRspCode(byte rspCode) {
        this.RspCode = rspCode;
    }

    public void setRspStatus(byte rspStatus) {
        this.RspStatus = rspStatus;
    }

    public void setValetPassword(byte[] backupIgnition_Rsp) {
        if (backupIgnition_Rsp.length > 9) {
            for (int i = 0; i < 4; i++) {
                this.ValetPassword[i] = backupIgnition_Rsp[i + 6];
            }
        }
    }

    public void setChallengeNonce(byte[] backupIgnition_Rsp) {
        if (backupIgnition_Rsp.length > 38) {
            for (int i = 0; i < 32; i++) {
                this.ChallengeNonce[i] = backupIgnition_Rsp[i + 6];
            }
        }
    }

    public void setSalt1(byte[] backupIgnition_Rsp) {
        if (backupIgnition_Rsp.length >= 54) {
            for (int i = 0; i < 16; i++) {
                this.Salt1[i] = backupIgnition_Rsp[i + 38];
            }
        }
    }

    public void setSalt2(byte[] backupIgnition_Rsp) {
        if (backupIgnition_Rsp.length > 22) {
            for (int i = 0; i < 16; i++) {
                this.Salt2[i] = backupIgnition_Rsp[i + 6];
            }
        }
    }

    public void setNumberOfItems(byte numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public void setPhoneVector(ArrayList<String> vector) {
        this.vector = vector;
    }

    public void setVariableData(byte variableData) {
        this.VariableData = variableData;
    }

    public byte getVariableData() {
        return this.VariableData;
    }

    public byte getSignalId() {
        return this.signalId;
    }

    public byte getUtilization() {
        return this.Utilization;
    }

    public byte getCes() {
        return this.Ces;
    }

    public byte getCharacterCoding() {
        return this.CharacterCoding;
    }

    public byte getRspCode() {
        return this.RspCode;
    }

    public byte getRspStatus() {
        return this.RspStatus;
    }

    public byte[] getValetPassword() {
        return this.ValetPassword;
    }

    public byte[] getChallengeNonce() {
        return this.ChallengeNonce;
    }

    public byte[] getSalt1() {
        return this.Salt1;
    }

    public byte[] getSalt2() {
        return this.Salt2;
    }

    public byte getNumberOfItems() {
        return this.numberOfItems;
    }

    public List<String> getPhoneVector() {
        return this.vector;
    }

    public String printString() {
        StringBuilder builder = new StringBuilder();
        builder.append("signalID: " + Integer.toHexString(this.signalId));
        builder.append(" Utilization: " + Integer.toHexString(this.Utilization));
        builder.append(" CES: " + Integer.toHexString(this.Ces));
        builder.append(" CharacterCoding: " + Integer.toHexString(this.CharacterCoding));
        builder.append(" Rspcode: " + Integer.toHexString(this.RspCode));
        builder.append(" RspStatus: " + Integer.toHexString(this.RspStatus));
        int i = 0;
        if ((this.RspCode == 5) | (this.RspCode == 10)) {
            builder.append(" ValetPassword: ");
            while (true) {
                int i2 = i;
                if (i2 >= this.ValetPassword.length) {
                    break;
                }
                builder.append(Integer.toHexString(this.ValetPassword[i2]));
                i = i2 + 1;
            }
        } else if (this.RspCode == 1) {
            builder.append(" ChallengeNonce: ");
            for (int i3 = 0; i3 < this.ChallengeNonce.length; i3++) {
                builder.append(Integer.toHexString(this.ChallengeNonce[i3]));
            }
            builder.append(" Salt: ");
            while (true) {
                int i4 = i;
                if (i4 >= this.Salt1.length) {
                    break;
                }
                builder.append(Integer.toHexString(this.Salt1[i4]));
                i = i4 + 1;
            }
        } else if ((this.RspCode == 3) | (this.RspCode == 4)) {
            builder.append(" Salt: ");
            for (int i5 = 0; i5 < this.Salt2.length; i5++) {
                builder.append(Integer.toHexString(this.Salt2[i5]));
            }
            builder.append(" NumberOfItems: ");
            builder.append(Integer.toHexString(this.numberOfItems));
            if (this.vector.size() > 0) {
                while (true) {
                    int i6 = i;
                    if (i6 >= this.vector.size()) {
                        break;
                    }
                    builder.append(this.vector.get(i6));
                    i = i6 + 1;
                }
            }
        } else {
            builder.append(" elseVariableData: ");
            builder.append(Integer.toHexString(this.VariableData));
        }
        return builder.toString();
    }
}
