package com.aquib.deeniyatassignment.hifaztdua;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;

public class BookPageIntroFragment extends Fragment {

    String imageId;

    public static BookPageIntroFragment newInstance(String imageId)
    {
        BookPageIntroFragment fragment = new BookPageIntroFragment();
        Bundle args = new Bundle();
        args.putString("imageId", imageId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageId = getArguments().getString("imageId", "Duas/bimari/en/d1.png");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_page_layout, container, false);

        ImageView imageView = rootView.findViewById(R.id.imageView);

     //   imageView.setImageResource(imageId);
        try
        {
            // get input stream
            InputStream ims = getActivity().getAssets().open(imageId);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            imageView.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
       //     return;
        }



        return rootView;
    }
}
