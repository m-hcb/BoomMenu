package com.nightonke.boommenusample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.OnBoomListenerAdapter;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class ListenerActivity extends AppCompatActivity {

    private TextView textViewForAnimation;
    private TextView textViewForButton;
    private BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener);

        textViewForButton = (TextView) findViewById(R.id.text_for_button);
        textViewForAnimation = (TextView) findViewById(R.id.text_for_animation);

        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_3);
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) addBuilder(i);

        // Use OnBoomListenerAdapter to listen part of methods
        bmb.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();
                // logic here
            }
        });

        // Use OnBoomListener to listen all methods
        bmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                // If you have implement listeners for boom-buttons in builders,
                // then you should add any listener here for duplicate callbacks.
            }

            @Override
            public void onBackgroundClick() {
                textViewForAnimation.setText("Click background!!!");
            }

            @Override
            public void onBoomWillHide() {
                textViewForAnimation.setText("Will RE-BOOM!!!");
            }

            @Override
            public void onBoomDidHide() {
                textViewForAnimation.setText("Did RE-BOOM!!!");
            }

            @Override
            public void onBoomWillShow() {
                textViewForAnimation.setText("Will BOOM!!!");
            }

            @Override
            public void onBoomDidShow() {
                textViewForAnimation.setText("Did BOOM!!!");
            }
        });
    }

    private void addBuilder(final int index) {
        bmb.addBuilder(new SimpleCircleButton.Builder()
                .normalImageRes(BuilderManager.getImageResource())
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick() {
                        textViewForButton.setText("No." + index + " boom-button is clicked!");
                    }
                }));
    }
}
