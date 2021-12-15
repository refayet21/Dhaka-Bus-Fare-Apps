package com.example.busfare_dhakacity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView fromautoCompleteTextView, toautoCompleteTextView;
    private TextInputLayout textInputLayout1, textInputLayout2;
    private TextView searchTextView, distanceView, busFareView;
    private ListView listView;
    private Button searchButton, allBusButton;
    String[] busDistanceName;
    String from, to;
    String[] bus;
    int routNo = 0;
    List<String> busList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        busDistanceName = getResources().getStringArray(R.array.distance_name);
        fromautoCompleteTextView = findViewById(R.id.fromAutoId);
        toautoCompleteTextView = findViewById(R.id.toAutoId);

        textInputLayout1 = findViewById(R.id.fromId);
        textInputLayout2 = findViewById(R.id.toId);
        searchTextView = findViewById(R.id.searchTextId);
        distanceView = findViewById(R.id.distanceId);
        busFareView = findViewById(R.id.busFareId);
        listView = findViewById(R.id.listViewId);

        searchButton = findViewById(R.id.buttonId);
        allBusButton = findViewById(R.id.allBusId);

        searchButton.setOnClickListener(this);
        allBusButton.setOnClickListener(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, busDistanceName);
        fromautoCompleteTextView.setThreshold(1);
        toautoCompleteTextView.setThreshold(1);

        fromautoCompleteTextView.setAdapter(adapter);
        toautoCompleteTextView.setAdapter(adapter);


        ArrayAdapter<String> mHistory = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, allBuses());
        listView.setAdapter(mHistory);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = (String) (listView.getItemAtPosition(position));
                // Toast.makeText(getApplicationContext(), selectedFromList, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(view.getContext(), BusRoutActivity.class);
                intent.putExtra("key", selectedFromList);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buttonId) {

            from = textInputLayout1.getEditText().getText().toString();
            to = textInputLayout2.getEditText().getText().toString();
            String searchText = from + " - " + to;
            searchTextView.setText(searchText);

            busList = bus(from, to);
            for (int i = 0; i < busList.size(); i++) {

                switch (busList.get(i)) {
                    case "Rojonigondha":
                        routNo = 1;
                        break;
                    case "Sadhin":
                        routNo = 2;
                        break;
//                    case "Torongo Plus":
//                        routNo = 3;
//                        break;
                    case "Projapati":
                        routNo = 4;
                        break;
//                    case "Achim Paribahan":
//                        routNo = 5;
//                        break;
//                    case "Active Paribahan":
//                        routNo = 6;
//                        break;
//                    case "Agradut":
//                        routNo = 7;
//                        break;
//                    case "Airport Bangabandhu Avenue":
//                        routNo = 8;
//                        break;
//                    case "Azmeri Glory":
//                        routNo = 9;
//                        break;
//                    case "Ajmi":
//                        routNo = 10;
//                        break;
//                    case "Akash":
//                        routNo = 11;
//                        break;
//                    case "Akik":
//                        routNo = 12;
//                        break;
//                    case "Al Makka":
//                        routNo = 13;
//                        break;
//                    case "Al Madina Plus":
//                        routNo = 14;
//                        break;
//                    case "Alif":
//                        routNo = 15;
//                        break;
//                    case "Anabil Super":
//                        routNo = 16;
//                        break;
//                    case "Arnob":
//                        routNo = 17;
//                        break;
//                    case "Ashirbad Pahibahan":
//                        routNo = 18;
//                        break;
//                    case "Ashulia Classic":
//                        routNo = 19;
//                        break;
//                    case "Asmani":
//                        routNo = 20;
//                        break;
//                    case "Ayat":
//                        routNo = 21;
//                        break;
//                    case "Bahon":
//                        routNo = 22;
//                        break;
//                    case "Baishakhi":
//                        routNo = 23;
//                        break;
//                    case "Balaka":
//                        routNo = 24;
//                        break;
//                    case "Basumati":
//                        routNo = 25;
//                        break;
//                    case "Basumati Transport":
//                        routNo = 26;
//                        break;

                }

            }

            String distance = dis(from, to);
            distanceView.setText("Distance: " + distance + " KM");

            int busFare = busRent(from, to);
            busFareView.setText("Bus Fare: " + busFare + " TK");


            ArrayAdapter<String> mHistory = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, busList);
            listView.setAdapter(mHistory);


            try {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                // TODO: handle exception
            }

            // Toast.makeText(getApplicationContext(), searchText, Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.allBusId) {
            ArrayAdapter<String> mHistory = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, allBuses());
            listView.setAdapter(mHistory);
            searchTextView.setText("");
            distanceView.setText("");
            busFareView.setText("");
        }

    }


    public List<String> bus(String from, String to) {

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

        List<String> elementList = new ArrayList();
        boolean fcheck;
        boolean tcheck;

        //bus
        fcheck = false;
        tcheck = false;
        bus = Rojonigondha;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Rojonigondha");
        }

        //bus
        fcheck = false;
        tcheck = false;
        bus = City_Link;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("City Link");
        }

        //bus
        fcheck = false;
        tcheck = false;
        bus = Sadhin;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Sadhin");
        }

        //bus
        fcheck = false;
        tcheck = false;
        bus = Torongo_plus;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Torongo Plus");
        }

        //bus
        fcheck = false;
        tcheck = false;
        bus = Romjan;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Romjan");
        }

        //bus
        fcheck = false;
        tcheck = false;
        bus = Projapati;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Projapati");
        }

        //bus
        fcheck = false;
        tcheck = false;
        bus = Paristhan;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Paristhan");
        }


        //bus
        fcheck = false;
        tcheck = false;
        bus = Alif_Bus;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Alif");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Arnob_Bus;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Arnob");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Akik;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Akik");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Akash;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Akash");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Asmani;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Asmani");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Achim_Paribahan;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Achim Paribahan");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Active_Paribahan;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Active Paribahan");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Agradut;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Agradut");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Airport_Bangabandhu_Avenue;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Airport Bangabandhu Avenue");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Ajmi;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Ajmi");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Al_Madina_Plus;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Al Madina Plus");
        }//bus
        fcheck = false;
        tcheck = false;
        bus = Al_Makka;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Al Makka");
        }//bus
        fcheck = false;
        tcheck = false;
        bus = Anabil_Super;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Anabil Super");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Ashirbad_Pahibahan;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Ashirbad Pahibahan");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Ashulia_Classic;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Ashulia Classic");
        }//bus
        fcheck = false;
        tcheck = false;
        bus = Ayat;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Ayat");
        }//bus
        fcheck = false;
        tcheck = false;
        bus = Azmeri_Glory;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Azmeri Glory");
        }//bus
        fcheck = false;
        tcheck = false;
        bus = Bahon;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Bahon");
        }//bus
        fcheck = false;
        tcheck = false;
        bus = Baishakhi;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Baishakhi");
        }
        //bus
        fcheck = false;
        tcheck = false;
        bus = Balaka;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Balaka");
        }//bus
        fcheck = false;
        tcheck = false;
        bus = Basumati;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Basumati");
        }//bus
        fcheck = false;
        tcheck = false;
        bus = Basumati_Transport;
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].equalsIgnoreCase(from)) {
                fcheck = true;
            } else if (bus[i].equalsIgnoreCase(to)) {
                tcheck = true;
            }
        }
        if (fcheck && tcheck) {
            elementList.add("Basumati Transport");
        }


        return elementList;

    }


    // bus fare calculation

    public int busRent(String a, String b) {
        double from = 0.0;
        double to = 0.0;
        double distance;

        //busRoutNo-1 Rojonigondha
        if (routNo == 1) {
            if ("Chittagong Road".equalsIgnoreCase(a)) {
                from = 0.0;
            }
            if ("Sign Board".equalsIgnoreCase(a)) {
                from = 2.9;
            }
            if ("Matuail".equalsIgnoreCase(a)) {
                from = 4.4;
            }
            if ("Rayerbag".equalsIgnoreCase(a)) {
                from = 5.5;
            }
            if ("Shonir Akhra".equalsIgnoreCase(a)) {
                from = 6.3;
            }
            if ("Jatrabari".equalsIgnoreCase(a)) {
                from = 8.1;
            }
            if ("Sayedabad".equalsIgnoreCase(a)) {
                from = 9.3;
            }
            if ("Gulistan".equalsIgnoreCase(a)) {
                from = 11.2;
            }
            if ("GPO".equalsIgnoreCase(a)) {
                from = 11.9;
            }
            if ("Paltan".equalsIgnoreCase(a)) {
                from = 12.2;
            }
            if ("Press Club".equalsIgnoreCase(a)) {
                from = 12.2;
            }
            if ("High Court".equalsIgnoreCase(a)) {
                from = 13.1;
            }
            if ("Shahbag".equalsIgnoreCase(a)) {
                from = 14.0;
            }
            if ("Bata Signal".equalsIgnoreCase(a)) {
                from = 14.6;
            }
            if ("Science Lab".equalsIgnoreCase(a)) {
                from = 15.2;
            }
            if ("City College".equalsIgnoreCase(a)) {
                from = 15.5;
            }
            if ("Jigatola".equalsIgnoreCase(a)) {
                from = 16.4;
            }
            if ("Dhanmondi 15".equalsIgnoreCase(a)) {
                from = 17.0;
            }
            if ("Star Kabab".equalsIgnoreCase(a)) {
                from = 17.4;
            }
            if ("Shankar".equalsIgnoreCase(a)) {
                from = 17.9;
            }
            if ("Mohammadpur".equalsIgnoreCase(a)) {
                from = 19.0;
            }
            if ("Bosila".equalsIgnoreCase(a)) {
                from = 21.7;
            }
            if ("Washpur".equalsIgnoreCase(a)) {
                from = 23.6;
            }
            if ("Arshinagar".equalsIgnoreCase(a)) {
                from = 24.1;
            }
            if ("Ghatar Char".equalsIgnoreCase(a)) {
                from = 26.0;
            }

            if ("Chittagong Road".equalsIgnoreCase(b)) {
                to = 0.0;
            }
            if ("Sign Board".equalsIgnoreCase(b)) {
                to = 2.9;
            }
            if ("Matuail".equalsIgnoreCase(b)) {
                to = 4.4;
            }
            if ("Rayerbag".equalsIgnoreCase(b)) {
                to = 5.5;
            }
            if ("Shonir Akhra".equalsIgnoreCase(b)) {
                to = 6.3;
            }
            if ("Jatrabari".equalsIgnoreCase(b)) {
                to = 8.1;
            }
            if ("Sayedabad".equalsIgnoreCase(b)) {
                to = 9.3;
            }
            if ("Gulistan".equalsIgnoreCase(b)) {
                to = 11.2;
            }
            if ("GPO".equalsIgnoreCase(b)) {
                to = 11.9;
            }
            if ("Paltan".equalsIgnoreCase(b)) {
                to = 12.2;
            }
            if ("Press Club".equalsIgnoreCase(b)) {
                to = 12.2;
            }
            if ("High Court".equalsIgnoreCase(b)) {
                to = 13.1;
            }
            if ("Shahbag".equalsIgnoreCase(b)) {
                to = 14.0;
            }
            if ("Bata Signal".equalsIgnoreCase(b)) {
                to = 14.6;
            }
            if ("Science Lab".equalsIgnoreCase(b)) {
                to = 15.2;
            }
            if ("City College".equalsIgnoreCase(b)) {
                to = 15.5;
            }
            if ("Jigatola".equalsIgnoreCase(b)) {
                to = 16.4;
            }
            if ("Dhanmondi 15".equalsIgnoreCase(b)) {
                to = 17.0;
            }
            if ("Star Kabab".equalsIgnoreCase(b)) {
                to = 17.4;
            }
            if ("Shankar".equalsIgnoreCase(b)) {
                to = 17.9;
            }
            if ("Mohammadpur".equalsIgnoreCase(b)) {
                to = 19.0;
            }
            if ("Bosila".equalsIgnoreCase(b)) {
                to = 21.7;
            }
            if ("Washpur".equalsIgnoreCase(b)) {
                to = 23.6;
            }
            if ("Arshinagar".equalsIgnoreCase(b)) {
                to = 24.1;
            }
            if ("Ghatar Char".equalsIgnoreCase(b)) {
                to = 26.0;
            }
        }

        //busRoutNo-2 Sadhin
        if (routNo == 2) {
            // from
            if ("Bosila".equalsIgnoreCase(a)) {
                from = 0.0;
            }
            if ("Mohammadpur".equalsIgnoreCase(a)) {
                from = 3.0;
            }
            if ("Asad Gate".equalsIgnoreCase(a)) {
                from = 4.3;
            }
            if ("Khamar Bari".equalsIgnoreCase(a)) {
                from = 5.5;
            }
            if ("Farmgate".equalsIgnoreCase(a)) {
                from = 6.0;
            }
            if ("Kawran Bazar".equalsIgnoreCase(a)) {
                from = 7.5;
            }
            if ("Bangla Motor".equalsIgnoreCase(a)) {
                from = 8.0;
            }
            if ("Mogbazar".equalsIgnoreCase(a)) {
                from = 9.2;
            }
            if ("Mouchak".equalsIgnoreCase(a)) {
                from = 10.2;
            }
            if ("Malibagh Railgate".equalsIgnoreCase(a)) {
                from = 11.0;
            }
            if ("Hazipara".equalsIgnoreCase(a)) {
                from = 11.7;
            }
            if ("Rampura Bazar".equalsIgnoreCase(a)) {
                from = 12.7;
            }
            if ("Rampura Bridge".equalsIgnoreCase(a)) {
                from = 13.2;
            }
            if ("Banasree".equalsIgnoreCase(a)) {
                from = 15.5;
            }
            if ("Demra Staff Quarter".equalsIgnoreCase(a)) {
                from = 23.5;
            }
            // to
            if ("Bosila".equalsIgnoreCase(b)) {
                to = 0.0;
            }
            if ("Mohammadpur".equalsIgnoreCase(b)) {
                to = 3.0;
            }
            if ("Asad Gate".equalsIgnoreCase(b)) {
                to = 4.3;
            }
            if ("Khamar Bari".equalsIgnoreCase(b)) {
                to = 5.5;
            }
            if ("Farmgate".equalsIgnoreCase(b)) {
                to = 6.0;
            }
            if ("Kawran Bazar".equalsIgnoreCase(b)) {
                to = 7.5;
            }
            if ("Bangla Motor".equalsIgnoreCase(b)) {
                to = 8.0;
            }
            if ("Mogbazar".equalsIgnoreCase(b)) {
                to = 9.2;
            }
            if ("Mouchak".equalsIgnoreCase(b)) {
                to = 10.2;
            }
            if ("Malibagh Railgate".equalsIgnoreCase(b)) {
                to = 11.0;
            }
            if ("Hazipara".equalsIgnoreCase(b)) {
                to = 11.7;
            }
            if ("Rampura Bazar".equalsIgnoreCase(b)) {
                to = 12.7;
            }
            if ("Rampura Bridge".equalsIgnoreCase(b)) {
                to = 13.2;
            }
            if ("Banasree".equalsIgnoreCase(b)) {
                to = 15.5;
            }
            if ("Demra Staff Quarter".equalsIgnoreCase(b)) {
                to = 23.5;
            }

        }
//        //busRoutNo-3 Torongo Plus
//        if (routNo == 3) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
        //busRoutNo-4 Projapoti
        if (routNo == 4) {
            // from
            if ("Ghatar Char".equalsIgnoreCase(a)) {
                from = 0.0;
            }
            if ("Arshinagar".equalsIgnoreCase(a)) {
                from = 1.9;
            }
            if ("Washpur".equalsIgnoreCase(a)) {
                from = 2.4;
            }
            if ("Bosila".equalsIgnoreCase(a)) {
                from = 4.3;
            }
            if ("Mohammadpur".equalsIgnoreCase(a)) {
                from = 7.0;
            }
            if ("Asad Gate".equalsIgnoreCase(a)) {
                from = 8.0;
            }
            if ("College Gate".equalsIgnoreCase(a)) {
                from = 8.6;
            }
            if ("Shyamoli".equalsIgnoreCase(a)) {
                from = 9.8;
            }
            if ("Kallyanpur".equalsIgnoreCase(a)) {
                from = 10.13;
            }
            if ("Darussalam".equalsIgnoreCase(a)) {
                from = 10.50;
            }
            if ("Technical".equalsIgnoreCase(a)) {
                from = 11.05;
            }
            if ("Bangla College".equalsIgnoreCase(a)) {
                from = 11.30;
            }
            if ("Ansar Camp".equalsIgnoreCase(a)) {
                from = 12.0;
            }
            if ("Mirpur 1".equalsIgnoreCase(a)) {
                from = 13.50;
            }
            if ("Mirpur 2".equalsIgnoreCase(a)) {
                from = 14.80;
            }
            if ("Mirpur 10".equalsIgnoreCase(a)) {
                from = 15.30;
            }
            if ("Mirpur 11".equalsIgnoreCase(a)) {
                from = 16.30;
            }
            if ("Purobi".equalsIgnoreCase(a)) {
                from = 16.80;
            }
            if ("Kalshi".equalsIgnoreCase(a)) {
                from = 20.0;
            }
            if ("ECB Square".equalsIgnoreCase(a)) {
                from = 22.0;
            }
            if ("MES".equalsIgnoreCase(a)) {
                from = 24.0;
            }
            if ("Shewra".equalsIgnoreCase(a)) {
                from = 24.40;
            }
            if ("Kuril Bishwa Road".equalsIgnoreCase(a)) {
                from = 25.0;
            }
            if ("Khilkhet".equalsIgnoreCase(a)) {
                from = 26.0;
            }
            if ("Airport".equalsIgnoreCase(a)) {
                from = 28.4;
            }
            if ("Jashimuddin".equalsIgnoreCase(a)) {
                from = 29.6;
            }
            if ("Rajlakshmi".equalsIgnoreCase(a)) {
                from = 29.9;
            }
            if ("House Building".equalsIgnoreCase(a)) {
                from = 30.2;
            }
            if ("Abdullahpur".equalsIgnoreCase(a)) {
                from = 32.1;
            }
            if ("Kamarpara".equalsIgnoreCase(a)) {
                from = 34.2;
            }

            // to
            if ("Ghatar Char".equalsIgnoreCase(b)) {
                to = 0.0;
            }
            if ("Arshinagar".equalsIgnoreCase(b)) {
                to = 1.9;
            }
            if ("Washpur".equalsIgnoreCase(b)) {
                to = 2.4;
            }
            if ("Bosila".equalsIgnoreCase(b)) {
                to = 4.3;
            }
            if ("Mohammadpur".equalsIgnoreCase(b)) {
                to = 7.0;
            }
            if ("Asad Gate".equalsIgnoreCase(b)) {
                to = 8.0;
            }
            if ("College Gate".equalsIgnoreCase(b)) {
                to = 8.6;
            }
            if ("Shyamoli".equalsIgnoreCase(b)) {
                to = 9.8;
            }
            if ("Kallyanpur".equalsIgnoreCase(b)) {
                to = 10.13;
            }
            if ("Darussalam".equalsIgnoreCase(b)) {
                to = 10.50;
            }
            if ("Technical".equalsIgnoreCase(b)) {
                to = 11.05;
            }
            if ("Bangla College".equalsIgnoreCase(b)) {
                to = 11.30;
            }
            if ("Ansar Camp".equalsIgnoreCase(b)) {
                to = 12.0;
            }
            if ("Mirpur 1".equalsIgnoreCase(b)) {
                to = 13.50;
            }
            if ("Mirpur 2".equalsIgnoreCase(b)) {
                to = 14.80;
            }
            if ("Mirpur 10".equalsIgnoreCase(b)) {
                to = 15.30;
            }
            if ("Mirpur 11".equalsIgnoreCase(b)) {
                to = 16.30;
            }
            if ("Purobi".equalsIgnoreCase(b)) {
                to = 16.80;
            }
            if ("Kalshi".equalsIgnoreCase(b)) {
                to = 20.0;
            }
            if ("ECB Square".equalsIgnoreCase(b)) {
                to = 22.0;
            }
            if ("MES".equalsIgnoreCase(b)) {
                to = 24.0;
            }
            if ("Shewra".equalsIgnoreCase(b)) {
                to = 24.40;
            }
            if ("Kuril Bishwa Road".equalsIgnoreCase(b)) {
                to = 25.0;
            }
            if ("Khilkhet".equalsIgnoreCase(b)) {
                to = 26.0;
            }
            if ("Airport".equalsIgnoreCase(b)) {
                to = 28.4;
            }
            if ("Jashimuddin".equalsIgnoreCase(b)) {
                to = 29.6;
            }
            if ("Rajlakshmi".equalsIgnoreCase(b)) {
                to = 29.9;
            }
            if ("House Building".equalsIgnoreCase(b)) {
                to = 30.2;
            }
            if ("Abdullahpur".equalsIgnoreCase(b)) {
                to = 32.1;
            }
            if ("Kamarpara".equalsIgnoreCase(b)) {
                to = 34.2;
            }

        }
//        //busRoutNo-5 Achim Paribahan
//        if (routNo == 5) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-6 Active Paribahan
//        if (routNo == 6) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-7 Agradut
//        if (routNo == 7) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-8 Airport Bangabandhu Avenue
//        if (routNo == 8) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-9 Azmeri Glory
//        if (routNo == 9) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-10 Ajmi
//        if (routNo == 10) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-11 Akash
//        if (routNo == 11) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-12 Akik
//        if (routNo == 12) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-13 Al Makka
//        if (routNo == 13) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-14 Al Madina Plus
//        if (routNo == 14) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-15 Alif
//        if (routNo == 15) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-16 Anabil Super
//        if (routNo == 16) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-17 Arnob
//        if (routNo == 17) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-18 Ashirbad Pahibahan
//        if (routNo == 18) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-19 Ashulia Classic
//        if (routNo == 19) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-20 Asmani
//        if (routNo == 20) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-21 Ayat
//        if (routNo == 21) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-22 Bahon
//        if (routNo == 22) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-23 Baishakhi
//        if (routNo == 23) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-24 Balaka
//        if (routNo == 24) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-25 Basumati
//        if (routNo == 25) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo-26 Basumati Transport
//        if (routNo == 26) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
//        //busRoutNo


        int rent;

        if (from > to) {
            distance = from - to;
        } else {
            distance = to - from;
        }

        rent = (int) Math.round(distance * 2.15);
        if (rent < 10) {
            rent = 10;
        }
        if (distance == 0.0) {
            rent = 0;
        }
        return rent;
    }


    public String dis(String a, String b) {
        double from = 0.0;
        double to = 0.0;
        double distance;
        //busRoutNo
        if (routNo == 1) {
            if ("Chittagong Road".equalsIgnoreCase(a)) {
                from = 0.0;
            }
            if ("Sign Board".equalsIgnoreCase(a)) {
                from = 2.9;
            }
            if ("Matuail".equalsIgnoreCase(a)) {
                from = 4.4;
            }
            if ("Rayerbag".equalsIgnoreCase(a)) {
                from = 5.5;
            }
            if ("Shonir Akhra".equalsIgnoreCase(a)) {
                from = 6.3;
            }
            if ("Jatrabari".equalsIgnoreCase(a)) {
                from = 8.1;
            }
            if ("Sayedabad".equalsIgnoreCase(a)) {
                from = 9.3;
            }
            if ("Gulistan".equalsIgnoreCase(a)) {
                from = 11.2;
            }
            if ("GPO".equalsIgnoreCase(a)) {
                from = 11.9;
            }
            if ("Paltan".equalsIgnoreCase(a)) {
                from = 12.2;
            }
            if ("Press Club".equalsIgnoreCase(a)) {
                from = 12.2;
            }
            if ("High Court".equalsIgnoreCase(a)) {
                from = 13.1;
            }
            if ("Shahbag".equalsIgnoreCase(a)) {
                from = 14.0;
            }
            if ("Bata Signal".equalsIgnoreCase(a)) {
                from = 14.6;
            }
            if ("Science Lab".equalsIgnoreCase(a)) {
                from = 15.2;
            }
            if ("City College".equalsIgnoreCase(a)) {
                from = 15.5;
            }
            if ("Jigatola".equalsIgnoreCase(a)) {
                from = 16.4;
            }
            if ("Dhanmondi 15".equalsIgnoreCase(a)) {
                from = 17.0;
            }
            if ("Star Kabab".equalsIgnoreCase(a)) {
                from = 17.4;
            }
            if ("Shankar".equalsIgnoreCase(a)) {
                from = 17.9;
            }
            if ("Mohammadpur".equalsIgnoreCase(a)) {
                from = 19.0;
            }
            if ("Bosila".equalsIgnoreCase(a)) {
                from = 21.7;
            }
            if ("Washpur".equalsIgnoreCase(a)) {
                from = 23.6;
            }
            if ("Arshinagar".equalsIgnoreCase(a)) {
                from = 24.1;
            }
            if ("Ghatar Char".equalsIgnoreCase(a)) {
                from = 26.0;
            }

            if ("Chittagong Road".equalsIgnoreCase(b)) {
                to = 0.0;
            }
            if ("Sign Board".equalsIgnoreCase(b)) {
                to = 2.9;
            }
            if ("Matuail".equalsIgnoreCase(b)) {
                to = 4.4;
            }
            if ("Rayerbag".equalsIgnoreCase(b)) {
                to = 5.5;
            }
            if ("Shonir Akhra".equalsIgnoreCase(b)) {
                to = 6.3;
            }
            if ("Jatrabari".equalsIgnoreCase(b)) {
                to = 8.1;
            }
            if ("Sayedabad".equalsIgnoreCase(b)) {
                to = 9.3;
            }
            if ("Gulistan".equalsIgnoreCase(b)) {
                to = 11.2;
            }
            if ("GPO".equalsIgnoreCase(b)) {
                to = 11.9;
            }
            if ("Paltan".equalsIgnoreCase(b)) {
                to = 12.2;
            }
            if ("Press Club".equalsIgnoreCase(b)) {
                to = 12.2;
            }
            if ("High Court".equalsIgnoreCase(b)) {
                to = 13.1;
            }
            if ("Shahbag".equalsIgnoreCase(b)) {
                to = 14.0;
            }
            if ("Bata Signal".equalsIgnoreCase(b)) {
                to = 14.6;
            }
            if ("Science Lab".equalsIgnoreCase(b)) {
                to = 15.2;
            }
            if ("City College".equalsIgnoreCase(b)) {
                to = 15.5;
            }
            if ("Jigatola".equalsIgnoreCase(b)) {
                to = 16.4;
            }
            if ("Dhanmondi 15".equalsIgnoreCase(b)) {
                to = 17.0;
            }
            if ("Star Kabab".equalsIgnoreCase(b)) {
                to = 17.4;
            }
            if ("Shankar".equalsIgnoreCase(b)) {
                to = 17.9;
            }
            if ("Mohammadpur".equalsIgnoreCase(b)) {
                to = 19.0;
            }
            if ("Bosila".equalsIgnoreCase(b)) {
                to = 21.7;
            }
            if ("Washpur".equalsIgnoreCase(b)) {
                to = 23.6;
            }
            if ("Arshinagar".equalsIgnoreCase(b)) {
                to = 24.1;
            }
            if ("Ghatar Char".equalsIgnoreCase(b)) {
                to = 26.0;
            }
        }
        //busRoutNo2
        if (routNo == 2) {
            // from
            if ("Bosila".equalsIgnoreCase(a)) {
                from = 0.0;
            }
            if ("Mohammadpur".equalsIgnoreCase(a)) {
                from = 3.0;
            }
            if ("Asad Gate".equalsIgnoreCase(a)) {
                from = 4.3;
            }
            if ("Khamar Bari".equalsIgnoreCase(a)) {
                from = 5.5;
            }
            if ("Farmgate".equalsIgnoreCase(a)) {
                from = 6.0;
            }
            if ("Kawran Bazar".equalsIgnoreCase(a)) {
                from = 7.5;
            }
            if ("Bangla Motor".equalsIgnoreCase(a)) {
                from = 8.0;
            }
            if ("Mogbazar".equalsIgnoreCase(a)) {
                from = 9.2;
            }
            if ("Mouchak".equalsIgnoreCase(a)) {
                from = 10.2;
            }
            if ("Malibagh Railgate".equalsIgnoreCase(a)) {
                from = 11.0;
            }
            if ("Hazipara".equalsIgnoreCase(a)) {
                from = 11.7;
            }
            if ("Rampura Bazar".equalsIgnoreCase(a)) {
                from = 12.7;
            }
            if ("Rampura Bridge".equalsIgnoreCase(a)) {
                from = 13.2;
            }
            if ("Banasree".equalsIgnoreCase(a)) {
                from = 15.5;
            }
            if ("Demra Staff Quarter".equalsIgnoreCase(a)) {
                from = 23.5;
            }
            // to
            if ("Bosila".equalsIgnoreCase(b)) {
                to = 0.0;
            }
            if ("Mohammadpur".equalsIgnoreCase(b)) {
                to = 3.0;
            }
            if ("Asad Gate".equalsIgnoreCase(b)) {
                to = 4.3;
            }
            if ("Khamar Bari".equalsIgnoreCase(b)) {
                to = 5.5;
            }
            if ("Farmgate".equalsIgnoreCase(b)) {
                to = 6.0;
            }
            if ("Kawran Bazar".equalsIgnoreCase(b)) {
                to = 7.5;
            }
            if ("Bangla Motor".equalsIgnoreCase(b)) {
                to = 8.0;
            }
            if ("Mogbazar".equalsIgnoreCase(b)) {
                to = 9.2;
            }
            if ("Mouchak".equalsIgnoreCase(b)) {
                to = 10.2;
            }
            if ("Malibagh Railgate".equalsIgnoreCase(b)) {
                to = 11.0;
            }
            if ("Hazipara".equalsIgnoreCase(b)) {
                to = 11.7;
            }
            if ("Rampura Bazar".equalsIgnoreCase(b)) {
                to = 12.7;
            }
            if ("Rampura Bridge".equalsIgnoreCase(b)) {
                to = 13.2;
            }
            if ("Banasree".equalsIgnoreCase(b)) {
                to = 15.5;
            }
            if ("Demra Staff Quarter".equalsIgnoreCase(b)) {
                to = 23.5;
            }

        }
//        //busRoutNo3
//        if (routNo == 3) {
//            // from
//            if ("Chittagong Road".equalsIgnoreCase(a)) {
//                from = 0.0;
//            }
//
//
//            // to
//            if ("Chittagong Road".equalsIgnoreCase(b)) {
//                to = 0.0;
//            }
//
//        }
        //busRoutNo4
        if (routNo == 4) {
            // from
            if ("Ghatar Char".equalsIgnoreCase(a)) {
                from = 0.0;
            }
            if ("Arshinagar".equalsIgnoreCase(a)) {
                from = 1.9;
            }
            if ("Washpur".equalsIgnoreCase(a)) {
                from = 2.4;
            }
            if ("Bosila".equalsIgnoreCase(a)) {
                from = 4.3;
            }
            if ("Mohammadpur".equalsIgnoreCase(a)) {
                from = 7.0;
            }
            if ("Asad Gate".equalsIgnoreCase(a)) {
                from = 8.0;
            }
            if ("College Gate".equalsIgnoreCase(a)) {
                from = 8.6;
            }
            if ("Shyamoli".equalsIgnoreCase(a)) {
                from = 9.8;
            }
            if ("Kallyanpur".equalsIgnoreCase(a)) {
                from = 10.13;
            }
            if ("Darussalam".equalsIgnoreCase(a)) {
                from = 10.50;
            }
            if ("Technical".equalsIgnoreCase(a)) {
                from = 11.05;
            }
            if ("Bangla College".equalsIgnoreCase(a)) {
                from = 11.30;
            }
            if ("Ansar Camp".equalsIgnoreCase(a)) {
                from = 12.0;
            }
            if ("Mirpur 1".equalsIgnoreCase(a)) {
                from = 13.50;
            }
            if ("Mirpur 2".equalsIgnoreCase(a)) {
                from = 14.80;
            }
            if ("Mirpur 10".equalsIgnoreCase(a)) {
                from = 15.30;
            }
            if ("Mirpur 11".equalsIgnoreCase(a)) {
                from = 16.30;
            }
            if ("Purobi".equalsIgnoreCase(a)) {
                from = 16.80;
            }
            if ("Kalshi".equalsIgnoreCase(a)) {
                from = 20.0;
            }
            if ("ECB Square".equalsIgnoreCase(a)) {
                from = 22.0;
            }
            if ("MES".equalsIgnoreCase(a)) {
                from = 24.0;
            }
            if ("Shewra".equalsIgnoreCase(a)) {
                from = 24.40;
            }
            if ("Kuril Bishwa Road".equalsIgnoreCase(a)) {
                from = 25.0;
            }
            if ("Khilkhet".equalsIgnoreCase(a)) {
                from = 26.0;
            }
            if ("Airport".equalsIgnoreCase(a)) {
                from = 28.4;
            }
            if ("Jashimuddin".equalsIgnoreCase(a)) {
                from = 29.6;
            }
            if ("Rajlakshmi".equalsIgnoreCase(a)) {
                from = 29.9;
            }
            if ("House Building".equalsIgnoreCase(a)) {
                from = 30.2;
            }
            if ("Abdullahpur".equalsIgnoreCase(a)) {
                from = 32.1;
            }
            if ("Kamarpara".equalsIgnoreCase(a)) {
                from = 34.2;
            }

            // to
            if ("Ghatar Char".equalsIgnoreCase(b)) {
                to = 0.0;
            }
            if ("Arshinagar".equalsIgnoreCase(b)) {
                to = 1.9;
            }
            if ("Washpur".equalsIgnoreCase(b)) {
                to = 2.4;
            }
            if ("Bosila".equalsIgnoreCase(b)) {
                to = 4.3;
            }
            if ("Mohammadpur".equalsIgnoreCase(b)) {
                to = 7.0;
            }
            if ("Asad Gate".equalsIgnoreCase(b)) {
                to = 8.0;
            }
            if ("College Gate".equalsIgnoreCase(b)) {
                to = 8.6;
            }
            if ("Shyamoli".equalsIgnoreCase(b)) {
                to = 9.8;
            }
            if ("Kallyanpur".equalsIgnoreCase(b)) {
                to = 10.13;
            }
            if ("Darussalam".equalsIgnoreCase(b)) {
                to = 10.50;
            }
            if ("Technical".equalsIgnoreCase(b)) {
                to = 11.05;
            }
            if ("Bangla College".equalsIgnoreCase(b)) {
                to = 11.30;
            }
            if ("Ansar Camp".equalsIgnoreCase(b)) {
                to = 12.0;
            }
            if ("Mirpur 1".equalsIgnoreCase(b)) {
                to = 13.50;
            }
            if ("Mirpur 2".equalsIgnoreCase(b)) {
                to = 14.80;
            }
            if ("Mirpur 10".equalsIgnoreCase(b)) {
                to = 15.30;
            }
            if ("Mirpur 11".equalsIgnoreCase(b)) {
                to = 16.30;
            }
            if ("Purobi".equalsIgnoreCase(b)) {
                to = 16.80;
            }
            if ("Kalshi".equalsIgnoreCase(b)) {
                to = 20.0;
            }
            if ("ECB Square".equalsIgnoreCase(b)) {
                to = 22.0;
            }
            if ("MES".equalsIgnoreCase(b)) {
                to = 24.0;
            }
            if ("Shewra".equalsIgnoreCase(b)) {
                to = 24.40;
            }
            if ("Kuril Bishwa Road".equalsIgnoreCase(b)) {
                to = 25.0;
            }
            if ("Khilkhet".equalsIgnoreCase(b)) {
                to = 26.0;
            }
            if ("Airport".equalsIgnoreCase(b)) {
                to = 28.4;
            }
            if ("Jashimuddin".equalsIgnoreCase(b)) {
                to = 29.6;
            }
            if ("Rajlakshmi".equalsIgnoreCase(b)) {
                to = 29.9;
            }
            if ("House Building".equalsIgnoreCase(b)) {
                to = 30.2;
            }
            if ("Abdullahpur".equalsIgnoreCase(b)) {
                to = 32.1;
            }
            if ("Kamarpara".equalsIgnoreCase(b)) {
                to = 34.2;
            }

        }
        //busRoutNo5

        if (from > to) {
            distance = from - to;
        } else {
            distance = to - from;
        }

        String result = String.format("%.2f", distance);

        return result;
    }


    List<String> allBuses() {
        String[] allBus = getResources().getStringArray(R.array.all_bus);

        List<String> allBusList = new ArrayList();
        for (int i = 0; i < allBus.length; i++) {
            allBusList.add(allBus[i]);
        }

        return allBusList;

    }


}