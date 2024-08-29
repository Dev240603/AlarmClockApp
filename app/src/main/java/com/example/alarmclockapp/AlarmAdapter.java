package com.example.alarmclockapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {
    private List<Alarm> alarms;
    private OnAlarmToggleListener listener;

    public interface OnAlarmToggleListener {
        void onAlarmToggle(Alarm alarm, boolean isEnabled);
    }

    public AlarmAdapter(List<Alarm> alarms, OnAlarmToggleListener listener) {
        this.alarms = alarms;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Alarm alarm = alarms.get(position);
        holder.bind(alarm);
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    class AlarmViewHolder extends RecyclerView.ViewHolder {
        TextView timeTextView;
        Switch enabledSwitch;

        AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            enabledSwitch = itemView.findViewById(R.id.enabledSwitch);
        }

        void bind(final Alarm alarm) {
            timeTextView.setText(String.format("%02d:%02d", alarm.getHour(), alarm.getMinute()));
            enabledSwitch.setChecked(alarm.isEnabled());

            enabledSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                alarm.setEnabled(isChecked);
                listener.onAlarmToggle(alarm, isChecked);
            });
        }
    }
}