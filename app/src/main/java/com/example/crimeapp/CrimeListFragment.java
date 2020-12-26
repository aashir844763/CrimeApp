package com.example.crimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListFragment extends Fragment {


    private RecyclerView crimeRecyclerView;
    private CrimeAdapter crimeAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.fragment_list_crime,container,false);
        crimeRecyclerView = (RecyclerView) v.findViewById(R.id.crime_recycler_view);
        crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if(crimeAdapter==null) {
            crimeAdapter = new CrimeAdapter(crimes);
            crimeRecyclerView.setAdapter(crimeAdapter);
        }else
            crimeAdapter.notifyDataSetChanged();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////*******CRIME HOLDER*******///////////////////////////////////////////////////////////////////////////////////




    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleTextView;
        private TextView dateTextView;
        private ImageView solvedImage;
        private Crime mCrime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_crime, parent, false));

            titleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            dateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            solvedImage = (ImageView) itemView.findViewById(R.id.crime_solved);

            itemView.setOnClickListener(this);

        }

        public void bind(Crime crime) {
            mCrime = crime;
            titleTextView.setText(mCrime.getTitle());
            dateTextView.setText(mCrime.getDate().toString());
            solvedImage.setVisibility(mCrime.isSolved() ? View.VISIBLE : View.GONE);

        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(), mCrime.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }

    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////*******CRIME ADAPTER*******///////////////////////////////////////////////////////////////////////////////////




    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> crime;

        public CrimeAdapter(List<Crime> crime) {
            this.crime=crime;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime c = crime.get(position);
            holder.bind(c);
        }

        @Override
        public int getItemCount() {
            return crime.size();
        }
    }


}
