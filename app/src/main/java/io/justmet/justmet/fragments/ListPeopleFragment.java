package io.justmet.justmet.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.justmet.justmet.ListPeopleAdapter;
import io.justmet.justmet.Person;
import io.justmet.justmet.R;


public class ListPeopleFragment extends Fragment {
    private static final String LOG_TAG = ListPeopleFragment.class.getSimpleName();

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ListPeopleAdapter adapter;

    ArrayList<Person> personArray = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        personArray.add(new Person("Jeff Stone", "Cool tall redhead"));
        personArray.add(new Person("Avery Bradley"));
        personArray.add(new Person("Matt Kujawinski", "Nerd that is kind of funny"));
        personArray.add(new Person("Really short possibly gay guy?"));
        personArray.add(new Person("Meghan Fox", "10/10 smoke from LF"));
        personArray.add(new Person("Mitesh","Work friend with all the answers"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_people, container, false);

        // Init RecyclerView and SwipeRefreshLayout by id
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.list);

        // Improve performance if you know the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the ListAdapter to use in the RecycleView
        adapter = new ListPeopleAdapter(getActivity().getApplicationContext(), personArray);

        // Set the adapter between the ListView and its backing area
        recyclerView.setAdapter(adapter);

        // Implement SwireRefreshLayout.OnRefreshListener for when users do "swipe to refresh"
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");

                initiateRefresh();
            }
        });

        return inflater.inflate(R.layout.fragment_list_people, container, false);
    }

    /**
     *  initiateRefresh() method and the Refresh action item to refresh the content.
     */
    private void initiateRefresh() {
        Log.i(LOG_TAG, "initiateRefresh");

        // Execute the background task, which uses AsyncTask to load the data.
        new MakeBackendGETCallForTheListDataEventuallyLOL().execute(personArray);
    }

    /**
     * When the AsyncTask finishes, it calls onRefreshComplete(), which updates the data in the
     * ListAdapter and turns off the progress bar.
     */
    private void onRefreshComplete(List<Person> result) {
        Log.i(LOG_TAG, "onRefreshComplete");

        personArray.clear();
        for (Person person : result) {
            personArray.add(person);
        }

        // Notify the ListPeopleAdapter about recent changed
        adapter.notifyDataSetChanged();

        // Stop the refreshing indicator
        swipeRefreshLayout.setRefreshing(false);
    }


    /**
     * Async call used to GET users justmets list
     */
    private class MakeBackendGETCallForTheListDataEventuallyLOL extends AsyncTask<ArrayList<Person>, Void, List<Person>> {

        static final int TASK_DURATION = 3 * 1000; // 3 seconds

        // No Param passed
        @Override
        protected void onPreExecute() {
            // do something before execution
        }

        // First param passed into doInBackground
        @Override
        protected List<Person> doInBackground(ArrayList<Person>... params) {
            // Sleep for a small amount of time to simulate a background-task
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Get passed array and add a refresh item to debug
            ArrayList<Person> personArray = params[0];
            personArray.add(new Person("Re Fresh", "Just me this guy on refresh!"));

            // Return a new random list of cheeses
            return personArray;
        }

        // Second param passed into onProgressUpdate
        @Override
        protected void onProgressUpdate(Void... voids) {
            // do something for progress update
        }

        // Third param passed into onPostExecute
        @Override
        protected void onPostExecute(List<Person> persons) {
            super.onPostExecute(persons);

            // Tell the Fragment that the refresh has completed
            onRefreshComplete(persons);
        }

    }
}
