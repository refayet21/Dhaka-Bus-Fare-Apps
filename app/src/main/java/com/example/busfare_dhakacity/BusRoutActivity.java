package com.example.busfare_dhakacity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusRoutActivity extends AppCompatActivity {

    private TextView busNameText, busRoutText;
    private ListView listView;
    String busName;
    String[] bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_rout);
        getSupportActionBar().hide();

        listView = findViewById(R.id.bus_details_route);
        busNameText = findViewById(R.id.busNameId);
        busRoutText = findViewById(R.id.busRoutDestinationId);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            busName = extras.getString("key");
            busNameText.setText(busName);
            busRoutText.setText(busRoutDestination(busName));
        }

        ArrayAdapter<String> mHistory = new ArrayAdapter<String>(this, R.layout.bus_details_list, R.id.rout_name_id, busRout(busName));
        listView.setAdapter(mHistory);


    }

    public List<String> busRout(String busName) {

        String[] Rojonigondha = getResources().getStringArray(R.array.Rojonigondha);
        String[] City_Link = getResources().getStringArray(R.array.City_Link);
        String[] Sadhin = getResources().getStringArray(R.array.Sadhin);
        String[] Torongo_plus = getResources().getStringArray(R.array.Torongo_plus);
        String[] Romjan = getResources().getStringArray(R.array.Romjan);
        String[] Projapati = getResources().getStringArray(R.array.Projapati);
        String[] Paristhan = getResources().getStringArray(R.array.Paristhan);

        String[] Achim_Paribahan = getResources().getStringArray(R.array.Achim_Paribahan);
        String[] Ajmi = getResources().getStringArray(R.array.Ajmi);
        String[] Akik = getResources().getStringArray(R.array.Akik);
        String[] Alif_Bus = getResources().getStringArray(R.array.Alif_Bus);
        String[] Arnob_Bus = getResources().getStringArray(R.array.Arnob_Bus);
        String[] Active_Paribahan = getResources().getStringArray(R.array.Active_Paribahan);
        String[] Agradut = getResources().getStringArray(R.array.Agradut);
        String[] Airport_Bangabandhu_Avenue = getResources().getStringArray(R.array.Airport_Bangabandhu_Avenue);
        String[] Akash = getResources().getStringArray(R.array.Akash);
        String[] Al_Madina_Plus = getResources().getStringArray(R.array.Al_Madina_Plus);
        String[] Al_Makka = getResources().getStringArray(R.array.Al_Makka);
        String[] Anabil_Super = getResources().getStringArray(R.array.Anabil_Super);
        String[] Ashirbad_Pahibahan = getResources().getStringArray(R.array.Ashirbad_Pahibahan);
        String[] Ashulia_Classic = getResources().getStringArray(R.array.Ashulia_Classic);
        String[] Asmani = getResources().getStringArray(R.array.Asmani);
        String[] Ayat = getResources().getStringArray(R.array.Ayat);
        String[] Azmeri_Glory = getResources().getStringArray(R.array.Azmeri_Glory);
        String[] Bahon = getResources().getStringArray(R.array.Bahon);
        String[] Baishakhi = getResources().getStringArray(R.array.Baishakhi);
        String[] Balaka = getResources().getStringArray(R.array.Balaka);
        String[] Basumati_Transport = getResources().getStringArray(R.array.Basumati_Transport);
        String[] Basumati = getResources().getStringArray(R.array.Basumati);

        List<String> rout = new ArrayList();
        if (busName.equalsIgnoreCase("Rojonigondha")) {
            bus = Rojonigondha;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("City Link")) {
            bus = City_Link;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Sadhin")) {
            bus = Sadhin;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Torongo plus")) {
            bus = Torongo_plus;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Romjan")) {
            bus = Romjan;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Projapati")) {
            bus = Projapati;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Paristhan")) {
            bus = Paristhan;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }

        if (busName.equalsIgnoreCase("Alif")) {
            bus = Alif_Bus;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Arnob")) {
            bus = Arnob_Bus;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Agradut")) {
            bus = Agradut;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Ajmi")) {
            bus = Ajmi;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Akik")) {
            bus = Akik;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Achim Paribahan")) {
            bus = Achim_Paribahan;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Active Paribahan")) {
            bus = Active_Paribahan;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Airport Bangabandhu Avenue")) {
            bus = Airport_Bangabandhu_Avenue;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Akash")) {
            bus = Akash;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Al Madina Plus")) {
            bus = Al_Madina_Plus;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Al Makka")) {
            bus = Al_Makka;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Anabil Super")) {
            bus = Anabil_Super;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Ashirbad Pahibahan")) {
            bus = Ashirbad_Pahibahan;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Ashulia Classic")) {
            bus = Ashulia_Classic;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Asmani")) {
            bus = Asmani;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Ayat")) {
            bus = Ayat;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Azmeri Glory")) {
            bus = Azmeri_Glory;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Bahon")) {
            bus = Bahon;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Baishakhi")) {
            bus = Baishakhi;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Balaka")) {
            bus = Balaka;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Basumati")) {
            bus = Basumati;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }
        if (busName.equalsIgnoreCase("Basumati Transport")) {
            bus = Basumati_Transport;
            for (int i = 0; i < bus.length; i++) {
                rout.add(bus[i]);
            }
        }

        return rout;
    }


    public String busRoutDestination(String busName) {
        String from = null;
        String to = null;

        String[] Rojonigondha = getResources().getStringArray(R.array.Rojonigondha);
        String[] City_Link = getResources().getStringArray(R.array.City_Link);
        String[] Sadhin = getResources().getStringArray(R.array.Sadhin);
        String[] Torongo_plus = getResources().getStringArray(R.array.Torongo_plus);
        String[] Romjan = getResources().getStringArray(R.array.Romjan);
        String[] Projapati = getResources().getStringArray(R.array.Projapati);
        String[] Paristhan = getResources().getStringArray(R.array.Paristhan);

        String[] Achim_Paribahan = getResources().getStringArray(R.array.Achim_Paribahan);
        String[] Ajmi = getResources().getStringArray(R.array.Ajmi);
        String[] Akik = getResources().getStringArray(R.array.Akik);
        String[] Alif_Bus = getResources().getStringArray(R.array.Alif_Bus);
        String[] Arnob_Bus = getResources().getStringArray(R.array.Arnob_Bus);
        String[] Active_Paribahan = getResources().getStringArray(R.array.Active_Paribahan);
        String[] Agradut = getResources().getStringArray(R.array.Agradut);
        String[] Airport_Bangabandhu_Avenue = getResources().getStringArray(R.array.Airport_Bangabandhu_Avenue);
        String[] Akash = getResources().getStringArray(R.array.Akash);
        String[] Al_Madina_Plus = getResources().getStringArray(R.array.Al_Madina_Plus);
        String[] Al_Makka = getResources().getStringArray(R.array.Al_Makka);
        String[] Anabil_Super = getResources().getStringArray(R.array.Anabil_Super);
        String[] Ashirbad_Pahibahan = getResources().getStringArray(R.array.Ashirbad_Pahibahan);
        String[] Ashulia_Classic = getResources().getStringArray(R.array.Ashulia_Classic);
        String[] Asmani = getResources().getStringArray(R.array.Asmani);
        String[] Ayat = getResources().getStringArray(R.array.Ayat);
        String[] Azmeri_Glory = getResources().getStringArray(R.array.Azmeri_Glory);
        String[] Bahon = getResources().getStringArray(R.array.Bahon);
        String[] Baishakhi = getResources().getStringArray(R.array.Baishakhi);
        String[] Balaka = getResources().getStringArray(R.array.Balaka);
        String[] Basumati_Transport = getResources().getStringArray(R.array.Basumati_Transport);
        String[] Basumati = getResources().getStringArray(R.array.Basumati);

        if (busName.equalsIgnoreCase("Rojonigondha")) {
            bus = Rojonigondha;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("City Link")) {
            bus = City_Link;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Sadhin")) {
            bus = Sadhin;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Torongo plus")) {
            bus = Torongo_plus;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Romjan")) {
            bus = Romjan;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Projapati")) {
            bus = Projapati;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Paristhan")) {
            bus = Paristhan;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }

        if (busName.equalsIgnoreCase("Alif")) {
            bus = Alif_Bus;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Arnob")) {
            bus = Arnob_Bus;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Agradut")) {
            bus = Agradut;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Ajmi")) {
            bus = Ajmi;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Akik")) {
            bus = Akik;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Achim Paribahan")) {
            bus = Achim_Paribahan;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Active Paribahan")) {
            bus = Active_Paribahan;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Airport Bangabandhu Avenue")) {
            bus = Airport_Bangabandhu_Avenue;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Akash")) {
            bus = Akash;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Al Madina Plus")) {
            bus = Al_Madina_Plus;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Al Makka")) {
            bus = Al_Makka;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Anabil Super")) {
            bus = Anabil_Super;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Ashirbad Pahibahan")) {
            bus = Ashirbad_Pahibahan;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Ashulia Classic")) {
            bus = Ashulia_Classic;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Asmani")) {
            bus = Asmani;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Ayat")) {
            bus = Ayat;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Azmeri Glory")) {
            bus = Azmeri_Glory;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Bahon")) {
            bus = Bahon;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Baishakhi")) {
            bus = Baishakhi;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Balaka")) {
            bus = Balaka;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Basumati")) {
            bus = Basumati;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }
        if (busName.equalsIgnoreCase("Basumati Transport")) {
            bus = Basumati_Transport;
            for (int i = 0; i < bus.length; i++) {
                from = bus[0];
                to = bus[bus.length - 1];
            }
        }

        return from + " - " + to;
    }
}