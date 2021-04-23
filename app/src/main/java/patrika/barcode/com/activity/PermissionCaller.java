package patrika.barcode.com.activity;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

public class PermissionCaller {

    Activity context;

    private PermissionCaller(Activity context) {
        this.context = context;
    }

    public static PermissionCaller getInstance(Activity context) {
        PermissionCaller com = new PermissionCaller(context);
        return com;
    }

    public Boolean isCameraPermission(int fineReq) {
        boolean isFine = ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        if (isFine) {
            return true;
        } else {
            reqCameraPermission(fineReq);
            return false;
        }
    }

    public Boolean isAccessLocation(int fineReq) {
        boolean isFine = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        boolean isCoarse = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if (isFine && isCoarse) {
            return true;
        } else {
            reqAccessLocation(fineReq);
            return false;
        }
    }

    public Boolean isAccessReadWrite(int fineReq) {
        boolean isRead = ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        boolean isWrite = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        if (isRead && isWrite) {
            return true;
        }
        if (!isRead && !isWrite) {
            reqAccessReadWrite(fineReq);
            return false;
        } else {
            if (!isRead)
                reqAccessRead(fineReq);
            if (!isWrite)
                reqAccessWrite(fineReq);
        }
        return false;
    }

    public Boolean isAccessAudioRecord(int fineReq) {
        boolean isAudio = ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        boolean isAudioSet = ActivityCompat.checkSelfPermission(context, Manifest.permission.MODIFY_AUDIO_SETTINGS) == PackageManager.PERMISSION_GRANTED;

        if (isAudio && isAudioSet) {
            return true;
        }

        reqAccessAudio(fineReq);
        return false;
    }

    public Boolean isGetAccount(int fineReq) {
        boolean isFine = ActivityCompat.checkSelfPermission(context, Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED;

        if (isFine) {
            return true;
        } else {
            reqGetAccount(fineReq);
            return false;
        }
    }

    public Boolean isListOfPermission(String fineReq[], int reqAll) {


        boolean isOk = true;
        StringBuilder perNeed = new StringBuilder();
        for (String per : fineReq) {
            if (!(ActivityCompat.checkSelfPermission(context, per) == PackageManager.PERMISSION_GRANTED)) {
                if (isOk)
                    isOk = false;
                perNeed.append(per);
                perNeed.append(",");
            }
        }

        if (isOk) {
            return true;
        }

        String permissions = (perNeed.length() > 1 ? perNeed.substring(0, perNeed.length() - 1) : "");
        if(!permissions.isEmpty()){
            String arrPer[] = permissions.split(",");
            reqAllPermissions(arrPer, reqAll);
        }

        return false;
    }

    public void reqCameraPermission(int fineReq) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION}, fineReq);
    }

    public void reqAccessLocation(int fineReq) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, fineReq);
    }

    public void reqAccessReadWrite(int fineReq) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, fineReq);
    }

    public void reqAccessAudio(int fineReq) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.MODIFY_AUDIO_SETTINGS}, fineReq);
    }

    public void reqAccessRead(int fineReq) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, fineReq);
    }

    public void reqAccessWrite(int fineReq) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, fineReq);
    }

    public void reqGetAccount(int fineReq) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.GET_ACCOUNTS}, fineReq);
    }

    public void reqAllPermissions(String permissions[], int fineReq) {
        ActivityCompat.requestPermissions(context, permissions, fineReq);
    }
}
