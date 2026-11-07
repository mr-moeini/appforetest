package com.mrmoeini.qatest;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Ui.light(this);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        Ui.applyInsets(root);

        // iOS-like navigation bar: "‹ Menu"  [About]
        FrameLayout bar = new FrameLayout(this);
        bar.setMinimumHeight(Ui.dp(this, 48));

        TextView back = new TextView(this);
        back.setText("\u2039 Menu");
        back.setTextSize(17);
        back.setTextColor(getColor(R.color.ios_blue));
        back.setGravity(Gravity.CENTER_VERTICAL);
        back.setPadding(Ui.dp(this, 16), 0, Ui.dp(this, 16), 0);
        back.setContentDescription("Back");
        back.setOnClickListener(v -> finish());
        bar.addView(back, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, Ui.dp(this, 48), Gravity.START));

        TextView navTitle = new TextView(this);
        navTitle.setText("About");
        navTitle.setTextSize(17);
        navTitle.setTypeface(Typeface.DEFAULT_BOLD);
        navTitle.setTextColor(getColor(R.color.ios_label));
        navTitle.setGravity(Gravity.CENTER);
        bar.addView(navTitle, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, Ui.dp(this, 48), Gravity.CENTER));
        root.addView(bar, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        LinearLayout card = Ui.card(this);
        TextView text = new TextView(this);
        text.setText("develop by : MR Moeini");
        text.setTextSize(17);
        text.setTextColor(getColor(R.color.ios_label));
        text.setPadding(Ui.dp(this, 16), Ui.dp(this, 16), Ui.dp(this, 16), Ui.dp(this, 16));
        card.addView(text);
        LinearLayout.LayoutParams cp = (LinearLayout.LayoutParams) card.getLayoutParams();
        cp.topMargin = Ui.dp(this, 24);
        root.addView(card, cp);

        setContentView(root);
    }
}
