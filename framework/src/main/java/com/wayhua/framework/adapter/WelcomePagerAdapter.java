/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wayhua.framework.adapter;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wayhua.framework.R;


public class WelcomePagerAdapter extends PagerAdapter {

    private final String[] info;
    private final String[] titles;
    private final int[] images;
    private int view;
    Context context;

    public WelcomePagerAdapter(Context context, int layoutId, int[] images, String[] info, String[] titles) {
        view = layoutId;
        this.images = images;
        this.info = info;
        this.titles = titles;
        this.context = context;
    }

    /**
     * @param images 图片
     * @param info   图片说明
     * @param titles 标题
     */
    public WelcomePagerAdapter(Context context, int[] images, String[] info, String[] titles) {
        view =
                R.layout.welcome_pager_item;
        this.images = images;
        this.info = info;
        this.titles = titles;
        this.context = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = LayoutInflater.from(container.getContext())
                .inflate(view, container, false);

        TextView titleText = ((TextView) v.findViewById( R.id.tv_title));
        TextView infoText = ((TextView) v.findViewById( R.id.tv_info));
        titleText.setText(titles[position]);
        infoText.setText(info[position]);
        if (position == 0) {
            @ColorInt int primaryColor = titleText.getResources().getColor( R.color.colorPrimary);
            titleText.setTextColor(primaryColor);
            infoText.setTextColor(primaryColor);
        }
        ImageView imageView = (ImageView) v.findViewById( R.id.iv_art);
        //.setImageResource(images[position]);
        Glide.with(context).load(images[position]).into(imageView);
        container.addView(v);
        return v;
    }

    @Override
    public int getCount() {
        return info.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
