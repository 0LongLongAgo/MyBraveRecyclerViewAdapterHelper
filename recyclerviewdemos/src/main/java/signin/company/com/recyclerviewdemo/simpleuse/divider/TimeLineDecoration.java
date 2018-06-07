package signin.company.com.recyclerviewdemo.simpleuse.divider;

/**
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import signin.company.com.recyclerviewdemo.R;


/**
 * This class is from the v7 samples of the Android SDK. It's not by me!
 * <p/>
 * See the license above for details.
 */
public class TimeLineDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;
    private Drawable mLevelDrawable;

     private Bitmap mLevelBitmapGreen;
     private Bitmap mLevelBitmapGray;
     private Bitmap mLevelBitmapYellow;
     private Bitmap mCurrentBitmap;

    private int mOrientation;
    private Paint mPaint;

    public TimeLineDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
//        mLevelDrawable = context.getDrawable(R.mipmap.green);
        mLevelBitmapGreen = BitmapFactory.decodeResource(context.getResources(), R.mipmap.green);
        mLevelBitmapGray = BitmapFactory.decodeResource(context.getResources(), R.mipmap.green);
        mLevelBitmapYellow = BitmapFactory.decodeResource(context.getResources(), R.mipmap.green);
        mCurrentBitmap =mLevelBitmapGreen;
        mDivider = a.getDrawable(0);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setStrokeWidth(10);
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

//    @Override
//    public void onDraw(Canvas c, RecyclerView parent) {
//
//        if (mOrientation == VERTICAL_LIST) {
//            drawVertical(c, parent);
//        } else {
//            drawHorizontal(c, parent);
//        }
//
//    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == VERTICAL_LIST) {

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View view = parent.getChildAt(i);
                Integer readStatus = (Integer) view.getTag();
                switch (readStatus) {
                    case 0:
                        mCurrentBitmap = mLevelBitmapGreen;
                        break;
                    case 1:
                        mCurrentBitmap = mLevelBitmapYellow;
                        break;
                    case 2:
                        mCurrentBitmap = mLevelBitmapGray;
                        break;
                    default:
                        mCurrentBitmap = mLevelBitmapGreen;
                }
                int index = parent.getChildAdapterPosition(view);
                float dividerTop = view.getTop() - 30;
                float dividerLeft = parent.getPaddingLeft();
                float dividerRight = parent.getWidth() - parent.getPaddingRight();
                float dividerBottom = view.getBottom() + 30;

//                float upLineTopX = centerX;
//                float upLineTopY = dividerTop;
//                float upLineBottomX = centerX;
//                float upLineBottomY = centerY -20;
//
//                c.drawLine(upLineTopX, upLineTopY, upLineBottomX, upLineBottomY, mPaint);
//
//                c.drawCircle(centerX, centerY, 20, mPaint);
//
//                float downLineTopX = centerX;
//                float downLineTopY = centerY +20;
//                float downLineBottomX = centerX;
//                float downLineBottomY = dividerBottom;
//                c.drawLine(downLineTopX, downLineTopY, downLineBottomX, downLineBottomY, mPaint);

                float leftTopX = dividerLeft + 60;
                float leftTopY = dividerTop + 15;
                float rightBottomX = dividerRight - 15;
                float rightBottomY = dividerBottom - 15;
                mPaint.setColor(Color.parseColor("#dedee7"));
                mPaint.setStrokeWidth(4);
                mPaint.setStyle(Paint.Style.STROKE);
                c.drawLine(dividerLeft + 60, view.getTop() - 45, dividerLeft + 60, rightBottomY + 45, mPaint);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    c.drawRoundRect(leftTopX,leftTopY,rightBottomX,rightBottomY,10f,10f,mPaint);
//                }else{
////                c.drawRect(leftTopX,leftTopY,rightBottomX,rightBottomY,mPaint);//画矩形
//                }

                float centerX = dividerLeft + 60;
                float centerY = dividerTop + (dividerBottom - dividerTop) / 2;
//
//                mPaint.setColor(Color.WHITE);
//                mPaint.setStyle(Paint.Style.FILL);
//                c.drawCircle(centerX,centerY,45,mPaint);//覆盖掉圆圈里的竖线
//
//                mPaint.setColor(Color.RED);
//                mPaint.setStyle(Paint.Style.STROKE);
//                c.drawCircle(centerX,centerY,30,mPaint);//画个圆圈
                c.drawBitmap(mCurrentBitmap, new Rect(0, 0, mCurrentBitmap.getWidth(), mCurrentBitmap.getHeight()), new RectF(centerX - 40, centerY - 40, centerX + 40, centerY + 40), mPaint);
//                c.drawCircle(centerX,centerY,45,mPaint);//画个圆圈

//                mPaint.setStyle(Paint.Style.FILL);
//                mPaint.setTextAlign(Paint.Align.CENTER);
//                mPaint.setTextSize(45);
//                Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
//                float y = centerY  + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
//                c.drawText(String.valueOf(realPosition),centerX,y,mPaint);//画序号
            }
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
//        final int left = parent.getPaddingLeft();
//        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView v = new RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
//            final int top = child.getBottom() + params.bottomMargin;
//            final int bottom = top + mDivider.getIntrinsicHeight();
//            final int bottom = top;

//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(c);
            final int top = child.getTop() - 50;
            final int left = child.getLeft();
//            final int left = parent.getPaddingLeft();
            final int right = child.getRight();
            final int bottom = child.getTop();
            c.drawRect(Float.valueOf(left), Float.valueOf(top), Float.valueOf(right), Float.valueOf(bottom), mPaint);
//            c.drawLine(Float.valueOf(left), Float.valueOf(top), Float.valueOf(right), Float.valueOf(bottom),mPaint);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

//    @Override
//    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
//        if (mOrientation == VERTICAL_LIST) {
//            outRect.set(20, 50, 20, 50);
//        } else {
//            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
//        }
//    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(120, 45, 60, 45);
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }

}
