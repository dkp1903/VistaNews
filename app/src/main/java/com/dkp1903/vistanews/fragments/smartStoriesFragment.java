package com.dkp1903.vistanews.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dkp1903.vistanews.MainActivity;
import com.dkp1903.vistanews.Model.Adapter;
import com.dkp1903.vistanews.Model.ApiClient;
import com.dkp1903.vistanews.Model.Articles;
import com.dkp1903.vistanews.Model.Headlines;
import com.dkp1903.vistanews.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class smartStoriesFragment extends Fragment {
    RecyclerView recyclerView;
    final String API_KEY = "03db90899c064911aa9517883d5415bd";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();



    public smartStoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentVie

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String country = getCountry();
        retrieveJson(country, API_KEY);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        View layoutView = inflater.inflate(R.layout.fragment_smart_stories, container, false);
        recyclerView = layoutView.findViewById(R.id.smartStories);
        //recyclerView.setLayoutManager(layoutManager);

        return layoutView;
    }

    public void retrieveJson(String country, String apiKey) {
        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country, apiKey);

        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticles() != null){
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(getActivity(), articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}
