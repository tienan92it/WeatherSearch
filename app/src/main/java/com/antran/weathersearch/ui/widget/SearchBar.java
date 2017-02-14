package com.antran.weathersearch.ui.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.antran.weathersearch.R;
import com.antran.weathersearch.util.Utils;


/**
 * Created by AN TRAN on 2/11/17.
 */

public class SearchBar extends RelativeLayout {

    private Context context;
    private SearchEvent searchEvent;
    private RelativeLayout searchContent;
    private ImageView imgSearch;
    private TextView txtSearch;
    private EditText edtSearch;
    private Button btnSearch;

    public SearchBar(Context context) {
        super(context);
        this.context = context;
        loadComponents(context);

        if (!isInEditMode()) {
            init();
        }
    }

    public SearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        loadComponents(context);

        if (!isInEditMode()) {
            init();
        }
    }

    public SearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        loadComponents(context);

        if (!isInEditMode()) {
            init();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SearchBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        loadComponents(context);

        if (!isInEditMode()) {
            init();
        }
    }

    private void loadComponents(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_bar, this);
        searchContent = (RelativeLayout) view.findViewById(R.id.search_content);
        imgSearch = (ImageView) view.findViewById(R.id.search_img);
        imgSearch.setColorFilter(ContextCompat.getColor(context, R.color.gray));
        txtSearch = (TextView) view.findViewById(R.id.search_txt);
        edtSearch = (EditText) view.findViewById(R.id.search_edt);
        btnSearch = (Button) view.findViewById(R.id.search_button);
    }


    private void init() {
        initEvent();
    }

    private void initEvent() {
        searchContent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startSearch();
            }
        });

        imgSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startSearch();
            }
        });

        txtSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startSearch();
            }
        });

        edtSearch.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!edtSearch.hasFocus()) {
                    stopSearch();
                }
            }
        });
    }

    public void stopSearch() {
        ViewGroup.LayoutParams layoutParams = searchContent.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        searchContent.setLayoutParams(layoutParams);

        txtSearch.setVisibility(View.VISIBLE);
        edtSearch.setVisibility(View.GONE);
        edtSearch.setText(null);
        edtSearch.clearFocus();
        btnSearch.setVisibility(View.GONE);
        Utils.hideKeyboard(edtSearch);

        if (searchEvent != null)
            searchEvent.onStopSearching();
    }

    private void startSearch() {
        ViewGroup.LayoutParams layoutParams = searchContent.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        searchContent.setLayoutParams(layoutParams);

        txtSearch.setVisibility(View.GONE);
        edtSearch.setVisibility(View.VISIBLE);
        btnSearch.setVisibility(View.VISIBLE);


        edtSearch.requestFocus();

        Utils.showKeyboard(edtSearch);

        if (searchEvent != null)
            searchEvent.onSearching();

    }

    public void addTextChangedListener(TextWatcher tw) {
        edtSearch.addTextChangedListener(tw);
    }

    public void setOnClickSearchButton(OnClickListener onClickListener) {
        btnSearch.setOnClickListener(onClickListener);
    }

    public boolean isSearching() {
        if (edtSearch.hasFocus()) {
            return true;
        } else {
            return false;
        }
    }

    public String getSearchText() {
        return edtSearch.getText().toString().trim();
    }

    public void setSearchEvent(SearchEvent searchEvent) {
        this.searchEvent = searchEvent;
    }

    public interface SearchEvent {
        void onSearching();

        void onStopSearching();
    }

}
