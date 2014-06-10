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
	// �������W(�X�J�C�c���[)
	private static LatLng START_POS = new LatLng(35.710184468125725, 139.81106221675873);
	// �n�}
	private GoogleMap map;
	// �J����
	private CameraUpdate camera;
	// �}�[�J�[��ݒu����ݒ�
	private MarkerOptions markers;
	// locationManager�擾
	private LocationManager mgr;
	// locationClient�擾
	private LocationClient mLocationClient;
	// ���N�G�X�g�ݒ�
	private static final LocationRequest REQUEST = LocationRequest.create().setInterval(5000).setFastestInterval(16).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		setup();
	}

	// �n�}�̐ݒ�
	private void setup() {
		// �J�����̊�{�I�Ȑݒ���Z�b�g
		final CameraPosition pos = new CameraPosition(START_POS, 16, 0, 0);
		// �J�����ɃZ�b�g
		camera = CameraUpdateFactory.newCameraPosition(pos);
		// �J�����̈ʒu�Ɉړ�
		map.moveCamera(camera);
		// �}�[�J�[�̏���
		markers = new MarkerOptions();
		// �}�[�J�[�̍��W������
		markers.position(START_POS);
		// �}�[�J�[��ǉ�
		map.addMarker(markers);
	}

	// XML���C�A�E�g�̃��[�h
	private void findView() {
		// FragmentManager�̃��[�h
		final FragmentManager manager = getSupportFragmentManager();
		// MapFragment�̃��[�h
		final SupportMapFragment frag = (SupportMapFragment) manager.findFragmentById(R.id.map);
		// Map���e�̃��[�h
		map = frag.getMap();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// ���j���[�I�����̓���
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
		// �o�x�E�ܓx�擾
		double lat = location.getLatitude(); // latitude
		double lon = location.getLongitude(); // longitude
		LatLng Pos = new LatLng(lat, lon);
		// �J�����|�W�V�����ݒ�
		CameraPosition cameraPos = new CameraPosition.Builder().target(Pos).zoom(16.0f).bearing(0).build();
		// �ړ�
		map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos));
		// �}�[�J�[�̏���
		markers = new MarkerOptions();
		// �}�[�J�[�̍��W������
		markers.position(Pos);
		// �}�[�J�[��ǉ�
		map.addMarker(markers);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
	}

	@Override
	public void onConnected(Bundle arg0) {
		mLocationClient.requestLocationUpdates(REQUEST, (com.google.android.gms.location.LocationListener) this);
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
	}

}
