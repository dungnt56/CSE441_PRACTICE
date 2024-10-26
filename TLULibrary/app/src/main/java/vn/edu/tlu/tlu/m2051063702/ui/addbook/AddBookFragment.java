package vn.edu.tlu.tlu.m2051063702.ui.addbook;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import vn.edu.tlu.tlu.m2051063702.Constant;
import vn.edu.tlu.tlu.m2051063702.R;
import vn.edu.tlu.tlu.m2051063702.model.Category;

public class AddBookFragment extends Fragment {
    private EditText titleEditText, authorEditText, yearEditText, descriptionEditText, imageEditText;
    private Spinner categorySpinner;
    private Button saveButton;
    private AddBookViewModel mViewModel;
    private OkHttpClient client = new OkHttpClient();
    private List<Category> categoryList = new ArrayList<>();
    private ArrayAdapter<String> categoryAdapter;

    public static AddBookFragment newInstance() {
        return new AddBookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        titleEditText = view.findViewById(R.id.titleEditText);
        authorEditText = view.findViewById(R.id.authorEditText);
        yearEditText = view.findViewById(R.id.yearEditText);
        descriptionEditText = view.findViewById(R.id.descriptionEditText);
        imageEditText = view.findViewById(R.id.imageEditText);
        categorySpinner = view.findViewById(R.id.categorySpinner);
        saveButton = view.findViewById(R.id.saveButton);

        fetchCategories();

        // Sự kiện cho nút Lưu
        saveButton.setOnClickListener(v -> saveBook());
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddBookViewModel.class);
        // TODO: Use the ViewModel
    }

    private void saveBook() {
        String title = titleEditText.getText().toString();
        String author = authorEditText.getText().toString();
        String year = yearEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String imageUrl = imageEditText.getText().toString();

        int selectedPosition = categorySpinner.getSelectedItemPosition();
        Long categoryId = categoryList.get(selectedPosition).getId();

        Toast.makeText(getContext(), "Book saved with category ID: " + categoryId, Toast.LENGTH_SHORT).show();
    }
    private void fetchCategories() {

        String url = Constant.BASE_URL + "/get-all-category";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String jsonResponse = response.body().string();
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Category>>() {}.getType();
                    categoryList = gson.fromJson(jsonResponse, listType);

                    List<String> categoryNames = new ArrayList<>();
                    for (Category category : categoryList) {
                        categoryNames.add(category.getName());
                    }

                    getActivity().runOnUiThread(() -> {
                        categoryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categoryNames);
                        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        categorySpinner.setAdapter(categoryAdapter);
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }
        });
    }
}