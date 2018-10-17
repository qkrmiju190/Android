package com.example.mapexfirst181010.mapexfirst181010;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class GetProviderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_provider);

        // 위치 관리자 구함
        LocationManager LocMan = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        // 제공자 목록 구해서 출력
        //List<String> arProvider = LocMan.getProviders(false);
        List<String> arProvider = LocMan.getAllProviders();
        String result = "";
        for (int i = 0; i < arProvider.size(); i++) {
            result += ("Provider " + i + " : " + arProvider.get(i) + "\n");
        }

        // 최적의 제공자 조사
        Criteria crit = new Criteria();
        crit.setAccuracy(Criteria.NO_REQUIREMENT);
        crit.setPowerRequirement(Criteria.NO_REQUIREMENT);
        crit.setAltitudeRequired(false);
        crit.setCostAllowed(false);
        String best = LocMan.getBestProvider(crit, true);
        result += ("\nbest provider : " + best + "\n\n");

        // GPS와 네트워크 제공자 사용 가능성 조사
        result += LocationManager.GPS_PROVIDER + " : " +
                LocMan.isProviderEnabled(LocationManager.GPS_PROVIDER) + "\n";
        result += LocationManager.NETWORK_PROVIDER  + " : " +
                LocMan.isProviderEnabled(LocationManager.NETWORK_PROVIDER ) + "\n";

        // 결과 출력
        TextView EditResult =(TextView)findViewById(R.id.result);
        EditResult.setText(result);
    }
}
