package digital.starein.com.project_june27;

import android.support.v4.view.ViewPager;
import android.view.View;


public class VerticalPageTransformer implements ViewPager.PageTransformer{

    private float MIN_SCALE = 0.75f;


    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if ( position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);


        } else if (position <= 1) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            //view.setTranslationX(1);
            view.setScaleX(1);
            view.setScaleY(1);
            float yPosition = position * view.getHeight();
            view.setTranslationY(yPosition);
            view.setTranslationX(-1 * view.getWidth() * position);

        } else if (position <= 0.2) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);

            view.setTranslationX(-1 * view.getWidth() * position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }

    }


}
