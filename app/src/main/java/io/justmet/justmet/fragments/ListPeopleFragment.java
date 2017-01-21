package io.justmet.justmet.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.justmet.justmet.AddPersonActivity;
import io.justmet.justmet.ListPeopleAdapter;
import io.justmet.justmet.Person;
import io.justmet.justmet.R;


public class ListPeopleFragment extends Fragment {
    private static final String LOG_TAG = ListPeopleFragment.class.getSimpleName();

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ListPeopleAdapter adapter;

    ArrayList<Person> personArray = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        populatePersonArray();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_people, container, false);

        // Initialize adapter
        adapter = new ListPeopleAdapter(getActivity().getApplicationContext(), personArray);

        // Inflate RecyclerView && set layout && set adapter
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true); // Improve performance
        recyclerView.setAdapter(adapter);

        // Add a divider for list items
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Create Swipe left to delete list item touch helper
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Get position which got swiped then remove that item
                final int position = viewHolder.getAdapterPosition();
                personArray.remove(position);
                adapter.notifyItemRemoved(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        // Add item touch helper (swipe L for delete) to recyclerview
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // Adding Floating Action Button to bottom right of main view
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v, "Fab!!", Snackbar.LENGTH_LONG).show();
                Context context = v.getContext();
                Intent intent = new Intent(context, AddPersonActivity.class);
                context.startActivity(intent);
            }
        });

        return rootView;
    }

    private void populatePersonArray() {
        personArray.add(new Person("Jeff Stone", "Cool tall redhead"));
        personArray.add(new Person("Avery Bradley"));
        personArray.add(new Person("Matt Kujawinski", "Nerd that is kind of funny"));
        personArray.add(new Person("Really short possibly gay guy?"));
        personArray.add(new Person("Meghan Fox", "10/10 smoke from LF"));
        personArray.add(new Person("Mitesh","Work friend with all the answers"));
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
                Toast.makeText(getActivity().getApplicationContext(), "Sleep for 3 seconds", Toast.LENGTH_SHORT).show();
                Log.i(LOG_TAG, "Sleep for 3 seconds");
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
            //onRefreshComplete(persons);
        }

    }

    /* GOES IN ONCREATEVIEWswipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
            // Implement SwireRefreshLayout.OnRefreshListener for when users do "swipe to refresh"
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");

                    initiateRefresh();
                }
            });*/
    /**
     *  initiateRefresh() method and the Refresh action item to refresh the content.
     */
    /*private void initiateRefresh() {
        Log.i(LOG_TAG, "initiateRefresh");

        // Execute the background task, which uses AsyncTask to load the data.
        new MakeBackendGETCallForTheListDataEventuallyLOL().execute(personArray);
    }*/

    /**
     * When the AsyncTask finishes, it calls onRefreshComplete(), which updates the data in the
     * ListAdapter and turns off the progress bar.
     */
    /*private void onRefreshComplete(List<Person> result) {
        Log.i(LOG_TAG, "onRefreshComplete");

        personArray.clear();
        for (Person person : result) {
            personArray.add(person);
        }

        // Notify the ListPeopleAdapter about recent changed
        adapter.notifyDataSetChanged();

        // Stop the refreshing indicator
        swipeRefreshLayout.setRefreshing(false);
    }*/
}
