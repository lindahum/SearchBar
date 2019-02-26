package com.milinda.searchbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.milinda.utils.Utils;

/**
 * 作者:Milinda 邮件:Milinda.Hu@gmail.com
 * 创建时间:2018/8/1
 * 描述:工具类
 */
public class SearchBar extends RelativeLayout{

    private int bgColor;
    private int editBg;
    private int editBottomPadding;
    private int editPadding;
    private int editTextColor;
    private int editTextHintColor;
    private float editTextSize;
    private int editTextPadding;
    private int editTextDrawable;
    private int editTextDrawablePadding;
    private int scanDrawable;
    private int scanPadding;
    private int searchDrawable;
    private int searchPadding;
    //搜索栏的类型
    private int type=0;
    //搜索栏的编辑框的提示内容
    private String editHintContent="";
    //搜索栏的搜索或确认按钮内容
    private String searchContent="";

    //扫码和搜索按钮 灰底+白底圆角
    private final int SEARCHBAR_TYPE_SCAN_SEARCH=0;
    //扫码和确定按钮 白底+白底方角
    private final int SEARCHBAR_TYPE_SCAN_SURE=1;
    //选择组件 白底+白底方角
    private final int SEARCHBAR_TYPE_CHOOSE=2;
    //输入组件 白底+白底方角
    private final int SEARCHBAR_TYPE_NO=3;
    //选择组件 白底+白底方角 + 向下选择
    private final int SEARCHBAR_TYPE_CHOOSE_DOWN=4;

    private static boolean isShowScan=true;
    private static boolean isShowSearch=true;

    private Context context;
    private LayoutInflater inflater;

    private RelativeLayout rlSearchBar;
    private RelativeLayout rlSearchEdit;
    private EditText etSearch;
    private TextView tvScan;
    private TextView tvSearch;


    public SearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
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
            t = getContext().obtainStyledAttributes(attrs, R.styleable.SearchBar);
            int n = t.getIndexCount();
            for(int i=0;i<n;i++){
                int attr = t.getIndex(i);
                if (attr == R.styleable.SearchBar_SearchBarBackgroundColor) {
                    bgColor = t.getColor(R.styleable.SearchBar_SearchBarBackgroundColor,
                            0);

                }else if (attr == R.styleable.SearchBar_SearchBarEditBackground) {
                    editBg = t.getResourceId(R.styleable.SearchBar_SearchBarEditBackground,0);

                }else if (attr == R.styleable.SearchBar_SearchBarEditPadding) {
                    editPadding = t.getDimensionPixelOffset(R.styleable.SearchBar_SearchBarEditPadding,
                            0);

                }else if (attr == R.styleable.SearchBar_SearchBarEditBottomPadding) {
                    editBottomPadding = t.getDimensionPixelOffset(R.styleable.SearchBar_SearchBarEditBottomPadding,
                            0);

                }else if (attr == R.styleable.SearchBar_SearchBarEditTextColor) {
                    editTextColor = t.getColor(R.styleable.SearchBar_SearchBarEditTextColor,
                            0);

                }else if (attr == R.styleable.SearchBar_SearchBarEditTextHintColor) {
                    editTextHintColor = t.getColor(R.styleable.SearchBar_SearchBarEditTextHintColor,
                            0);

                }else if (attr == R.styleable.SearchBar_SearchBarEditTextSize) {
                    editTextSize = t.getDimension(R.styleable.SearchBar_SearchBarEditTextSize,
                            0);

                }else if (attr == R.styleable.SearchBar_SearchBarEditTextPadding) {
                    editTextPadding = t.getDimensionPixelOffset(R.styleable.SearchBar_SearchBarEditTextPadding,
                            0);

                }else if (attr == R.styleable.SearchBar_SearchBarEditTextDrawable) {
                    editTextDrawable = t.getResourceId(R.styleable.SearchBar_SearchBarEditTextDrawable,0);

                }else if (attr == R.styleable.SearchBar_SearchBarEditTextDrawablePadding) {
                    editTextDrawablePadding = t.getDimensionPixelOffset(R.styleable.SearchBar_SearchBarEditTextDrawablePadding,
                           0);

                }else if (attr == R.styleable.SearchBar_SearchBarScanDrawable) {
                    scanDrawable = t.getResourceId(R.styleable.SearchBar_SearchBarScanDrawable,0);

                }else if (attr == R.styleable.SearchBar_SearchBarScanPadding) {
                    scanPadding = t.getDimensionPixelOffset(R.styleable.SearchBar_SearchBarScanPadding,
                            0);

                }else if (attr == R.styleable.SearchBar_SearchBarSearchDrawable) {
                    searchDrawable = t.getResourceId(R.styleable.SearchBar_SearchBarSearchDrawable, 0);

                }else if (attr == R.styleable.SearchBar_SearchBarSearchPadding) {
                    searchPadding = t.getDimensionPixelOffset(R.styleable.SearchBar_SearchBarSearchPadding,
                          0);

                } else if (attr == R.styleable.SearchBar_SearchBarType) {
                    type = t.getInteger(R.styleable.SearchBar_SearchBarType, 0);

                } else if (attr == R.styleable.SearchBar_SearchBarEdittextHintContent) {
                    editHintContent = t.getString(R.styleable.SearchBar_SearchBarEdittextHintContent);

                } else if (attr == R.styleable.SearchBar_SearchBarSearchContent) {
                    searchContent = t.getString(R.styleable.SearchBar_SearchBarSearchContent);
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
        View view = inflater.inflate(R.layout.include_searchbar, this);
        //初始化组件
        tvScan= view.findViewById(R.id.tv_scan);
        tvSearch= view.findViewById(R.id.tv_search);
        etSearch= view.findViewById(R.id.et_search);
        rlSearchEdit= view.findViewById(R.id.rl_search_edit);
        rlSearchBar= view.findViewById(R.id.rl_searchbar);

        setType(type);

        setBgColor(bgColor);
        setEditBg(editBg);
        setEditPadding(editPadding,editBottomPadding);
        setEditTextColor(editTextColor);
        setEditTextHintColor(editTextHintColor);
        setEditTextSize(editTextSize);
        setEditTextPadding(editTextPadding);
        setEditTextDrawable(editTextDrawable);
        setEditTextDrawablePadding(editTextDrawablePadding);
        setScanDrawable(scanDrawable);
        setScanPadding(scanPadding);
        setSearchDrawable(searchDrawable);
        setSearchPadding(searchPadding);
        setSearchContent(searchContent);
        setEditHintContent(editHintContent);

        setConfig();

    }

    private void setConfig() {
        if(!isShowScan){
            tvScan.setVisibility(GONE);
        }
        if(!isShowSearch){
            tvSearch.setVisibility(GONE);
        }
    }


    public void setBgColor(int bgColor) {
        if(bgColor!=0){
            this.bgColor = bgColor;
            rlSearchBar.setBackgroundColor(bgColor);
        }
    }

    public void setEditBg(int editBg) {
        if(editBg>0){
            rlSearchEdit.setBackgroundResource(editBg);
            this.editBg = editBg;
        }
    }

    public void setEditPadding(int editPadding,int editBottomPadding) {
        if(editPadding>0) {
            this.editPadding = editPadding;

            if(editBottomPadding>0) {
                this.editBottomPadding = editBottomPadding;
            }
            rlSearchBar.setPadding(editPadding, editPadding, editPadding, editBottomPadding);
        }
    }

    public void setEditTextColor(int editTextColor) {
        if(editTextColor!=0){
            this.editTextColor = editTextColor;
            etSearch.setTextColor(editTextColor);
        }
    }

    public void setEditTextHintColor(int editTextHintColor) {
        if(editTextHintColor!=0){
            this.editTextHintColor = editTextHintColor;
            etSearch.setHintTextColor(editTextHintColor);
        }
    }

    public void setEditTextSize(float editTextSize) {
        if(editTextSize>0){
            this.editTextSize = editTextSize;
            etSearch.setTextSize(TypedValue.COMPLEX_UNIT_PX,editTextSize);
        }
    }

    public void setEditTextPadding(int editTextPadding) {
        if(editTextPadding>0){
            this.editTextPadding = editTextPadding;
            etSearch.setPadding(editTextPadding,editTextPadding,editTextPadding,editTextPadding);
        }
    }

    public void setEditTextDrawable(int editTextDrawable) {
        if(editTextDrawable>0){
            etSearch.setCompoundDrawablesWithIntrinsicBounds(ResourcesCompat.getDrawable(getResources(), editTextDrawable, null),null,null,null);
            this.editTextDrawable = editTextDrawable;
        }
    }

    public void setEditTextDrawablePadding(int editTextDrawablePadding) {
        if(editTextDrawablePadding>0){
            this.editTextDrawablePadding = editTextDrawablePadding;
            etSearch.setCompoundDrawablePadding(editTextDrawablePadding);
        }
    }

    public void setScanDrawable(int scanDrawable) {
        if(scanDrawable>0){
            tvScan.setCompoundDrawablesWithIntrinsicBounds(null,null,ResourcesCompat.getDrawable(getResources(), scanDrawable, null),null);
            this.scanDrawable = scanDrawable;
        }
    }

    public void setScanPadding(int scanPadding) {
        if(scanPadding>0){
            this.scanPadding = scanPadding;
            tvScan.setPadding(scanPadding,scanPadding,scanPadding,scanPadding);
        }
    }

    public void setSearchDrawable(int searchDrawable) {
        if(searchDrawable>0){
            tvSearch.setCompoundDrawablesWithIntrinsicBounds(ResourcesCompat.getDrawable(getResources(), searchDrawable, null),null,null,null);
            this.searchDrawable = searchDrawable;
        }
    }

    public void setSearchPadding(int searchPadding) {
        if(searchPadding>0){
            this.searchPadding = searchPadding;
            tvSearch.setPadding(searchPadding,searchPadding,0,searchPadding);
        }
    }

    private void setEditorAction(int action){
        etSearch.setImeOptions(action);
        switch (action){
            case EditorInfo.IME_ACTION_GO:
                break;
            case EditorInfo.IME_ACTION_SEARCH:
                break;
        }
    }

    public void setType(int type) {
        this.type = type;
        switch (type){
            case SEARCHBAR_TYPE_SCAN_SEARCH:
                tvScan.setVisibility(VISIBLE);
                tvSearch.setVisibility(VISIBLE);
                if(bgColor==0){
                    setBgColor(ContextCompat.getColor(context,R.color.background));
                }
                if(editBg==0){
                    setEditBg(R.drawable.bg_edittext_search_white);
                }
                if(editTextPadding==0){
                    setEditTextPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));
                }
                if(editTextSize==0){
                    setEditTextSize(getResources().getDimension(R.dimen.textsize_sp_15));
                }
                if(editTextColor==0){
                    setEditTextColor(ContextCompat.getColor(context,R.color.textcolor_333));
                }
                if(editTextHintColor==0){
                    setEditTextHintColor(ContextCompat.getColor(context,R.color.textcolor_555));
                }
                if(editTextDrawable==0){
                    setEditTextDrawable(R.drawable.ic_search_goods);
                }
                if(editTextDrawablePadding==0){
                    setEditTextDrawablePadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));
                }
                if(searchPadding==0){
                    setSearchPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));
                }
                if(Utils.isTrimEmpty(searchContent)){
                    setSearchContent(context.getString(R.string.btn_search_content));
                }
                if(scanDrawable==0){
                    setScanDrawable(R.drawable.ic_scan);
                }
                if(scanPadding==0){
                    setScanPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_14));
                }
                if(editPadding==0){
                    setEditPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10),
                            getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));
                }

                break;
            case SEARCHBAR_TYPE_SCAN_SURE:
                tvScan.setVisibility(VISIBLE);
                tvSearch.setVisibility(VISIBLE);

                if(bgColor==0){
                    setBgColor(ContextCompat.getColor(context,R.color.white));
                }
                if(editBg==0){
                    setEditBg(R.drawable.bg_edittext_line);
                }
                if(editTextPadding==0){
                    setEditTextPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));
                }
                if(editTextSize==0){
                    setEditTextSize(getResources().getDimension(R.dimen.textsize_sp_15));
                }
                if(editTextColor==0){
                    setEditTextColor(ContextCompat.getColor(context,R.color.textcolor_333));
                }
                if(editTextHintColor==0){
                    setEditTextHintColor(ContextCompat.getColor(context,R.color.textcolor_555));
                }
                if(searchDrawable==0){
                    setSearchDrawable(R.drawable.ic_action_go);
                }
                if(searchPadding==0){
                    setSearchPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));
                }

                if(scanDrawable==0){
                    setScanDrawable(R.drawable.ic_scan);
                }
                if(scanPadding==0){
                    setScanPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_8));
                }
                if(editPadding==0){
                    setEditPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10),0);
                }

                break;
            case SEARCHBAR_TYPE_CHOOSE:
                tvScan.setVisibility(VISIBLE);
                tvSearch.setVisibility(GONE);

                if(bgColor==0){
                    setBgColor(ContextCompat.getColor(context,R.color.white));
                }
                if(editBg==0){
                    setEditBg(R.drawable.bg_edittext_line);
                }
                if(editTextPadding==0){
                    setEditTextPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));
                }
                if(editTextSize==0){
                    setEditTextSize(getResources().getDimension(R.dimen.textsize_sp_15));
                }
                if(editTextColor==0){
                    setEditTextColor(ContextCompat.getColor(context,R.color.textcolor_333));
                }
                if(scanDrawable==0){
                    setScanDrawable(R.drawable.ic_small_arrow_right);
                }
                if(scanPadding==0){
                    setScanPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_8));
                }
                if(editPadding==0){
                    setEditPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10),0);
                }

                Utils.forbidEdittext(etSearch);
                break;
            case SEARCHBAR_TYPE_CHOOSE_DOWN:
                tvScan.setVisibility(VISIBLE);
                tvSearch.setVisibility(GONE);
                if(bgColor==0){
                    setBgColor(ContextCompat.getColor(context,R.color.white));
                }
                if(editBg==0){
                    setEditBg(R.drawable.bg_edittext_line);
                }
                if(editTextPadding==0){
                    setEditTextPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));
                }
                if(editTextSize==0){
                    setEditTextSize(getResources().getDimension(R.dimen.textsize_sp_15));
                }
                if(editTextColor==0){
                    setEditTextColor(ContextCompat.getColor(context,R.color.textcolor_333));
                }
                if(scanDrawable==0){
                    setScanDrawable(R.drawable.ic_down);
                }
                if(scanPadding==0){
                    setScanPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_8));
                }
                if(editPadding==0){
                    setEditPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10),0);
                }

                Utils.forbidEdittext(etSearch);
                break;
            case SEARCHBAR_TYPE_NO:
                tvScan.setVisibility(GONE);
                tvSearch.setVisibility(GONE);

                if(bgColor==0){
                    setBgColor(ContextCompat.getColor(context,R.color.white));
                }
                if(editBg==0){
                    setEditBg(R.drawable.bg_edittext_line);
                }
                if(editTextPadding==0){
                    setEditTextPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));
                }
                if(editTextSize==0){
                    setEditTextSize(getResources().getDimension(R.dimen.textsize_sp_15));
                }
                if(editTextColor==0){
                    setEditTextColor(ContextCompat.getColor(context,R.color.textcolor_333));
                }
                if(editTextHintColor==0){
                    setEditTextHintColor(ContextCompat.getColor(context,R.color.textcolor_555));
                }
                if(editPadding==0){
                    setEditPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10),0);
                }

                break;
        }
    }

    public void setEditHintContent(String editHintContent) {
        if(!Utils.isTrimEmpty(editHintContent)){
            this.editHintContent = editHintContent;
            etSearch.setHint(editHintContent);
        }
    }

    public void setSearchContent(String searchContent) {
        if(!Utils.isTrimEmpty(searchContent)){
            this.searchContent = searchContent;
            tvSearch.setText(searchContent);
        }
    }

    public static void initConfig(Boolean isShowScanB,Boolean isShowSearchB){
        isShowScan=isShowScanB;
        isShowSearch=isShowSearchB;
    }


}
