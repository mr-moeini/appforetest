package com.mrmoeini.qatest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/** Small helpers that give the app an iOS "Settings" look. */
final class Ui {
    private Ui() {}

    static int dp(Context c, float v) {
        return Math.round(v * c.getResources().getDisplayMetrics().density);
    }

    static LinearLayout.LayoutParams lp(int w, int h) {
        return new LinearLayout.LayoutParams(w, h);
    }

    /** Rounded white "inset grouped" card. */
    static LinearLayout card(Context c) {
        LinearLayout card = new LinearLayout(c);
        card.setOrientation(LinearLayout.VERTICAL);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(c.getColor(R.color.ios_card));
        bg.setCornerRadius(dp(c, 12));
        card.setBackground(bg);
        LinearLayout.LayoutParams p = lp(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(dp(c, 16), dp(c, 8), dp(c, 16), dp(c, 8));
        card.setLayoutParams(p);
        return card;
    }

    /** iOS-style row: colored icon tile, title, chevron. */
    static View row(Context c, String title, int tileColor, String glyph, View.OnClickListener onClick) {
        LinearLayout row = new LinearLayout(c);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(dp(c, 16), 0, dp(c, 12), 0);
        row.setMinimumHeight(dp(c, 52));
        row.setClickable(true);
        row.setFocusable(true);
        row.setContentDescription(title);
        row.setOnClickListener(onClick);

        TextView tile = new TextView(c);
        tile.setText(glyph);
        tile.setTextColor(Color.WHITE);
        tile.setTextSize(15);
        tile.setTypeface(Typeface.DEFAULT_BOLD);
        tile.setGravity(Gravity.CENTER);
        GradientDrawable tbg = new GradientDrawable();
        tbg.setColor(tileColor);
        tbg.setCornerRadius(dp(c, 7));
        tile.setBackground(tbg);
        row.addView(tile, lp(dp(c, 30), dp(c, 30)));

        TextView label = new TextView(c);
        label.setText(title);
        label.setTextColor(c.getColor(R.color.ios_label));
        label.setTextSize(17);
        LinearLayout.LayoutParams llp = lp(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        llp.weight = 1;
        llp.leftMargin = dp(c, 14);
        row.addView(label, llp);

        TextView chevron = new TextView(c);
        chevron.setText("\u203A");
        chevron.setTextColor(c.getColor(R.color.ios_separator));
        chevron.setTextSize(26);
        row.addView(chevron, lp(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return row;
    }

    /** Applies system-bar insets as padding so content is not hidden (edge-to-edge on API 35). */
    static void applyInsets(View root) {
        root.setOnApplyWindowInsetsListener((v, insets) -> {
            v.setPadding(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(),
                    insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
            return insets;
        });
    }

    static void light(Activity a) {
        a.getWindow().getDecorView().setBackgroundColor(a.getColor(R.color.ios_background));
    }
}
