package io.justmet.justmet.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.justmet.justmet.Person;
import io.justmet.justmet.R;


public class ListFragment extends Fragment {

    ArrayList<Person> personArray = new ArrayList<>();

    public ListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        personArray.add(new Person("Jeff Stone", "Cool tall redhead"));
        personArray.add(new Person("Avery Bradley"));
        personArray.add(new Person("Matt Kujawinski", "Nerd that is kind of funny"));
        personArray.add(new Person("Really short possibly gay guy?"));
        personArray.add(new Person("Meghan Fox", "10/10 smoke from LF"));
        personArray.add(new Person("Mitesh","Work friend with all the answers"));

        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}
