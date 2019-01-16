package com.example.sokol.testpaging;

import android.arch.paging.PagedList;

import java.util.concurrent.Executors;

public class NumbersRepository {

    private static NumbersRepository instance = new NumbersRepository();

    public static NumbersRepository getInstance() {
        return instance;
    }

    private PagedList<Integer> pagedList;

    private NumbersRepository() {

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(30)
                .setPrefetchDistance(20)
                .build();

        pagedList = new PagedList.Builder<>(new NumbersDataSource(), config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .setNotifyExecutor(new MainExecutor())
                .build();


    }

    public PagedList<Integer> getPagedList() {
        return pagedList;
    }
}