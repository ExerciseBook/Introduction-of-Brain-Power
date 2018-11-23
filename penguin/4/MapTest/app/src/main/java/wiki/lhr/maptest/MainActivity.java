package wiki.lhr.maptest;

import java.util.*;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.amap.api.maps.*;
import com.amap.api.location.*;
import com.amap.api.maps.model.*;

public class MainActivity extends AppCompatActivity {

    private AMapLocationClient locationClientContinue=null;
    private AMapLocationClientOption locationClientOption = null;

    private MapView mapView;
    private AMap aMap;


    private Date time_start;
    private TextView viewTime;

    private TextView viewHeartBeats;
    private int heartBeats=0;

    private LatLng lastLocation=null;
    private TextView viewDistance;
    private float distance=0.00f;


    private void requestPermisson() {
        List<String> permissionList = new ArrayList<>();
//用于进行网络定位
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) { permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION); }
//用于访问GPS定位
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) { permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION); }
//用于获取运营商信息，用于支持提供运营商信息相关的接口
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) { permissionList.add(Manifest.permission.ACCESS_NETWORK_STATE); }
//用于访问wifi网络信息，wifi信息会用于进行网络定位
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) { permissionList.add(Manifest.permission.ACCESS_WIFI_STATE); }
//用于获取wifi的获取权限，wifi信息会用来进行网络定位
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) { permissionList.add(Manifest.permission.CHANGE_WIFI_STATE); }
//用于访问网络，网络定位需要上网
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) { permissionList.add(Manifest.permission.INTERNET); }
//用于读取手机当前的状态
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) { permissionList.add(Manifest.permission.READ_PHONE_STATE); }
//用于写入缓存数据到扩展存储卡
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) { permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE); }
//用于申请调用A-GPS模块
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS) != PackageManager.PERMISSION_GRANTED) { permissionList.add(Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS); }
//用于申请获取蓝牙信息进行室内定位
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) { permissionList.add(Manifest.permission.BLUETOOTH); }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) { permissionList.add(Manifest.permission.BLUETOOTH_ADMIN); }

        if (!permissionList.isEmpty()) {
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestPermisson();


        mapView = (MapView) findViewById(R.id.map);//找到地图控件
        //在activity执行onCreate时执行 mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);
        aMap = mapView.getMap();//初始化地图控制器对象
        aMap.setMyLocationEnabled(true); //显示当前位置

        AMapLocationClient locationClientContinue = new AMapLocationClient(this.getApplicationContext());

        //使用连续的定位方式  默认连续
        locationClientOption = new AMapLocationClientOption();
        // 地址信息
        locationClientOption.setNeedAddress(true);
        locationClientOption.setInterval(1000);
        locationClientContinue.setLocationOption(locationClientOption);
        locationClientContinue.setLocationListener(locationContinueListener);
        locationClientContinue.startLocation();


        viewDistance = (TextView) findViewById(R.id.viewDistance);
        viewHeartBeats = (TextView) findViewById(R.id.viewHeartBeats);
        viewTime = (TextView) findViewById(R.id.viewTime);


        time_start = new Date();

    }


    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    long time_consuming (){
        Date now = new Date();
        System.out.println(now.getTime());
        return (now.getTime() - time_start.getTime())/1000;
    }

    /**
     * 连续客户端的定位监听
     */
    AMapLocationListener locationContinueListener = new AMapLocationListener() {


        @Override
        public void onLocationChanged(AMapLocation location) {
            heartBeats++;

            LatLng newLocation = new LatLng(location.getLatitude(),location.getLongitude());

            System.out.printf("%.6f %.6f\n",location.getLatitude(),location.getLongitude());
            System.out.printf(location.toJson(1).toString());

            if (lastLocation!=null) {
                distance += AMapUtils.calculateLineDistance(lastLocation,newLocation);

            };

            lastLocation=newLocation;


            viewTime.setText(String.format("Time: %ds",time_consuming()));
            viewDistance.setText(String.format("Dist: %.2fm",distance));
            viewHeartBeats.setText(String.format("Beat: %d",heartBeats));

        }
    };



}
