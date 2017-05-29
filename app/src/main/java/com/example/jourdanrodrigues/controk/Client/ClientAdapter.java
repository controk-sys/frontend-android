package com.example.jourdanrodrigues.controk.Client;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jourdanrodrigues.controk.R;

import java.util.List;

class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {
    private List<Client> clients;
    private Listener listener;

    ClientAdapter(List<Client> clients, Listener listener) {
        this.clients = clients;
        this.listener = listener;
    }

    static class ClientViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView name;
        TextView email;
        TextView cpf;

        ClientViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.client_card_view);
            name = (TextView) view.findViewById(R.id.client_name);
            email = (TextView) view.findViewById(R.id.client_email);
            cpf = (TextView) view.findViewById(R.id.client_cpf);
        }
    }

    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_client, viewGroup, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClientViewHolder clientViewHolder, int i) {
        final Client client = clients.get(i);
        clientViewHolder.name.setText(client.getName());
        clientViewHolder.email.setText(client.getEmail());
        clientViewHolder.cpf.setText(client.getCpf());

        clientViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(client);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    interface Listener {
        void onClick(Client client);
    }
}
