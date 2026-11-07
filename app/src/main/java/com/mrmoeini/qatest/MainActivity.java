package com.mrmoeini.qatest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Ui.light(this);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        Ui.applyInsets(scroll);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        scroll.addView(root, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // iOS large title
        TextView title = new TextView(this);
        title.setText("Menu");
        title.setTextSize(34);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setTextColor(getColor(R.color.ios_label));
        title.setPadding(Ui.dp(this, 20), Ui.dp(this, 24), Ui.dp(this, 20), Ui.dp(this, 12));
        root.addView(title);

        LinearLayout card = Ui.card(this);
        card.addView(Ui.row(this, "About", getColor(R.color.ios_blue), "i", v ->
                startActivity(new Intent(this, AboutActivity.class))));
        root.addView(card);

        setContentView(scroll);
    }
}
