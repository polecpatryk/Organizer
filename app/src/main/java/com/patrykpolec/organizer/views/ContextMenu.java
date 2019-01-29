package com.patrykpolec.organizer.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.patrykpolec.organizer.R;

public class ContextMenu {

    PopupMenu menu;

    @SuppressLint("ResourceType")
    public ContextMenu(View v, Context context) {
        menu = new PopupMenu(context, v);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

    }

    public void Create() {
        menu.inflate(R.menu.menu_context);
        menu.show();
    }
}
