package com.kampus.developer.tagihanpln;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kampus.developer.tagihanpln.model.DataItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edtIdPel)
    EditText edtIdPel;
    @BindView(R.id.spnBulan)
    Spinner spnBulan;
    @BindView(R.id.spnTahun)
    Spinner spnTahun;
    @BindView(R.id.tvCek)
    TextView tvCek;
    @BindView(R.id.tvKosong)
    TextView tvKosong;
    @BindView(R.id.tvIdPel)
    TextView tvIdPel;
    @BindView(R.id.tvNamaPel)
    TextView tvNamaPel;
    @BindView(R.id.tvDiskon)
    TextView tvDiskon;
    @BindView(R.id.tvBeban)
    TextView tvBeban;
    @BindView(R.id.tvNamaThb)
    TextView tvNamaThb;
    @BindView(R.id.tvTerbilang)
    TextView tvTerbilang;
    @BindView(R.id.tvTagihan)
    TextView tvTagihan;
    @BindView(R.id.tvTahun)
    TextView tvTahun;
    @BindView(R.id.tvBulan)
    TextView tvBulan;


    private ProgressDialog progressDialog;
    // HTTP Client
    AsyncHttpClient client;
    private String[] bulanList;
    private String[] tahunList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        listBulan();
        listTahun();
        // Init HTTP Client
        client = new AsyncHttpClient();

        tvCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Memuat data");
                progressDialog.setMessage("Harap Tunggu...");
                progressDialog.show();

                String idPel= edtIdPel.getText().toString();
                String bulan=tvBulan.getText().toString();
                String tahun =tvTahun.getText().toString();

                if (edtIdPel.equals("")&& edtIdPel.length()<10){
                    Toast.makeText(MainActivity.this,"ID Pelanggan harus diisi",Toast.LENGTH_LONG).show();
                }

                else {
                    // Make Api Request
                    // GET http://ibacor.com/api/tagihan-pln?idp=...&thn=...&bln=..
                    String apiUrl = "http://ibacor.com/api/tagihan-pln?idp=" + idPel + "&thn=" + tahun + "&bln=" + bulan;
                    client.get(apiUrl, new JsonHttpResponseHandler() {

                        @Override
                        public void onStart() {
                            // Change Content Title to Loading... when process start
                            // judulHasil.setText(R.string.lading);
                            progressDialog.setMessage("Mohon tunggu...");
                            progressDialog.show();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d("onSuccess", "onSuccess: " + response.toString());

                            try {
                                String status = response.getString("status");
                                if (status.equals("success")) {
                                    Gson gson = new GsonBuilder().create();
                                    DataItem data = gson.fromJson(response.getJSONObject("data").toString(), DataItem.class);


                                    tvIdPel.setText("ID Pelanggan   : " + data.getIdpel());
                                    tvNamaPel.setText("Nama           : " + data.getNama());
                                    tvDiskon.setText("Diskon         : " + data.getDiskon());
                                    tvBeban.setText("Beban          : " + data.getBeban());
                                    tvNamaThb.setText("Periode        : " + data.getNamathblrek());
                                    tvTerbilang.setText("Terbilang      : " + data.getTerbilang());
                                    tvTagihan.setText("Tagihan        : Rp. " + data.getTagihan());

                                    Log.d("DataDebug", "Data : " + data.getTerbilang());
                                } else {
                                    Toast.makeText(MainActivity.this, "Periksa koneksi internet anda atau data yang anda masukan salah",
                                            Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            progressDialog.hide();
                        }


                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            Log.d("onFailure", "Request Gagal " + throwable.getMessage());
                            progressDialog.hide();
                        }
                    });
                }
            }
        });
    }

    public void listBulan(){
        this.bulanList = new String[]{ "Januari", "Februari","Maret",
                "April","Mei", "Juni","Juli", "Agustus",
                "September", "Oktober","November", "Desember"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,bulanList);
        spnBulan.setAdapter(adapter);

        spnBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String  mselection=spnBulan.getSelectedItem().toString();
                if (mselection.equals("Januari"))
                { tvBulan.setText("01"); }
                else if (mselection.equals("Februari"))
                { tvBulan.setText("02"); }
                else if (mselection.equals("Maret"))
                { tvBulan.setText("03"); }
                else if (mselection.equals("April"))
                { tvBulan.setText("04"); }
                else if (mselection.equals("Mei"))
                { tvBulan.setText("05"); }
                else if (mselection.equals("Juni"))
                { tvBulan.setText("06"); }
                else if (mselection.equals("Juli"))
                { tvBulan.setText("07"); }
                else if (mselection.equals("Agustus"))
                { tvBulan.setText("08"); }
                else if (mselection.equals("September"))
                { tvBulan.setText("09"); }
                else if (mselection.equals("Oktober"))
                { tvBulan.setText("10"); }
                else if (mselection.equals("November"))
                { tvBulan.setText("11"); }
                else if (mselection.equals("Desember"))
                { tvBulan.setText("12"); }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void listTahun(){
        this.tahunList = new String[]{ "2016", "2015"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,tahunList);
        spnTahun.setAdapter(adapter);

        spnTahun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String  mselection=spnTahun.getSelectedItem().toString();
                if (mselection.equals("2015"))
                { tvTahun.setText("2015"); }
                else if (mselection.equals("2016"))
                { tvTahun.setText("2016"); }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
