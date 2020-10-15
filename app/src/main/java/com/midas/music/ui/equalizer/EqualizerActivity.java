package com.midas.music.ui.equalizer;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.PresetReverb;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.midas.music.R;
import com.midas.music.ui.base.BaseActivity;

import butterknife.BindView;

public class EqualizerActivity extends BaseActivity {

    @BindView(R.id.chip_group)
    ChipGroup chipGroup;
    @BindView(R.id.controllerBass)
    AnalogController bassController;
    @BindView(R.id.controller3D)
    AnalogController reverbController;
    @BindView(R.id.equalizerBlocker)
    FrameLayout equalizerBlocker;

    SeekBar[] seekBarFinal = new SeekBar[5];

    public Equalizer mEqualizer;
    public BassBoost bassBoost;
    public PresetReverb presetReverb;
    private int audioSesionId;

    int y = 0;
    short numberOfFrequencyBands;
    float[] points;

    static int themeColor = Color.parseColor("#B24242");

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_equalizer;
    }

    @Override
    protected String setToolbarTitle() {
        return getString(R.string.title_equalizer);
    }

    @Override
    protected void initView() {
//        View view = LayoutInflater.from(this).inflate(R.layout.switch_layout_view, null);
//        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
//        getSupportActionBar().setCustomView(view, layoutParams);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        titleEqualizerSwitch = view.findViewById(R.id.title_switch_btn);
//        titleEqualizerSwitch.setBackgroundColor(Color.YELLOW);

        Settings.isEditing = true;

        audioSesionId = getIntent().getIntExtra(AudioEffect.EXTRA_AUDIO_SESSION, 0);//, PlayManager.getAudioSessionId())

        if (Settings.equalizerModel == null){
            Settings.equalizerModel = new EqualizerModel();
            Settings.equalizerModel.setReverbPreset(PresetReverb.PRESET_NONE);
            Settings.equalizerModel.setBassStrength((short) (1000 / 19));
        }

        mEqualizer = new Equalizer(0, audioSesionId);

        bassBoost = new BassBoost(0, audioSesionId);
        bassBoost.setEnabled(Settings.isEqualizerEnabled);
        BassBoost.Settings bassBoostSettingTemp = bassBoost.getProperties();
        BassBoost.Settings bassBoostSetting = new BassBoost.Settings(bassBoostSettingTemp.toString());
        bassBoostSetting.strength = Settings.equalizerModel.getBassStrength();
        bassBoost.setProperties(bassBoostSetting);

        presetReverb = new PresetReverb(0, audioSesionId);
        presetReverb.setPreset(Settings.equalizerModel.getReverbPreset());
        presetReverb.setEnabled(Settings.isEqualizerEnabled);

        mEqualizer.setEnabled(Settings.isEqualizerEnabled);
        equalizerBlocker.setVisibility(Settings.isEqualizerEnabled ? View.INVISIBLE : View.VISIBLE);

        if (Settings.presetPos == 0){
            for (short bandIdx = 0; bandIdx < mEqualizer.getNumberOfBands(); bandIdx++) {
                mEqualizer.setBandLevel(bandIdx, (short) Settings.seekbarpos[bandIdx]);
            }
        }
        else {
            mEqualizer.usePreset((short) Settings.presetPos);
        }


        bassController.setLabel(R.string.euqalizer_bass);
        reverbController.setLabel(R.string.euqalizer_3d);

        bassController.circlePaint2.setColor(themeColor);
        bassController.linePaint.setColor(themeColor);
        bassController.invalidate();
        reverbController.circlePaint2.setColor(themeColor);
        bassController.linePaint.setColor(themeColor);
        reverbController.invalidate();

        if (!Settings.isEqualizerReloaded) {
            int x = 0;
            if (bassBoost != null) {
                try {
                    x = ((bassBoost.getRoundedStrength() * 19) / 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (presetReverb != null) {
                try {
                    y = (presetReverb.getPreset() * 19) / 6;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (x == 0) {
                bassController.setProgress(1);
            } else {
                bassController.setProgress(x);
            }

            if (y == 0) {
                reverbController.setProgress(1);
            } else {
                reverbController.setProgress(y);
            }
        } else {
            int x = ((Settings.bassStrength * 19) / 1000);
            y = (Settings.reverbPreset * 19) / 6;
            if (x == 0) {
                bassController.setProgress(1);
            } else {
                bassController.setProgress(x);
            }

            if (y == 0) {
                reverbController.setProgress(1);
            } else {
                reverbController.setProgress(y);
            }
        }

        bassController.setOnProgressChangedListener(new AnalogController.onProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress) {
                Settings.bassStrength = (short) (((float) 1000 / 19) * (progress));
                try {
                    bassBoost.setStrength(Settings.bassStrength);
                    Settings.equalizerModel.setBassStrength(Settings.bassStrength);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        reverbController.setOnProgressChangedListener(new AnalogController.onProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress) {
                Settings.reverbPreset = (short) ((progress * 6) / 19);
                Settings.equalizerModel.setReverbPreset(Settings.reverbPreset);
                try {
                    presetReverb.setPreset(Settings.reverbPreset);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                y = progress;
            }
        });

//        mLinearLayout = view.findViewById(R.id.equalizerContainer);

        TextView equalizerHeading = new TextView(getContext());
        equalizerHeading.setText(R.string.equalizer);
        equalizerHeading.setTextSize(20);
        equalizerHeading.setGravity(Gravity.CENTER_HORIZONTAL);

        numberOfFrequencyBands = 5;

        points = new float[numberOfFrequencyBands];

        final short lowerEqualizerBandLevel = mEqualizer.getBandLevelRange()[0];
        final short upperEqualizerBandLevel = mEqualizer.getBandLevelRange()[1];

        for (short i = 0; i < numberOfFrequencyBands; i++) {
            final short equalizerBandIndex = i;
            final TextView frequencyHeaderTextView = new TextView(getContext());
//            frequencyHeaderTextView.setLayoutParams(new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            ));
            frequencyHeaderTextView.setGravity(Gravity.CENTER_HORIZONTAL);
//            frequencyHeaderTextView.setTextColor(Color.parseColor("#FFFFFF"));
            frequencyHeaderTextView.setText((mEqualizer.getCenterFreq(equalizerBandIndex) / 1000) + "Hz");

            LinearLayout seekBarRowLayout = new LinearLayout(getContext());
            seekBarRowLayout.setOrientation(LinearLayout.VERTICAL);

            TextView lowerEqualizerBandLevelTextView = new TextView(getContext());
//            lowerEqualizerBandLevelTextView.setLayoutParams(new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
//            ));
//            lowerEqualizerBandLevelTextView.setTextColor(Color.parseColor("#FFFFFF"));
            lowerEqualizerBandLevelTextView.setText((lowerEqualizerBandLevel / 100) + "dB");

            TextView upperEqualizerBandLevelTextView = new TextView(getContext());
//            lowerEqualizerBandLevelTextView.setLayoutParams(new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            ));
//            upperEqualizerBandLevelTextView.setTextColor(Color.parseColor("#FFFFFF"));
            upperEqualizerBandLevelTextView.setText((upperEqualizerBandLevel / 100) + "dB");

//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            );
//            layoutParams.weight = 1;

            SeekBar seekBar = new SeekBar(getContext());
            TextView textView = new TextView(getContext());
            switch (i) {
                case 0:
                    seekBar = findViewById(R.id.seekBar1);
                    textView = findViewById(R.id.textView1);
                    break;
                case 1:
                    seekBar = findViewById(R.id.seekBar2);
                    textView = findViewById(R.id.textView2);
                    break;
                case 2:
                    seekBar = findViewById(R.id.seekBar3);
                    textView = findViewById(R.id.textView3);
                    break;
                case 3:
                    seekBar = findViewById(R.id.seekBar4);
                    textView = findViewById(R.id.textView4);
                    break;
                case 4:
                    seekBar = findViewById(R.id.seekBar5);
                    textView = findViewById(R.id.textView5);
                    break;
            }
            seekBarFinal[i] = seekBar;
            seekBar.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN));
            seekBar.getThumb().setColorFilter(new PorterDuffColorFilter(themeColor, PorterDuff.Mode.SRC_IN));
            seekBar.setId(i);
//            seekBar.setLayoutParams(layoutParams);
            seekBar.setMax(upperEqualizerBandLevel - lowerEqualizerBandLevel);

            textView.setText(frequencyHeaderTextView.getText());
//            textView.setTextColor(Color.WHITE);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            if (Settings.isEqualizerReloaded) {
                points[i] = Settings.seekbarpos[i] - lowerEqualizerBandLevel;
//                dataset.addPoint(frequencyHeaderTextView.getText().toString(), points[i]);
                seekBar.setProgress(Settings.seekbarpos[i] - lowerEqualizerBandLevel);
            } else {
                points[i] = mEqualizer.getBandLevel(equalizerBandIndex) - lowerEqualizerBandLevel;
                seekBar.setProgress(mEqualizer.getBandLevel(equalizerBandIndex) - lowerEqualizerBandLevel);
                Settings.seekbarpos[i] = mEqualizer.getBandLevel(equalizerBandIndex);
                Settings.isEqualizerReloaded = true;
            }

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    mEqualizer.setBandLevel(equalizerBandIndex, (short) (progress + lowerEqualizerBandLevel));
                    points[seekBar.getId()] = mEqualizer.getBandLevel(equalizerBandIndex) - lowerEqualizerBandLevel;
                    Settings.seekbarpos[seekBar.getId()] = (progress + lowerEqualizerBandLevel);
                    Settings.equalizerModel.getSeekbarpos()[seekBar.getId()] = (progress + lowerEqualizerBandLevel);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    ((Chip)chipGroup.getChildAt(0)).setChecked(true);
                    Settings.presetPos = 0;
                    Settings.equalizerModel.setPresetPos(0);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
        equalizeSound();
    }

    public void equalizeSound() {
        Chip chipCustom = (Chip) getLayoutInflater().inflate(R.layout.equalizer_chip, chipGroup, false);
        chipCustom.setText(getString(R.string.euqalizer_custom));
        chipGroup.addView(chipCustom);

        for (short i = 0; i < mEqualizer.getNumberOfPresets(); i++) {
            Chip chip = (Chip) getLayoutInflater().inflate(R.layout.equalizer_chip, chipGroup, false);
            chip.setText(mEqualizer.getPresetName(i));
            chipGroup.addView(chip);
        }

        for(int i = 0; i < chipGroup.getChildCount(); i ++) {
            Chip chip = (Chip)chipGroup.getChildAt(i);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chip.setChecked(true);
                    int position = chipGroup.indexOfChild(chip);
                    try {
                        if (position != 0) {
                            mEqualizer.usePreset((short) (position - 1));
                            Settings.presetPos = position;
                            short numberOfFreqBands = 5;
                            final short lowerEqualizerBandLevel = mEqualizer.getBandLevelRange()[0];

                            for (short i = 0; i < numberOfFreqBands; i++) {
                                seekBarFinal[i].setProgress(mEqualizer.getBandLevel(i) - lowerEqualizerBandLevel);
                                points[i] = mEqualizer.getBandLevel(i) - lowerEqualizerBandLevel;
                                Settings.seekbarpos[i] = mEqualizer.getBandLevel(i);
                                Settings.equalizerModel.getSeekbarpos()[i] = mEqualizer.getBandLevel(i);
                            }
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Error while updating Equalizer", Toast.LENGTH_SHORT).show();
                    }
                    Settings.equalizerModel.setPresetPos(position);
                }
            });
        }

        if (Settings.isEqualizerReloaded && Settings.presetPos != 0) {
            ((Chip)chipGroup.getChildAt(Settings.presetPos)).setChecked(true);
        } else {
            ((Chip)chipGroup.getChildAt(0)).setChecked(true);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initInjector() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_equalizer, menu);
        MenuItem euqalizerMenuItem = menu.findItem(R.id.euqalizer_switch);
        SwitchCompat titleEqualizerSwitch = (SwitchCompat) euqalizerMenuItem.getActionView();
        titleEqualizerSwitch.setChecked(Settings.isEqualizerEnabled);
        titleEqualizerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mEqualizer.setEnabled(isChecked);
                bassBoost.setEnabled(isChecked);
                presetReverb.setEnabled(isChecked);
                equalizerBlocker.setVisibility(isChecked ? View.INVISIBLE : View.VISIBLE);
                Settings.isEqualizerEnabled = isChecked;
                Settings.equalizerModel.setEqualizerEnabled(isChecked);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mEqualizer != null){
            mEqualizer.release();
        }

        if (bassBoost != null){
            bassBoost.release();
        }

        if (presetReverb != null){
            presetReverb.release();
        }

        Settings.isEditing = false;
    }
}
