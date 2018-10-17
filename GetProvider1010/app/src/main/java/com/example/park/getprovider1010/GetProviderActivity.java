package com.example.park.getprovider1010;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GetProviderActivity extends AppCompatActivity {

    LocationManager mLocMan;
    TextView mStatus;
    TextView mResult;
    String mProvider;
    int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_provider);

        mLocMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mStatus = (TextView)findViewById(R.id.status);
        mResult = (TextView)findViewById(R.id.result);

        mProvider = mLocMan.getBestProvider(new Criteria(),true);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mCount = 0;
        mLocMan.requestLocationUpdates(mProvider,2000,10, mListener);
        mStatus.setText("현재 상태:서비스 시작");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocMan.removeUpdates(mListener);
        mStatus.setText("현재 상태 : 서비스 정지");
    }

    LocationListener mListener = new LocationListener(){

        @Override
        public void onLocationChanged(Location location) {
            mCount++;
            String sloc = String.format(
                    "수신회수 : %d/n위도 : %f/n경도 : %f/n고도 :%f",mCount,location.getLatitude(),location.getLongitude(),
                    location.getAltitude());
            mResult.setText(sloc);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            String sStatus = "";
            switch (status){
                case LocationProvider.OUT_OF_SERVICE:
                    sStatus = "범위 벗어남";
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    sStatus = "일시적 불능";
                    break;
                case LocationProvider.AVAILABLE:
                    sStatus = "사용가능";
                    break;
            }
            mStatus.setText(provider + "상태 변경 : " + sStatus);
        }

        @Override
        public void onProviderEnabled(String provider) {
            mStatus.setText("현재 상태 : 서비스 사용 가능");
        }

        @Override
        public void onProviderDisabled(String provider){
            mStatus.setText("현재 상태 : 서비스 사용 불가");
        }
    };
}
