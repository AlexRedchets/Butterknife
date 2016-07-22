package com.example.azvk.butterknife.Clients;

import com.example.azvk.butterknife.Models.Player;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface PlayerClient {
    @GET("api/player/{team}")
    Observable<List<Player>> player(
            @Path("team") String team
    );
}

