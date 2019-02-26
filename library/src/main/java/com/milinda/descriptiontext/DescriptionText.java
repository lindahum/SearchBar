package com.milinda.descriptiontext;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.milinda.searchbar.R;
import com.milinda.utils.Utils;

public class DescriptionText extends LinearLayout {

    private int bgColor;
    private int layoutBg;
    private int layoutPadding;
    private int layoutBottomPadding;
    private String leftTextContent;
    private int leftTextColor;
    private float leftTextSize;
    private String rightTextContent;
    private int rightTextColor;
    private float rightTextSize;
    private int leftTextPadding;
    private int rightTextPadding;
    private int leftTextDrawable;
    private int leftTextDrawablePadding;
    private int rightTextDrawable;
    private int rightTextDrawablePadding;
    private int rightTextGravity;

    private Context context;
    private LayoutInflater inflater;

    private LinearLayout rlDescription;
    private TextView tvLeft;
    private TextView tvRight;

    private int type;

    //纯textview
    private final int DESCRIPTION_TYPE_NO=0;
    //2个textview
    private final int DESCRIPTION_TYPE_TWO=1;
    //选择组件 白底+白底方角
    private final int DESCRIPTION_TYPE_CHOOSE=2;
    //输入组件 白底+白底方角
    private final int DESCRIPTION_TYPE_PIC_SHOW=3;


    public DescriptionText(Context context, AttributeSet attrs) {
        super(context);
        this.context=context;
        init(attrs);
    }

    /**
     * 初始化自定义属性
     * @param attrs
     */
    public void init(AttributeSet attrs){
        TypedArray t = null;
        try{
            t = getContext().obtainStyledAttributes(attrs, R.styleable.DescriptionText);
            int n = t.getIndexCount();
            for(int i=0;i<n;i++){
                int attr = t.getIndex(i);
                if (attr == R.styleable.DescriptionText_DescriptionTextBackgroundColor) {
                    bgColor = t.getColor(R.styleable.DescriptionText_DescriptionTextBackgroundColor,
                            0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextBackground) {
                    layoutBg = t.getResourceId(R.styleable.DescriptionText_DescriptionTextBackground,0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextPadding) {
                    layoutPadding = t.getDimensionPixelOffset(R.styleable.DescriptionText_DescriptionTextPadding,
                            0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextBottomPadding) {
                    layoutBottomPadding = t.getDimensionPixelOffset(R.styleable.DescriptionText_DescriptionTextBottomPadding,
                            0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextLeftTextCotent) {
                    leftTextContent = t.getString(R.styleable.DescriptionText_DescriptionTextLeftTextCotent);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextLeftTextColor) {
                    leftTextColor = t.getColor(R.styleable.DescriptionText_DescriptionTextLeftTextColor,
                           0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextLeftTextSize) {
                    leftTextSize = t.getDimension(R.styleable.DescriptionText_DescriptionTextLeftTextSize,
                            0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextRightTextCotent) {
                    rightTextContent = t.getString(R.styleable.DescriptionText_DescriptionTextRightTextCotent);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextRightTextColor) {
                    rightTextColor = t.getColor(R.styleable.DescriptionText_DescriptionTextRightTextColor,
                            0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextRightTextSize) {
                    rightTextSize = t.getDimension(R.styleable.DescriptionText_DescriptionTextRightTextSize,
                           0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextLeftPadding) {
                    leftTextPadding = t.getDimensionPixelOffset(R.styleable.DescriptionText_DescriptionTextLeftPadding,
                            0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextRightPadding) {
                    rightTextPadding = t.getDimensionPixelOffset(R.styleable.DescriptionText_DescriptionTextRightPadding,
                            0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextLeftDrawable) {
                    leftTextDrawable = t.getResourceId(R.styleable.DescriptionText_DescriptionTextLeftDrawable,0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextLeftDrawablePadding) {
                    leftTextDrawablePadding = t.getDimensionPixelOffset(R.styleable.DescriptionText_DescriptionTextLeftDrawablePadding,
                            0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextRightDrawable) {
                    rightTextDrawable = t.getResourceId(R.styleable.DescriptionText_DescriptionTextRightDrawable,0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextRightDrawablePadding) {
                    leftTextDrawablePadding = t.getDimensionPixelOffset(R.styleable.DescriptionText_DescriptionTextRightDrawablePadding,
                            0);

                }else if (attr == R.styleable.DescriptionText_DescriptionTextRightGravity) {
                    rightTextGravity = t.getInteger(R.styleable.DescriptionText_DescriptionTextRightGravity,1);

                } else if (attr == R.styleable.DescriptionText_DescriptionTextType) {
                    type = t.getInteger(R.styleable.DescriptionText_DescriptionTextType, 0);

                }
            }

        }finally{
            if(null!=t){
                t.recycle();
            }
        }
        initview(context);
    }

    private void initview(Context context) {

        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.include_description_text, this);
        //初始化组件
        rlDescription= view.findViewById(R.id.rl_description_text);
        tvLeft= view.findViewById(R.id.tv_left);
        tvRight= view.findViewById(R.id.tv_right);

        setType(type);

        setBgColor(bgColor);
        setLayoutBg(layoutBg);
        setLayoutPadding(layoutPadding,layoutBottomPadding);
        setLeftTextContent(leftTextContent);
        setLeftTextColor(leftTextColor);
        setLeftTextSize(leftTextSize);
        setRightTextContent(rightTextContent);
        setRightTextColor(rightTextColor);
        setRightTextSize(rightTextSize);
        setLeftPadding(leftTextPadding);
        setRightPadding(rightTextPadding);
        setLeftTextDrawable(leftTextDrawable);
        setLeftTextDrawablePadding(leftTextDrawablePadding);
        setRightTextDrawable(rightTextDrawable);
        setRightTextDrawablePadding(rightTextDrawablePadding);
        setRightTextGravity(rightTextGravity);

    }

    private void setType(int type) {
        this.type = type;
        switch (type){
            case DESCRIPTION_TYPE_TWO:
                tvRight.setVisibility(VISIBLE);
                tvLeft.setVisibility(VISIBLE);
                if(bgColor==0){
                    setBgColor(ContextCompat.getColor(context, R.color.white));
                }
                if(leftTextSize==0){
                    setLeftTextSize(getResources().getDimension(R.dimen.textsize_sp_15));
                }
                if (leftTextColor==0){
                    setLeftTextColor(ContextCompat.getColor(context, R.color.textcolor_555));
                }
                if(rightTextSize==0){
                    setRightTextSize(getResources().getDimension(R.dimen.textsize_sp_18));
                }
                if (rightTextColor==0){
                    setRightTextColor(ContextCompat.getColor(context, R.color.textcolor_333));
                }

                if (layoutPadding==0){
                    setLayoutPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_12),0);
                }

                break;
            case DESCRIPTION_TYPE_CHOOSE:
                tvRight.setVisibility(VISIBLE);
                tvLeft.setVisibility(VISIBLE);

                if(rightTextDrawable==0){
                    setRightTextDrawable(R.drawable.ic_small_arrow_right);
                }
                if (rightTextDrawablePadding==0){
                    setRightTextDrawablePadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_5));
                }

                if(layoutBg==0){
                    setLayoutBg(R.drawable.bg_edittext_line_bottom);
                }
                if(leftTextSize==0){
                    setLeftTextSize(getResources().getDimension(R.dimen.textsize_sp_15));
                }
                if (leftTextColor==0){
                    setLeftTextColor(ContextCompat.getColor(context, R.color.textcolor_555));
                }
                if(rightTextSize==0){
                    setRightTextSize(getResources().getDimension(R.dimen.textsize_sp_18));
                }
                if (rightTextColor==0){
                    setRightTextColor(ContextCompat.getColor(context, R.color.textcolor_333));
                }

                if (layoutPadding==0){
                    setLayoutPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_12),getResources().getDimensionPixelOffset(R.dimen.padding_dp_12));
                }
                break;
            case DESCRIPTION_TYPE_PIC_SHOW:
                tvRight.setVisibility(VISIBLE);
                tvLeft.setVisibility(VISIBLE);

                if (leftTextDrawablePadding==0){
                    setLeftTextDrawablePadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_5));
                }

                if(layoutBg==0){
                    setLayoutBg(R.drawable.bg_edittext_line_bottom);
                }
                if(leftTextSize==0){
                    setLeftTextSize(getResources().getDimension(R.dimen.textsize_sp_15));
                }
                if (leftTextColor==0){
                    setLeftTextColor(ContextCompat.getColor(context, R.color.textcolor_555));
                }
                if(rightTextSize==0){
                    setRightTextSize(getResources().getDimension(R.dimen.textsize_sp_18));
                }
                if (rightTextColor==0){
                    setRightTextColor(ContextCompat.getColor(context, R.color.textcolor_333));
                }

                if (layoutPadding==0){
                    setLayoutPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_12),0);
                }
                break;
            case DESCRIPTION_TYPE_NO:
                tvRight.setVisibility(GONE);
                tvLeft.setVisibility(VISIBLE);
                if(bgColor==0){
                    setBgColor(ContextCompat.getColor(context, R.color.white));
                }
                if(leftTextSize==0){
                    setLeftTextSize(getResources().getDimension(R.dimen.textsize_sp_15));
                }
                if (leftTextColor==0){
                    setLeftTextColor(ContextCompat.getColor(context, R.color.textcolor_555));
                }
                if (layoutPadding==0){
                    setLayoutPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_12),
                            getResources().getDimensionPixelOffset(R.dimen.padding_dp_12));
                }
                break;
        }
    }


    public void setBgColor(int bgColor) {
        if(bgColor!=0){
            this.bgColor = bgColor;
            rlDescription.setBackgroundColor(bgColor);
        }
    }

    public void setLayoutBg(int layoutBg) {
        if(layoutBg>0){
            rlDescription.setBackground(ResourcesCompat.getDrawable(getResources(), layoutBg, null));
            this.layoutBg = layoutBg;
        }
    }

    public void setLayoutPadding(int layoutPadding,int layoutBottomPadding) {
        if(layoutPadding>0){
            this.layoutPadding = layoutPadding;
            if(layoutBottomPadding>0) {
                this.layoutBottomPadding = layoutBottomPadding;
            }
            rlDescription.setPadding(layoutPadding,layoutPadding,layoutPadding,layoutBottomPadding);
        }
    }

    public void setLeftTextColor(int leftTextColor) {
        if(leftTextColor!=0){
            this.leftTextColor = leftTextColor;
            tvLeft.setTextColor(leftTextColor);
        }
    }

    public void setLeftTextSize(float leftTextSize) {
        if(leftTextSize>0){
            this.leftTextSize = leftTextSize;
            tvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX,leftTextSize);
        }
    }

    public void setRightTextColor(int rightTextColor) {
        if(rightTextColor!=0){
            this.rightTextColor = rightTextColor;
            tvRight.setTextColor(rightTextColor);
        }
    }

    public void setRightTextSize(float rightTextSize) {
        if(rightTextSize>0){
            this.rightTextSize = rightTextSize;
            tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX,rightTextSize);
        }
    }

    public void setLeftPadding(int leftTextPadding) {
        if(leftTextPadding>0){
            this.leftTextPadding = leftTextPadding;
            tvLeft.setPadding(leftTextPadding,leftTextPadding,leftTextPadding,leftTextPadding);
        }
    }

    public void setRightPadding(int rightTextPadding) {
        if(rightTextPadding>0){
            this.rightTextPadding = rightTextPadding;
            tvRight.setPadding(rightTextPadding,rightTextPadding,rightTextPadding,rightTextPadding);
        }
    }

    public void setLeftTextDrawable(int leftTextDrawable) {
        if(leftTextDrawable>0){
            tvLeft.setCompoundDrawablesWithIntrinsicBounds(ResourcesCompat.getDrawable(getResources(), leftTextDrawable, null),null,null,null);
            this.leftTextDrawable = leftTextDrawable;
        }
    }

    public void setLeftTextDrawablePadding(int leftTextDrawablePadding) {
        if(leftTextDrawablePadding>0){
            this.leftTextDrawablePadding = leftTextDrawablePadding;
            tvLeft.setCompoundDrawablePadding(leftTextDrawablePadding);
        }
    }

    public void setRightTextDrawable(int rightTextDrawable) {
        if(rightTextDrawable>0){
            tvRight.setCompoundDrawablesWithIntrinsicBounds(null,null,ResourcesCompat.getDrawable(getResources(), rightTextDrawable, null),null);
            this.rightTextDrawable = rightTextDrawable;
        }
    }

    public void setRightTextDrawablePadding(int rightTextDrawablePadding) {
        if(rightTextDrawablePadding>0){
            this.rightTextDrawablePadding = rightTextDrawablePadding;
            tvRight.setCompoundDrawablePadding(rightTextDrawablePadding);
        }
    }

    public void setRightTextGravity(int gravity){
        this.rightTextGravity=gravity;
        switch (gravity){
            case 1:
                tvRight.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                break;
            case 0:
                tvRight.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
                break;
        }
    }

    public void setLeftTextContent(String leftTextContent) {
        if(!Utils.isTrimEmpty(leftTextContent)){
            this.leftTextContent = leftTextContent;
            tvLeft.setText(leftTextContent);
        }
    }

    public void setRightTextContent(String rightTextContent) {
        if(!Utils.isTrimEmpty(rightTextContent)){
            this.rightTextContent = rightTextContent;
            tvRight.setText(rightTextContent);
        }
    }
}
