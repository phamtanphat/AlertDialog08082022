package com.example.alertdialog08082022;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    Button btnAlertDialog, btnDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlertDialog = findViewById(R.id.button_alert_dialog);
        btnDialog = findViewById(R.id.button_dialog);

        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo!!!");
                builder.setMessage("Đã có bản cập nhật mới");
                builder.setIcon(android.R.drawable.star_on);
                builder.setCancelable(false);

                builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

        btnDialog.setOnClickListener(new View.OnClickListener() {
            int colorAlphaValue = 0;
            int colorRedValue = 0;
            int colorGreenValue = 0;
            int colorBlueValue = 0;
            TextView tvHex, tvRgb, tvButtonOk, tvButtonCancel;
            ImageView imgBackground;
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_picker_color_dialog);

                Window window = dialog.getWindow();

                if (window != null) {
                    window.setGravity(Gravity.CENTER);
                    window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                }

                SeekBar seekBarAlpha = dialog.findViewById(R.id.seek_bar_alpha);
                SeekBar seekBarRed = dialog.findViewById(R.id.seek_bar_red);
                SeekBar seekBarGreen = dialog.findViewById(R.id.seek_bar_green);
                SeekBar seekBarBlue = dialog.findViewById(R.id.seek_bar_blue);
                tvHex = dialog.findViewById(R.id.text_view_hex_color);
                tvRgb = dialog.findViewById(R.id.text_view_rgb_color);
                tvButtonOk = dialog.findViewById(R.id.text_view_ok);
                tvButtonCancel = dialog.findViewById(R.id.text_view_cancel);
                imgBackground = dialog.findViewById(R.id.image_view_background);

                // Set max value seekbar
                int maxValueOfColor = 255;
                seekBarAlpha.setMax(maxValueOfColor);
                seekBarRed.setMax(maxValueOfColor);
                seekBarGreen.setMax(maxValueOfColor);
                seekBarBlue.setMax(maxValueOfColor);

                // Event change seekbar
                seekBarAlpha.setOnSeekBarChangeListener(listenerSeekBarChange);
                seekBarRed.setOnSeekBarChangeListener(listenerSeekBarChange);
                seekBarGreen.setOnSeekBarChangeListener(listenerSeekBarChange);
                seekBarBlue.setOnSeekBarChangeListener(listenerSeekBarChange);

                dialog.show();
            }

            private SeekBar.OnSeekBarChangeListener listenerSeekBarChange = new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("DefaultLocale")
                @Override
                public void onProgressChanged(SeekBar seekBar, int colorValue, boolean b) {
                    switch (seekBar.getId()) {
                        case R.id.seek_bar_alpha:
                            colorAlphaValue = colorValue;
                            break;
                        case R.id.seek_bar_red:
                            colorRedValue = colorValue;
                            break;
                        case R.id.seek_bar_green:
                            colorGreenValue = colorValue;
                            break;
                        case R.id.seek_bar_blue:
                            colorBlueValue = colorValue;
                            break;
                    }

                    tvRgb.setText(String.format(
                                    "RGB %d %d %d %d",
                                    colorAlphaValue,
                                    colorRedValue,
                                    colorGreenValue,
                                    colorBlueValue)
                    );

                    imgBackground.setBackgroundColor(Color.argb(
                                                            colorAlphaValue,
                                                            colorRedValue,
                                                            colorGreenValue,
                                                            colorBlueValue)
                    );
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
        });
    }
}
