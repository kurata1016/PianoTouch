package example.android.mapsample;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements LocationListener, OnConnectionFailedListener, ConnectionCallbacks {
	// 初期座標(スカイツリー)
	private static LatLng START_POS = new LatLng(35.710184468125725, 139.81106221675873);
	// 地図
	private GoogleMap map;
	// カメラ
	private CameraUpdate camera;
	// マーカーを設置する設定
	private MarkerOptions markers;
	// locationManager取得
	private LocationManager mgr;
	// locationClient取得
	private LocationClient mLocationClient;
	// リクエスト設定
	private static final LocationRequest REQUEST = LocationRequest.create().setInterval(5000).setFastestInterval(16).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		setup();
	}

	// 地図の設定
	private void setup() {
		// カメラの基本的な設定をセット
		final CameraPosition pos = new CameraPosition(START_POS, 16, 0, 0);
		// カメラにセット
		camera = CameraUpdateFactory.newCameraPosition(pos);
		// カメラの位置に移動
		map.moveCamera(camera);
		// マーカーの準備
		markers = new MarkerOptions();
		// マーカーの座標を決定
		markers.position(START_POS);
		// マーカーを追加
		map.addMarker(markers);
	}

	// XMLレイアウトのロード
	private void findView() {
		// FragmentManagerのロード
		final FragmentManager manager = getSupportFragmentManager();
		// MapFragmentのロード
		final SupportMapFragment frag = (SupportMapFragment) manager.findFragmentById(R.id.map);
		// Map内容のロード
		map = frag.getMap();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// メニュー選択時の動作
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.location:
			mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			mgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) MainActivity.this);
			return true;
		default:
			break;
		}
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
		// 経度・緯度取得
		double lat = location.getLatitude(); // latitude
		double lon = location.getLongitude(); // longitude
		LatLng Pos = new LatLng(lat, lon);
		// カメラポジション設定
		CameraPosition cameraPos = new CameraPosition.Builder().target(Pos).zoom(16.0f).bearing(0).build();
		// 移動
		map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos));
		// マーカーの準備
		markers = new MarkerOptions();
		// マーカーの座標を決定
		markers.position(Pos);
		// マーカーを追加
		map.addMarker(markers);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void onConnected(Bundle arg0) {
		mLocationClient.requestLocationUpdates(REQUEST, (com.google.android.gms.location.LocationListener) this);
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO 自動生成されたメソッド・スタブ
	}

}
