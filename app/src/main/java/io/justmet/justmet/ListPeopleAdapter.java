package io.justmet.justmet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListPeopleAdapter extends RecyclerView.Adapter<ListPeopleAdapter.ViewHolder> {
    private Context context;
    private List<Person> personArray = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.person_name);
            description = (TextView) view.findViewById(R.id.person_description);
            // Add more list item fields?
        }
    }

    public ListPeopleAdapter(Context context, List<Person> personArray) {
        this.context = context;
        this.personArray = personArray;
    }

    @Override
    public ListPeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_people_item,parent,false);

        // Get the TextView reference from RecyclerView current item
        final TextView name = (TextView) v.findViewById(R.id.person_name);
        // Set a click listener for the current item of RecyclerView
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the RecyclerView current item text
                final String label = name.getText().toString();

                // Display the RecyclerView clicked item label
                Toast.makeText(
                        context,
                        "Clicked : " + label,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person person = personArray.get(position);
        holder.name.setText(person.getFullName());
        holder.description.setText(person.getDescription());
    }

    @Override
    public int getItemCount() {
        return personArray.size();
    }
}