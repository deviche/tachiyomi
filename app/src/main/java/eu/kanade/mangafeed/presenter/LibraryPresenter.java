package eu.kanade.mangafeed.presenter;

import android.content.Intent;

import javax.inject.Inject;

import eu.kanade.mangafeed.App;
import eu.kanade.mangafeed.data.helpers.DatabaseHelper;
import eu.kanade.mangafeed.data.models.Manga;
import eu.kanade.mangafeed.ui.activity.MangaDetailActivity;
import eu.kanade.mangafeed.view.LibraryView;
import uk.co.ribot.easyadapter.EasyAdapter;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class LibraryPresenter {

    private LibraryView view;

    @Inject
    public DatabaseHelper db;

    public LibraryPresenter(LibraryView view) {
        this.view = view;
        App.getComponent(view.getActivity()).inject(this);
    }

    public void onMangaClick(EasyAdapter<Manga> adapter, int position) {
        Intent intent = MangaDetailActivity.newIntent(
                view.getActivity(),
                adapter.getItem(position)
        );
        view.getActivity().startActivity(intent);
    }

    public void initializeMangas() {
        db.manga.get()
                .observeOn(mainThread())
                .subscribe(view::setMangas);
    }

}
