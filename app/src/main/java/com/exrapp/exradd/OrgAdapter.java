package com.exrapp.exradd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrgAdapter extends RecyclerView.Adapter<OrgAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView orgNameView;
        public TextView orgPhoneView;
        public TextView orgIDView;

        public ViewHolder(View itemView) {
            super(itemView);

            orgNameView = itemView.findViewById(R.id.org_name);
            orgPhoneView = itemView.findViewById(R.id.org_phone);
            orgIDView = itemView.findViewById(R.id.org_id);
        }
    }

    private final List<Organization> mOrganizations;

    public OrgAdapter(List<Organization> organizations) {
        mOrganizations = organizations;
    }

    @NonNull
    @Override
    public OrgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View orgView = inflater.inflate(R.layout.card_row_item2, parent, false);

        return new ViewHolder(orgView);
    }

    @Override
    public void onBindViewHolder(OrgAdapter.ViewHolder holder, int position) {
        Organization org = mOrganizations.get(position);

        TextView orgNView = holder.orgNameView;
        TextView orgPView = holder.orgPhoneView;
        TextView orgIView = holder.orgIDView;

        orgNView.setText(org.getOrgName());
        orgPView.setText(org.getOrgPhone());
        orgIView.setText(org.getOrgID());
    }

    @Override
    public int getItemCount() {
        return mOrganizations.size();
    }
}