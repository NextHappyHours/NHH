package com.kobgnirps.nhh2;

import com.kobgnirps.nhh2.R;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.kobgnirps.nhh2.adapter.CustomListAdapter;
import com.kobgnirps.nhh2.app.AppController;
import com.kobgnirps.nhh2.model.Bar;

public class BarsFragment extends Fragment {
	private static final String TAG = BarsFragment.class.getSimpleName();
	

	// Bars json url
	private static final String url = "http://www.nexthappyhours.com/movies.json";
	private ProgressDialog pDialog;
	private List<Bar> barList = new ArrayList<Bar>();
	private ListView listView;
	private CustomListAdapter adapter;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_games, container, false);
		listView = (ListView) rootView.findViewById(R.id.list);
		return rootView;
	}
	
	  @Override
	  public void onActivityCreated(Bundle savedInstanceState) {
		  super.onActivityCreated(savedInstanceState);

        Context context = getActivity().getApplicationContext();
		adapter = new CustomListAdapter(context, this, barList);
		listView.setAdapter(adapter);    

		pDialog = new ProgressDialog(getActivity());
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();


		// Creating volley request obj
		JsonArrayRequest barReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						hidePDialog();

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Bar bar = new Bar();
								bar.setName(obj.getString("name"));
								bar.setThumbnailUrl(obj.getString("image"));
								bar.setDistance(((Number) obj.get("distance"))
										.doubleValue());
								bar.setDeal(obj.getString("deal"));						
								
								// adding movie to movies array
								barList.add(bar);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(barReq);
	}	
    
	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

}

