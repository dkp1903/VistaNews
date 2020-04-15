package com.dkp1903.vistanews.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dkp1903.vistanews.Model.Adapter;
import com.dkp1903.vistanews.Model.ApiClient;
import com.dkp1903.vistanews.Model.Articles;
import com.dkp1903.vistanews.Model.Headlines;
import com.dkp1903.vistanews.R;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class smartStoriesFragment extends Fragment {
    private final String API_KEY = "03db90899c064911aa9517883d5415bd";
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Articles> articles;
    private String country;
    private View view;


    public smartStoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view != null) {
            view = inflater.inflate(R.layout.fragment_smart_stories, container, false);
            country = getCountry();
            recyclerView = view.findViewById(R.id.recycler1);
            retrieveJson(country, API_KEY);
        }
        return view;
    }

    private void retrieveJson(String country, String apiKey) {
        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country, apiKey);

        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null) {
                    articles = response.body().getArticles();
                    Log.d("HI", String.valueOf(articles.get(0).getTitle()));
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    adapter = new Adapter(getContext(), articles);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
            }
        });
    }

    private String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}
