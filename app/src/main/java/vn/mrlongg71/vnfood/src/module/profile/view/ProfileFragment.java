package vn.mrlongg71.vnfood.src.module.profile.view;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.model.User;
import vn.mrlongg71.vnfood.src.module.profile.presenter.PresenterProfile;
import vn.mrlongg71.vnfood.src.utils.DialogLoading;
import vn.mrlongg71.vnfood.src.utils.SplashScreenActivity;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment implements IViewProfile, View.OnClickListener {

    private PresenterProfile presenterProfile;
    private TextView txtNameProfile;
    private GoogleProgressBar progressBarProfile;
    private ImageView imgProfile, imageProfileUpload;
    private LinearLayout layoutChangePassword;
    private String realPathImages;
    private Dialog dialogChanePass;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(v);
        DialogLoading.LoadingGoogle(true, progressBarProfile);
        presenterProfile.getProfile();

        return v;
    }

    private void initView(View v) {
        presenterProfile = new PresenterProfile(this);
        txtNameProfile = v.findViewById(R.id.txtNameProfile);
        progressBarProfile = v.findViewById(R.id.progressBarProfile);
        imgProfile = v.findViewById(R.id.imageProfile);
        layoutChangePassword = v.findViewById(R.id.layoutChangePassword);
        layoutChangePassword.setOnClickListener(this);
    }
    @Override
    public void onSuccess(User user) {
        if (user != null) {
            DialogLoading.LoadingGoogle(false, progressBarProfile);
            txtNameProfile.setText(user.getUsername());
        }
    }

    @Override
    public void onFailed(String msg) {
        DialogLoading.LoadingGoogle(false, progressBarProfile);
        Toasty.error(Objects.requireNonNull(getActivity()), msg, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onChangePasswordSuccess(String msg) {
        DialogLoading.LoadingGoogle(false, progressBarProfile);
        dialogChanePass.dismiss();
        Toasty.success(Objects.requireNonNull(getActivity()), "Đổi mật khẩu thành công", Toasty.LENGTH_LONG).show();
        Toasty.warning(Objects.requireNonNull(getActivity()), "Vui lòng đăng nhập lại!", Toast.LENGTH_SHORT, true).show();
        SharedPreferences pref = getActivity().getSharedPreferences("User", MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", null);
        editor.apply();
        new Handler().postDelayed(() -> {
            startActivity(new Intent(getActivity(), SplashScreenActivity.class));
            getActivity().finish();
        }, 3000);
    }

    @Override
    public void onChangePasswordFail(String msg) {
        DialogLoading.LoadingGoogle(false, progressBarProfile);
        dialogChanePass.dismiss();
        Toasty.error(Objects.requireNonNull(getActivity()), msg, Toasty.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageProfile:
                openPhotoImages();
                break;
            case R.id.layoutChangePassword:
                handlerChangePassword();
                break;
        }

    }

    private void handlerChangePassword() {
        dialogChanePass = new Dialog(Objects.requireNonNull(getActivity()));
        dialogChanePass.setContentView(R.layout.custom_dialog_change_password);
        TextInputEditText edtOldPassword, edtNewPassword, edtNewPasswordAgain;
        TextView txtCloseDialogChangePassword;
        Button btnChangePassword = dialogChanePass.findViewById(R.id.btnChangePassword);
        edtOldPassword = dialogChanePass.findViewById(R.id.edtOldPassword);
        edtNewPassword = dialogChanePass.findViewById(R.id.edtNewPassword);
        edtNewPasswordAgain = dialogChanePass.findViewById(R.id.edtNewPasswordAgain);
        txtCloseDialogChangePassword = dialogChanePass.findViewById(R.id.txtCloseDialogChangePassword);
        btnChangePassword.setOnClickListener(v -> {
            String oldPassword = edtOldPassword.getText().toString().trim();
            String newPassword = edtNewPassword.getText().toString().trim();
            String newPasswordAgain = edtNewPasswordAgain.getText().toString().trim();
            if (edtOldPassword.getText().toString().isEmpty()) {
                edtOldPassword.setError("Empty");
            } else if (newPassword.isEmpty()) {
                edtNewPassword.setError("Empty");
            } else if (newPasswordAgain.isEmpty()) {
                edtNewPasswordAgain.setError("Empty");
            } else if (!newPasswordAgain.equals(newPassword)) {
                edtNewPasswordAgain.setError("New Password Different");
            } else {
                DialogLoading.LoadingGoogle(true, progressBarProfile);
                presenterProfile.changePassword(oldPassword, newPassword);
            }
        });
        txtCloseDialogChangePassword.setOnClickListener(v -> dialogChanePass.dismiss());

        dialogChanePass.show();

    }

    private void openPhotoImages() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 456);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 456) {
            getActivity();
            if (resultCode == Activity.RESULT_OK && data != null) {
                try {
                    realPathImages = getRealPathFromURI(data.getData());
                    Bitmap mBitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(data.getData()));
                    imgProfile.setImageBitmap(mBitmap);
                    showDialogAccept(mBitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showDialogAccept(Bitmap mBitmap) {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog_profile);
        ImageView imageProfileUpload = dialog.findViewById(R.id.imageProfileUpload);
        Button btnAcceptUploadImage, btnCancelUploadImage;
        btnAcceptUploadImage = dialog.findViewById(R.id.btnAcceptUploadImage);
        btnCancelUploadImage = dialog.findViewById(R.id.btnCancelUploadImage);
        imageProfileUpload.setImageBitmap(mBitmap);
        btnCancelUploadImage.setOnClickListener(v -> dialog.dismiss());
        btnAcceptUploadImage.setOnClickListener(v -> {
            File file = new File(realPathImages);
            String filePath = file.getAbsolutePath();
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/from-data"), filePath);
//            MultipartBody.Part body = new MultipartBody.Part.createFormData("file", filePath, requestBody);
//            presenterProfile.uploadImage(body);

        });

        dialog.show();

    }

    private String getRealPathFromURI(Uri contentUri) {
        String path = null;
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = Objects.requireNonNull(getActivity()).getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

}
