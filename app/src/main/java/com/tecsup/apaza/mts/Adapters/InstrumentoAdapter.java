package com.tecsup.apaza.mts.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tecsup.apaza.mts.Models.Instrumento;
import com.tecsup.apaza.mts.R;
import com.tecsup.apaza.mts.Service.ApiService;

import java.util.ArrayList;
import java.util.List;

public class InstrumentoAdapter extends RecyclerView.Adapter<InstrumentoAdapter.ViewHolder> {

    private List<Instrumento> instrumentos;

    //final ArrayList<String> correcta = new ArrayList<String>();

    public List<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    public InstrumentoAdapter(){
        this.instrumentos = new ArrayList<>();
    }

    public void setInstrumentos(List<Instrumento> evaluateds){
        this.instrumentos = evaluateds;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView nombreText,serieText;




        public ViewHolder(View itemView) {
            super(itemView);

            nombreText = itemView.findViewById(R.id.txt_evaluated);
            serieText = itemView.findViewById(R.id.txt_serie);


        }
    }

    @Override
    public InstrumentoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_instrumento, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final InstrumentoAdapter.ViewHolder viewHolder, final int position) {

        final Instrumento instrumento = this.instrumentos.get(position);

        viewHolder.nombreText.setText(instrumento.getDescripcion());
        viewHolder.serieText.setText(instrumento.getSerie());


        //  }

    }

    @Override
    public int getItemCount() {
        return this.instrumentos.size();
    }
}
