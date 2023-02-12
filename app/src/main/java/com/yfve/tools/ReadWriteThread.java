package com.yfve.tools;

import android.util.Log;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/* loaded from: classes1.dex */
public class ReadWriteThread extends Thread {
    public final String tag = getClass().getSimpleName() + "_zyx";
    private boolean mbIsInputStreamRead = true;
    private Socket socket = null;
    private OutputStream outputStream = null;
    private OutputStreamWriter outputStreamWriter = null;
    private BufferedWriter bufferedWriter = null;
    private InputStream is = null;
    private InputStreamReader inputStreamReader = null;
    String mstrToSer = "";

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        Log.w(this.tag, "run()+++");
        try {
            this.socket = new Socket("localhost", 8000);
            this.outputStream = this.socket.getOutputStream();
            this.outputStreamWriter = new OutputStreamWriter(this.outputStream);
            this.bufferedWriter = new BufferedWriter(this.outputStreamWriter);
            this.is = this.socket.getInputStream();
            this.inputStreamReader = new InputStreamReader(this.is);
            while (this.mbIsInputStreamRead) {
                char[] cha = new char[512];
                int len = this.inputStreamReader.read(cha);
                String strFromMcu = new String(cha, 0, len);
                String str = this.tag;
                LU.w(str, "run() strFromMcu===" + strFromMcu);
                SocketUtils.getInstance().onServerResultUpdate(strFromMcu);
            }
            this.inputStreamReader.close();
            this.is.close();
            this.bufferedWriter.close();
            this.outputStreamWriter.close();
            this.socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            String str2 = this.tag;
            Log.e(str2, "run()xxx  Exception==" + e.toString());
            nullScoket();
        }
        LU.w(this.tag, "run()--------- ");
    }

    public void nullScoket() {
        try {
            if (this.outputStream != null) {
                this.outputStream.close();
                this.outputStream = null;
            }
            if (this.bufferedWriter != null) {
                this.bufferedWriter.close();
                this.bufferedWriter = null;
            }
            if (this.outputStreamWriter != null) {
                this.outputStreamWriter.close();
                this.outputStreamWriter = null;
            }
            if (this.inputStreamReader != null) {
                this.inputStreamReader.close();
                this.inputStreamReader = null;
            }
            if (this.is != null) {
                this.is.close();
                this.is = null;
            }
            if (this.socket != null) {
                this.socket.close();
                this.socket = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isMbIsStartRead() {
        return this.mbIsInputStreamRead;
    }

    public void setMbIsStartRead(boolean mbIsStartRead) {
        this.mbIsInputStreamRead = mbIsStartRead;
    }

    public void setValueToServer(String strToSer) {
        String str = this.tag;
        LU.w(str, "setValueToServer()  strToSer== " + strToSer);
        this.mstrToSer = strToSer;
        if (this.bufferedWriter != null) {
            new Thread(new Runnable() { // from class: com.yfve.tools.ReadWriteThread.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ReadWriteThread.this.bufferedWriter.write(ReadWriteThread.this.mstrToSer);
                        ReadWriteThread.this.bufferedWriter.flush();
                    } catch (Exception e) {
                        String str2 = ReadWriteThread.this.tag;
                        LU.w(str2, "setValueToServer()  Exception== " + e.toString());
                        e.printStackTrace();
                        ReadWriteThread.this.nullScoket();
                    }
                }
            }).start();
            return;
        }
        LU.e(this.tag, "setValueToServer()  bufferedWriter==null ");
        new Thread(new Runnable() { // from class: com.yfve.tools.ReadWriteThread.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(2000L);
                    ReadWriteThread.this.bufferedWriter.write(ReadWriteThread.this.mstrToSer);
                    ReadWriteThread.this.bufferedWriter.flush();
                } catch (Exception e) {
                    String str2 = ReadWriteThread.this.tag;
                    LU.w(str2, "setValueToServer()  Exception== " + e.toString());
                    e.printStackTrace();
                    ReadWriteThread.this.nullScoket();
                }
            }
        }).start();
    }
}
