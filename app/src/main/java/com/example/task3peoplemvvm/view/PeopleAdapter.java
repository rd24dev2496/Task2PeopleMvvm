package com.example.task3peoplemvvm.view;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task3peoplemvvm.R;
import com.example.task3peoplemvvm.databinding.ItemPeopleBinding;
import com.example.task3peoplemvvm.model.People;
import com.example.task3peoplemvvm.viewmodel.ItemPeopleViewModel;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder> {

    private List<People> peopleList;
    PeopleAdapter(){
        this.peopleList= Collections.emptyList();
    }
    @NonNull
    @Override
    public PeopleAdapter.PeopleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ItemPeopleBinding itemPeopleBinding=
               DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people,parent,false);

        return new PeopleAdapterViewHolder(itemPeopleBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleAdapter.PeopleAdapterViewHolder holder, int position) {

        holder.bindPeople(peopleList.get(position));
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }
    void setPeopleList(List<People> peopleList) {
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }

    public static class PeopleAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemPeopleBinding binding;

        PeopleAdapterViewHolder(ItemPeopleBinding binding) {
            super(binding.itemPeople);
            this.binding = binding;
        }
        void bindPeople(People people) {
            if (binding.getPeopleViewModel() == null) {
                binding.setPeopleViewModel(new ItemPeopleViewModel(itemView.getContext(), people));
            } else {
             binding.getPeopleViewModel().setPeople(people);
            }
        }
    }
}
