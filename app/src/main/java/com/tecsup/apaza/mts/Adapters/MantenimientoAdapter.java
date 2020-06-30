package com.tecsup.apaza.mts.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tecsup.apaza.mts.Models.Instrumento;
import com.tecsup.apaza.mts.Models.Mantenimiento;
import com.tecsup.apaza.mts.R;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MantenimientoAdapter extends RecyclerView.Adapter<MantenimientoAdapter.ViewHolder> {

    private List<Mantenimiento> mantenimientos;
    Format formatter;

    //final ArrayList<String> correcta = new ArrayList<String>();

    public List<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    public MantenimientoAdapter(){
        this.mantenimientos = new ArrayList<>();
    }

    public void setMantenimientos(List<Mantenimiento> mantenimientos){
        this.mantenimientos = mantenimientos;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView nombreText,serieText, nombreText2;
        public LinearLayout linearLayout;




        public ViewHolder(View itemView) {
            super(itemView);

            nombreText = itemView.findViewById(R.id.txt1);
            serieText = itemView.findViewById(R.id.txt2);
            //nombreText2 = itemView.findViewById(R.id.txt3);
            linearLayout = itemView.findViewById(R.id.linearbg);


        }
    }

    @Override
    public MantenimientoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fecha, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MantenimientoAdapter.ViewHolder viewHolder, final int position) {

        final Mantenimiento mantenimiento = this.mantenimientos.get(position);

        formatter = new SimpleDateFormat("dd-MM-yyyy");
        String s = formatter.format(mantenimiento.getFlimite());

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date fechaActual = null;
        Date fechaMan = null;
        try {
            fechaMan = dateFormat.parse(s);
            fechaActual = dateFormat.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        int diasdiferencia=(int)((fechaMan.getTime()-fechaActual.getTime())/86400000);
        String days = String.valueOf(diasdiferencia);

        if (diasdiferencia > 3){
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#04B431"));
        }else if(diasdiferencia < 4 && diasdiferencia > 0){
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#D7DF01"));
        }else if(diasdiferencia < 1){
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#FF0000"));
        }


        //String a = String.valueOf(mantenimiento.getIdmantenimiento());



        viewHolder.nombreText.setText("Fecha actual:"+date +"- Fecha mantenimiento "+s);
        viewHolder.serieText.setText( mantenimiento.getDescripcion()+" les quedan "+days+" días!!");
        //viewHolder.serieText.setText(mantenimiento.getFlimite());
        //viewHolder.nombreText.setText(mantenimiento.get());

        //  }

    }

    @Override
    public int getItemCount() {
        return this.mantenimientos.size();
    }
}
