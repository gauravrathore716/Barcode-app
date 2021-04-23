package patrika.barcode.com.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import patrika.barcode.com.R;
import patrika.barcode.com.model.DepthList;


public abstract class BaseActivity extends AppCompatActivity {

//    SpotsDialog mProgressDialog;
    //AlertDialog  mProgressDialog;

    public static final int REQUEST_CAPTURE = 1001;
    public static final int REQUEST_GALLERY = 1002;
    public static final int REQUEST_VIDEO = 1003;

    public Uri captureMediaFile = null;

    ProgressDialog pDialog;
    BaseActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /* Check whether this uri is a content uri or not.
     *  content uri like content://media/external/images/media/1302716
     *  */
    private boolean isContentUri(Uri uri) {
        boolean ret = false;
        if (uri != null) {
            String uriSchema = uri.getScheme();
            if ("content".equalsIgnoreCase(uriSchema)) {
                ret = true;
            }
        }
        return ret;
    }

    /* Check whether this uri is a file uri or not.
     *  file uri like file:///storage/41B7-12F1/DCIM/Camera/IMG_20180211_095139.jpg
     * */
    private boolean isFileUri(Uri uri) {
        boolean ret = false;
        if (uri != null) {
            String uriSchema = uri.getScheme();
            if ("file".equalsIgnoreCase(uriSchema)) {
                ret = true;
            }
        }
        return ret;
    }


    /* Check whether this document is provided by ExternalStorageProvider. */
    private boolean isExternalStoreDoc(String uriAuthority) {
        boolean ret = false;

        if ("com.android.externalstorage.documents".equals(uriAuthority)) {
            ret = true;
        }

        return ret;
    }

    /* Check whether this document is provided by DownloadsProvider. */
    private boolean isDownloadDoc(String uriAuthority) {
        boolean ret = false;

        if ("com.android.providers.downloads.documents".equals(uriAuthority)) {
            ret = true;
        }

        return ret;
    }

    /* Check whether this document is provided by MediaProvider. */
    private boolean isMediaDoc(String uriAuthority) {
        boolean ret = false;

        if ("com.android.providers.media.documents".equals(uriAuthority)) {
            ret = true;
        }

        return ret;
    }

    /* Check whether this document is provided by google photos. */
    private boolean isGooglePhotoDoc(String uriAuthority) {
        boolean ret = false;

        if ("com.google.android.apps.photos.content".equals(uriAuthority)) {
            ret = true;
        }

        return ret;
    }

    /* Return uri represented document file real local path.*/
    private String getImageRealPath(ContentResolver contentResolver, Uri uri, String whereClause) {
        String ret = "";

        // Query the uri with condition.
        Cursor cursor = contentResolver.query(uri, null, whereClause, null, null);

        if (cursor != null) {
            boolean moveToFirst = cursor.moveToFirst();
            if (moveToFirst) {

                // Get columns name by uri type.
                String columnName = MediaStore.Images.Media.DATA;

                if (uri == MediaStore.Images.Media.EXTERNAL_CONTENT_URI) {
                    columnName = MediaStore.Images.Media.DATA;
                } else if (uri == MediaStore.Audio.Media.EXTERNAL_CONTENT_URI) {
                    columnName = MediaStore.Audio.Media.DATA;
                } else if (uri == MediaStore.Video.Media.EXTERNAL_CONTENT_URI) {
                    columnName = MediaStore.Video.Media.DATA;
                }

                // Get column index.
                int imageColumnIndex = cursor.getColumnIndex(columnName);

                // Get column value which is the uri related file local path.
                ret = cursor.getString(imageColumnIndex);
            }
        }

        return ret;
    }


    /**
     * Create a File for saving an image
     */
//    private File getOutputMediaFile(int type) {
//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//
//                Environment.DIRECTORY_PICTURES), AppConstants.SAVE_IMAGE_DIRECTORY);
//        /**Create the storage directory if it does not exist*/
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                return null;
//            }
//        }
//
//        /**Create a media file name*/
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        File mediaFile;
//        if (type == 1) {
//            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
//                    "IMG_" + timeStamp + ".png");
//        } else {
//            return null;
//        }
//
//        return mediaFile;
//    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "BookComplaint", null);

        return Uri.parse(path);

    }

    public String getRealPathFromURI(Uri uri) {

        String mCurrentPhotoPath = null;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);

            mCurrentPhotoPath = cursor.getString(idx);
            cursor.close();
        }

        return mCurrentPhotoPath;

    }


    public String tag() {
        return getClass().getSimpleName();
    }

    public void log(String message) {
        Log.d(tag(), message);
    }

    public void loadProgressBar(Context context, String message, boolean cancellable) {

        if (pDialog == null) {
            pDialog = new ProgressDialog(this);
            pDialog.setMessage(message);
            pDialog.setCancelable(false);
            //            pDialog.setIndeterminate(true);
        }
        if (!pDialog.isShowing())
            pDialog.show();

    }

    public void dismissProgressBar(Context context) {

        if (pDialog != null) {
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            pDialog = null;
        }
    }

    public AlertDialog.Builder getAlertDialogBuilder(String title, String message, boolean cancellable) {
        return new AlertDialog.Builder(this, R.style.AppTheme_AlertDialog)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancellable);
    }

    public void enableLoadingBar(Context context, boolean enable, String s) {
        if (enable) {
            loadProgressBar(context, s, false);
        } else {
            dismissProgressBar(context);
        }
    }

    public void onError(String reason) {
        onError(reason, false);
    }

    public void onError(String reason, final boolean finishOnOk) {
        if (reason != null && !reason.isEmpty()) {
            getAlertDialogBuilder(null, reason, false)
                    .setPositiveButton(getString(R.string.btn_ok), finishOnOk ? new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    } : null).show();
        } else {
            getAlertDialogBuilder(null, getString(R.string.default_error), false)
                    .setPositiveButton(getString(R.string.btn_ok), finishOnOk ? new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    } : null).show();
        }
    }


}
