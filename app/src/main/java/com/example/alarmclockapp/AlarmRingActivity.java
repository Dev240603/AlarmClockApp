package com.example.alarmclockapp;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmRingActivity extends AppCompatActivity {
    private Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_ring);

        Button dismissButton = findViewById(R.id.dismissButton);
        Button snoozeButton = findViewById(R.id.snoozeButton);

        playAlarmSound();

        dismissButton.setOnClickListener(v -> {
            stopAlarmSound();
            finish();
        });

        snoozeButton.setOnClickListener(v -> {
            // Implement snooze functionality here
            stopAlarmSound();
            finish();
        });
    }




    private void playAlarmSound() {
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(this, alarmUri);
        ringtone.play();
    }

    private void stopAlarmSound() {
        if (ringtone != null && ringtone.isPlaying()) {
            ringtone.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAlarmSound();
    }
}

