package com.example.student.readfiles;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class BlankFragment extends Fragment {


    private int position;

    public BlankFragment(final int position) {
        this.position = position;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        if (position == 0) {

            ReadXml readXml = new ReadXml(getContext(), view);
            readXml.start();


        } else if (position == 1) {
            ReadJson readJson = new ReadJson(getContext(), view);
            readJson.start();
        }
        return view;
    }


    public class ReadXml extends Thread {

        private final Context context;
        private final View view;


        public ReadXml(Context context, View view) {
            this.context = context;
            this.view = view;
        }

        @Override
        public void run() {
            parseXML();
        }

        private void parseXML() {
            final XmlPullParserFactory parserFactory;
            try {
                parserFactory = XmlPullParserFactory.newInstance();
                final XmlPullParser parser = parserFactory.newPullParser();
                final InputStream is = context.getAssets().open("cars.xml");
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(is, null);

                processParsing(parser);

            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
        }

        private void processParsing(XmlPullParser parser) throws IOException, XmlPullParserException {
            ArrayList<ModelInfo> cars = new ArrayList<>();
            int eventType = parser.getEventType();
            ModelInfo currentCar = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String eltName = null;

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        eltName = parser.getName();

                        if ("car".equals(eltName)) {
                            currentCar = new ModelInfo();
                            cars.add(currentCar);
                        } else if (currentCar != null) {
                            if ("marka".equals(eltName)) {
                                currentCar.textMarka = parser.nextText();
                            } else if ("model".equals(eltName)) {
                                currentCar.textModel = parser.nextText();
                            } else if ("price".equals(eltName)) {
                                currentCar.textPrice = parser.nextText();
                            }
                        }
                        break;
                }

                eventType = parser.next();
            }
            startCarsList(cars, view);

        }

        public void startCarsList(final List<ModelInfo> cars, final View view) {
            final RecyclerView recyclerPeoples = view.findViewById(R.id.rec_view);
            recyclerPeoples.setHasFixedSize(true);
            recyclerPeoples.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerPeoples.setAdapter(new CarsAdapter(getContext(), cars));
        }


    }

    public class ReadJson extends Thread {

        private final Context context;
        private final View view;

        private static final String JSON_NAME = "cars.json";


        public ReadJson(Context context, View view) {
            this.context = context;
            this.view = view;
        }


        @Override
        public void run() {

            getJson();

        }


        private void getJson() {
            final List<ModelInfo> cars = new ArrayList<>();
            StringBuffer stringBuffer = null;
            JSONArray jArray = null;
            try (final BufferedReader bf = new BufferedReader(new InputStreamReader(
                    context.getResources().getAssets().open("cars.json")))) {
                String line;
                stringBuffer = new StringBuffer();
                while ((line = bf.readLine()) != null) {
                    stringBuffer.append(line);
                }
                jArray = new JSONArray(stringBuffer.toString());
            } catch (final IOException | JSONException e) {
            }

            assert jArray != null;
            for (int i = 0; i < jArray.length(); i++) {
                try {
                    final JSONObject jsonObject = jArray.getJSONObject(i);
                    final String model = jsonObject.getString("model");
                    final String marka = jsonObject.getString("marka");
                    final String price = jsonObject.getString("price");
                    cars.add(new ModelInfo(marka, model, price));
                } catch (final JSONException e) {
                }
            }
            startCarsList(cars, view);
        }

        public void startCarsList(final List<ModelInfo> cars, final View view) {
            final RecyclerView recyclerPeoples = view.findViewById(R.id.rec_view);
            recyclerPeoples.setHasFixedSize(true);
            recyclerPeoples.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerPeoples.setAdapter(new CarsAdapter(getContext(), cars));
        }

    }


}
