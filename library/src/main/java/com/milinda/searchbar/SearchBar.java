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

/**
 * 作者:Milinda 邮件:Milinda.Hu@gmail.com
 * 创建时间:2018/8/1
 * 描述:工具类
 */
public class SearchBar extends RelativeLayout{

    private int bgColor;
    private int editBg;
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
                            ContextCompat.getColor(context, R.color.background));

                }else if (attr == R.styleable.SearchBar_SearchBarEditBackground) {
                    editBg = t.getResourceId(R.styleable.SearchBar_SearchBarEditBackground,0);

                }else if (attr == R.styleable.SearchBar_SearchBarEditPadding) {
                    editPadding = t.getDimensionPixelOffset(R.styleable.SearchBar_SearchBarEditPadding,
                            getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));

                }else if (attr == R.styleable.SearchBar_SearchBarEditTextColor) {
                    editTextColor = t.getColor(R.styleable.SearchBar_SearchBarEditTextColor,
                            ContextCompat.getColor(context, R.color.textcolor_333));

                }else if (attr == R.styleable.SearchBar_SearchBarEditTextHintColor) {
                    editTextHintColor = t.getColor(R.styleable.SearchBar_SearchBarEditTextHintColor,
                            ContextCompat.getColor(context, R.color.textcolor_ccc));

                }else if (attr == R.styleable.SearchBar_SearchBarEditTextSize) {
                    editTextSize = t.getDimension(R.styleable.SearchBar_SearchBarEditTextSize,
                            getResources().getDimension(R.dimen.textsize_sp_15));

                }else if (attr == R.styleable.SearchBar_SearchBarEditTextPadding) {
                    editTextPadding = t.getDimensionPixelOffset(R.styleable.SearchBar_SearchBarEditTextPadding,
                            getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));

                }else if (attr == R.styleable.SearchBar_SearchBarEditTextDrawable) {
                    editTextDrawable = t.getResourceId(R.styleable.SearchBar_SearchBarEditTextDrawable,0);

                }else if (attr == R.styleable.SearchBar_SearchBarEditTextDrawablePadding) {
                    editTextDrawablePadding = t.getDimensionPixelOffset(R.styleable.SearchBar_SearchBarEditTextDrawablePadding,
                            getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));

                }else if (attr == R.styleable.SearchBar_SearchBarScanDrawable) {
                    scanDrawable = t.getResourceId(R.styleable.SearchBar_SearchBarScanDrawable,0);

                }else if (attr == R.styleable.SearchBar_SearchBarScanPadding) {
                    scanPadding = t.getDimensionPixelOffset(R.styleable.SearchBar_SearchBarScanPadding,
                            getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));

                }else if (attr == R.styleable.SearchBar_SearchBarSearchDrawable) {
                    searchDrawable = t.getResourceId(R.styleable.SearchBar_SearchBarSearchDrawable, 0);

                }else if (attr == R.styleable.SearchBar_SearchBarSearchPadding) {
                    searchPadding = t.getDimensionPixelOffset(R.styleable.SearchBar_SearchBarSearchPadding,
                            getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));

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

        setBgColor(bgColor);
        setEditBg(editBg);
        setEditPadding(editPadding);
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
        setType(type);

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
        bgColor = bgColor==0?ContextCompat.getColor(context, R.color.background):bgColor;
        this.bgColor = bgColor;
        rlSearchBar.setBackgroundColor(bgColor);
    }

    public void setEditBg(int editBg) {
        if(editBg>0){
            rlSearchEdit.setBackground(ResourcesCompat.getDrawable(getResources(), editBg, null));
            this.editBg = editBg;
        }
    }

    public void setEditPadding(int editPadding) {
        editPadding=editPadding==0?getResources().getDimensionPixelOffset(R.dimen.padding_dp_13):editPadding;
        this.editPadding = editPadding;
        rlSearchBar.setPadding(editPadding,editPadding,editPadding,editPadding);
    }

    public void setEditTextColor(int editTextColor) {
        editTextColor = editTextColor==0?ContextCompat.getColor(context, R.color.textcolor_333):editTextColor;
        this.editTextColor = editTextColor;
        etSearch.setTextColor(editTextColor);
    }

    public void setEditTextHintColor(int editTextHintColor) {
        editTextHintColor = editTextHintColor==0?ContextCompat.getColor(context, R.color.textcolor_ccc):editTextHintColor;
        this.editTextHintColor = editTextHintColor;
        etSearch.setHintTextColor(editTextColor);
    }

    public void setEditTextSize(float editTextSize) {
        editTextSize=Utils.handleTextSize(context,editTextSize,R.dimen.textsize_sp_15);
        this.editTextSize = editTextSize;
        etSearch.setTextSize(TypedValue.COMPLEX_UNIT_SP,editTextSize);
    }

    public void setEditTextPadding(int editTextPadding) {
        editTextPadding=editTextPadding==0?getResources().getDimensionPixelOffset(R.dimen.padding_dp_10):editTextPadding;
        this.editTextPadding = editTextPadding;
        etSearch.setPadding(editTextPadding,editTextPadding,editTextPadding,editTextPadding);
    }

    public void setEditTextDrawable(int editTextDrawable) {
        if(editTextDrawable>0){
            etSearch.setCompoundDrawablesWithIntrinsicBounds(ResourcesCompat.getDrawable(getResources(), editTextDrawable, null),null,null,null);
            this.editTextDrawable = editTextDrawable;
        }
    }

    public void setEditTextDrawablePadding(int editTextDrawablePadding) {
        editTextDrawablePadding=editTextDrawablePadding==0?getResources().getDimensionPixelOffset(R.dimen.padding_dp_10):editTextDrawablePadding;
        this.editTextDrawablePadding = editTextDrawablePadding;
        etSearch.setCompoundDrawablePadding(editTextDrawablePadding);
    }

    public void setScanDrawable(int scanDrawable) {
        if(scanDrawable>0){
            tvScan.setCompoundDrawablesWithIntrinsicBounds(null,null,ResourcesCompat.getDrawable(getResources(), scanDrawable, null),null);
            this.scanDrawable = scanDrawable;
        }
    }

    public void setScanPadding(int scanPadding) {
        scanPadding=scanPadding==0?getResources().getDimensionPixelOffset(R.dimen.padding_dp_10):scanPadding;
        this.scanPadding = scanPadding;
        tvScan.setPadding(scanPadding,scanPadding,scanPadding,scanPadding);
    }

    public void setSearchDrawable(int searchDrawable) {
        if(searchDrawable>0){
            tvSearch.setCompoundDrawablesWithIntrinsicBounds(ResourcesCompat.getDrawable(getResources(), searchDrawable, null),null,null,null);
            this.searchDrawable = searchDrawable;
        }
    }

    public void setSearchPadding(int searchPadding) {
        searchPadding=searchPadding==0?getResources().getDimensionPixelOffset(R.dimen.padding_dp_10):searchPadding;
        this.searchPadding = searchPadding;
        tvSearch.setPadding(searchPadding,searchPadding,0,searchPadding);
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
                setBgColor(ContextCompat.getColor(context, R.color.background));
                setEditBg(R.drawable.bg_edittext_search_white);
                setEditTextDrawable(R.drawable.ic_search_goods);
                setSearchPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));
                setSearchContent(context.getString(R.string.btn_search_content));
                setScanDrawable(R.drawable.ic_scan);
                setScanPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_14));
                break;
            case SEARCHBAR_TYPE_SCAN_SURE:
                tvScan.setVisibility(VISIBLE);
                tvSearch.setVisibility(VISIBLE);
                setBgColor(ContextCompat.getColor(context, R.color.white));
                setEditBg(R.drawable.bg_edittext_line);
                setEditTextPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_12));
                setScanDrawable(R.drawable.ic_scan);
                setScanPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_8));
                setSearchContent("");
                setSearchDrawable(R.drawable.ic_action_go);
                setSearchPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_10));
                break;
            case SEARCHBAR_TYPE_CHOOSE:
                tvScan.setVisibility(VISIBLE);
                tvSearch.setVisibility(GONE);
                setBgColor(ContextCompat.getColor(context, R.color.white));
                setEditBg(R.drawable.bg_edittext_line);
                setScanDrawable(R.drawable.ic_small_arrow_right);
                setEditTextPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_12));
                break;
            case SEARCHBAR_TYPE_NO:
                tvScan.setVisibility(GONE);
                tvSearch.setVisibility(GONE);
                setBgColor(ContextCompat.getColor(context, R.color.white));
                setEditBg(R.drawable.bg_edittext_line);
                setEditTextPadding(getResources().getDimensionPixelOffset(R.dimen.padding_dp_12));
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
