package com.grademojo.draganddrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView imag_grag;

    private String msg = "hello";

    private RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imag_grag = (ImageView) findViewById(R.id.imageView);

        imag_grag.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {



                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());

                String[] strings = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragdata = new ClipData(v.getTag().toString(),strings,item);

                View.DragShadowBuilder myshadow = new View.DragShadowBuilder(imag_grag);

                v.startDrag(dragdata,myshadow,null,0);

                return true;
            }
        });



        imag_grag.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                switch (event.getAction())
                {

                    case  DragEvent.ACTION_DRAG_STARTED:

                        layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();

                        Log.d(msg,"action drag started");

                        break;



                    case  DragEvent.ACTION_DRAG_ENTERED:

                        Log.d(msg,"action drag entered");

                        int x_cord = (int) event.getX();

                        int y_cord = (int) event.getY();

                        break;



                    case  DragEvent.ACTION_DRAG_EXITED:

                        Log.d(msg,"action drag excited");

                        x_cord = (int) event.getX();

                        y_cord = (int) event.getY();

                        layoutParams.leftMargin = x_cord;

                        layoutParams.topMargin = y_cord;

                        v.setLayoutParams(layoutParams);
                        break;


                    case DragEvent.ACTION_DRAG_LOCATION:

                        Log.d(msg,"action drag location");

                        x_cord = (int) event.getX();

                        y_cord = (int) event.getY();

                        break;


                    case DragEvent.ACTION_DRAG_ENDED:

                        Log.d(msg,"action drag ended");

                        break;


                    case  DragEvent.ACTION_DROP:

                        Log.d(msg,"action drag drop");


                        break;



                    default:
                        break;
                }


                return true;
            }
        });


        imag_grag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction()==MotionEvent.ACTION_POINTER_DOWN)

                {
                    ClipData data = ClipData.newPlainText("","");

                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(imag_grag);

                    imag_grag.startDrag(data,shadowBuilder,imag_grag,0);

                    imag_grag.setVisibility(View.INVISIBLE);

                    return true;

                }

                else {


                    return false;


                }


            }
        });


    }
}
