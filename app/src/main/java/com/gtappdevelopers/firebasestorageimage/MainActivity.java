package com.gtappdevelopers.firebasestorageimage;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;


public class MainActivity extends AppCompatActivity {

    //creating a variable for exoplayerview.
    SimpleExoPlayerView exoPlayerView;
    //creating a variable for exoplayer
    SimpleExoPlayer exoPlayer;
    //url of video which we are loading.
    String videoURL="https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exoPlayerView=findViewById(R.id.idExoPlayerVIew);
        try {
            //bandwisthmeter is used for getting default bandwidth
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            // track selector is used to navigate between video using a default seekbar.
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            //we are ading our track selector to exoplayer.
            exoPlayer= ExoPlayerFactory.newSimpleInstance(this,trackSelector);
            // we are parsing a video url and parsing its video uri.
            Uri videouri= Uri.parse(videoURL);
            // we are creating a variable for datasource factory and setting its user agent as 'exoplayer_view'
            DefaultHttpDataSourceFactory dataSourceFactory=new DefaultHttpDataSourceFactory("exoplayer_video");
            // we are creating a variable for extractor factory and setting it to default extractor factory.
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            //we are creating a media source with above variables and passing our event handler as null,
            MediaSource mediaSource = new ExtractorMediaSource(videouri,dataSourceFactory,extractorsFactory,null,null);
            //inside our exoplayer view we are setting our player
            exoPlayerView.setPlayer(exoPlayer);
            //we are preparing our exoplayer with media source.
            exoPlayer.prepare(mediaSource);
            //we are setting our exoplayer when it is ready.
            exoPlayer.setPlayWhenReady(true);

        }catch (Exception e){
            // below line is used for handling our errors.
            Log.e("TAG","Error : "+e.toString());

        }


    }
}