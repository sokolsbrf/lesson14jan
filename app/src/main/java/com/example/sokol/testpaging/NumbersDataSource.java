package com.example.sokol.testpaging;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class NumbersDataSource extends PositionalDataSource<Integer> {

    private static final int LIMIT = 10000;

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {

        highload();

        List<Integer> result = new ArrayList<>(params.requestedLoadSize);

        for (int i = 0; i < params.requestedLoadSize; i++) {
            result.add(params.requestedStartPosition + i);
        }

        callback.onResult(result, params.requestedStartPosition, LIMIT);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback callback) {

        highload();

        List<Integer> result = new ArrayList<>(params.loadSize);

        for (int i = 0; i < params.loadSize; i++) {
            result.add(params.startPosition + i);
        }

        callback.onResult(result);
    }


    private void highload() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
