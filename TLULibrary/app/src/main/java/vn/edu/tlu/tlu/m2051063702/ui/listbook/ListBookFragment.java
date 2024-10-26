package vn.edu.tlu.tlu.m2051063702.ui.listbook;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.tlu.tlu.m2051063702.R;

public class ListBookFragment extends Fragment {

    private ListBookViewModel mViewModel;

    public static ListBookFragment newInstance() {
        return new ListBookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_book, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListBookViewModel.class);
        // TODO: Use the ViewModel
    }

}