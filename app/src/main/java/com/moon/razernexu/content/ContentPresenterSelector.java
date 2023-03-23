package com.moon.razernexu.content;

import androidx.leanback.widget.ListRow;

import com.moon.razernexu.bean.TypeSeven;
import com.moon.razernexu.presenter.ImageRowHeaderPresenter;
import com.moon.razernexu.presenter.TypeFiveContentPresenter;
import com.moon.razernexu.presenter.TypeFourContentPresenter;
import com.moon.razernexu.presenter.TypeOneContentPresenter;
import com.moon.razernexu.presenter.TypeSevenPresenter;
import com.moon.razernexu.presenter.TypeSixContentPresenter;
import com.moon.razernexu.presenter.TypeThreeContentPresenter;
import com.moon.razernexu.presenter.TypeTwoContentPresenter;
import com.moon.razernexu.presenter.TypeZeroContentPresenter;
import com.moon.razernexu.presenter.row.ContentListRowPresenter;
import com.moon.razernexu.presenter.row.TypeFiveListRowPresenter;
import com.moon.razernexu.presenter.row.TypeTwoListRowPresenter;
import com.moon.razernexu.presenter.row.TypeZeroListRowPresenter;
import com.moon.razernexu.ui.BasePresenterSelector;


public class ContentPresenterSelector extends BasePresenterSelector {
    public ContentPresenterSelector() {
        ContentListRowPresenter listRowPresenter = new ContentListRowPresenter();
        listRowPresenter.setShadowEnabled(false);
        listRowPresenter.setSelectEffectEnabled(false);
        listRowPresenter.setKeepChildForeground(false);
        listRowPresenter.setHeaderPresenter(new ImageRowHeaderPresenter());



        TypeZeroListRowPresenter listRowPresenterOne = new TypeZeroListRowPresenter();
        listRowPresenterOne.setShadowEnabled(false);
        listRowPresenterOne.setSelectEffectEnabled(false);
        listRowPresenterOne.setKeepChildForeground(false);
        listRowPresenterOne.setHeaderPresenter(new ImageRowHeaderPresenter());

        addClassPresenter(ListRow.class, listRowPresenterOne, TypeZeroContentPresenter.class);
        addClassPresenter(ListRow.class, listRowPresenterOne, TypeOneContentPresenter.class);

        TypeTwoListRowPresenter listRowPresenterTwo = new TypeTwoListRowPresenter();
        listRowPresenterTwo.setShadowEnabled(false);
        listRowPresenterTwo.setSelectEffectEnabled(false);
        listRowPresenterTwo.setKeepChildForeground(false);
        listRowPresenterTwo.setHeaderPresenter(new ImageRowHeaderPresenter());




        addClassPresenter(ListRow.class, listRowPresenterTwo, TypeTwoContentPresenter.class);
        addClassPresenter(ListRow.class, listRowPresenterOne, TypeThreeContentPresenter.class);
        addClassPresenter(ListRow.class, listRowPresenterOne, TypeFourContentPresenter.class);

        TypeFiveListRowPresenter listRowPresenterFive = new TypeFiveListRowPresenter();
        listRowPresenterFive.setShadowEnabled(false);
        listRowPresenterFive.setSelectEffectEnabled(false);
        listRowPresenterFive.setKeepChildForeground(false);
        listRowPresenterFive.setHeaderPresenter(new ImageRowHeaderPresenter());

        addClassPresenter(ListRow.class, listRowPresenterFive, TypeFiveContentPresenter.class);
        addClassPresenter(ListRow.class, listRowPresenter, TypeSixContentPresenter.class);

        addClassPresenter(TypeSeven.class, new TypeSevenPresenter());

    }

}
