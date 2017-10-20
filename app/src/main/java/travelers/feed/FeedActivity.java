package travelers.feed;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import travelers.feed.info_adapters.DestAdpater;
import travelers.feed.info_feed.Destinations;

public class FeedActivity extends AppCompatActivity {

    private RecyclerView recyclerviewDestinations;
    private DestAdpater adapterDestinations;
    private ArrayList<Destinations> arr_collection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        init();
        new FetchDataTask().execute();
    }

    private void init() {
        recyclerviewDestinations = (RecyclerView) findViewById(R.id.recyclerDestination);
        recyclerviewDestinations.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewDestinations.setHasFixedSize(true);
        arr_collection = new ArrayList<>();
        adapterDestinations = new DestAdpater(arr_collection, this);
        recyclerviewDestinations.setAdapter(adapterDestinations);
    }
    public class FetchDataTask extends AsyncTask<Void, Void, Void> {
        private String apiString;

        @Override
        protected Void doInBackground(Void... voids) {
            HttpURLConnection url_connect = null;
            BufferedReader reader = null;
            Uri builtUri = Uri.parse(getString(R.string.google_api));
            URL url;
            try {
                url = new URL(builtUri.toString());
                url_connect = (HttpURLConnection) url.openConnection();
                url_connect.setRequestMethod("GET");
                url_connect.setRequestProperty("user-key", "AIzaSyC1Svb1mu2sq-sdXzrRoI-VVsSR4BoWEkA");
                url_connect.connect();

                InputStream input_stream = url_connect.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (input_stream == null) {
                    return null;    //Nothing to do
                }

                reader = new BufferedReader(new InputStreamReader(input_stream));

                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                apiString = buffer.toString();
                JSONObject jsonObject = new JSONObject(apiString);

                Log.v("Response", jsonObject.toString());

                JSONArray destinationsArray = jsonObject.getJSONArray("restaurants");

                //list = new ArrayList<>();
                for (int i = 0; i < destinationsArray.length(); i++) {

                    Log.v("LOL", i + "");
                    String name;
                    String address;
                    String currency;
                    String imageUrl;
                    long lon;
                    long lat;
                    long expense;
                    float rating;


                    JSONObject jDestination = (JSONObject) destinationsArray.get(i);
                    jDestination = jDestination.getJSONObject("name");
                    JSONObject jLocattion = jDestination.getJSONObject("location");
                    JSONObject jRating = jDestination.getJSONObject("user_rating");


                    name = jDestination.getString("name");
                    address = jLocattion.getString("address");
                    lat = jLocattion.getLong("latitude");
                    lon = jLocattion.getLong("longitude");
                    currency = jDestination.getString("currency");
                    expense = jDestination.getInt("average_expense");
                    imageUrl = jDestination.getString("thumb");
                    rating = (float) jRating.getDouble("aggregate_rating");


                    Destinations dest = new Destinations();
                    dest.setName(name);
                    dest.setAddress(address);
                    dest.setLatitude(lat);
                    dest.setLongitude(lon);
                    dest.setCurrency(currency);
                    dest.setExpense(String.valueOf(expense));
                    dest.setImageUrl(imageUrl);
                    dest.setRating(String.valueOf(rating));

                    arr_collection.add(dest);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (url_connect != null) {
                    url_connect.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("FeedActivity", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapterDestinations.notifyDataSetChanged();
        }
    }
}
