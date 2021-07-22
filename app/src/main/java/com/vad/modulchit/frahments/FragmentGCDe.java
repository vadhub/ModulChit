package com.vad.modulchit.frahments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vad.modulchit.adapters.AdapterGCDe;
import com.vad.modulchit.R;
import com.vad.modulchit.pojos.TableNumberGCDe;
import com.vad.modulchit.utils.AlgebraMod;

import java.util.List;

public class FragmentGCDe extends Fragment {

    private EditText editTextA;
    private EditText editTextB;
    private Button btnOk;

    private RecyclerView mRecyclerView;
    private AdapterGCDe adapterGCDe;

    private AlgebraMod algebraMod;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//
////        List<TableNumber> ta = gcdGraph(625, 216);
////
////        for(TableNumber t: ta){
////            System.out.println(t.getA()+" "+t.getB()+" "+t.getQ()+" "+t.getR()+" "+t.getX1()+" "+t.getX2()+" "+t.getY1()+" "+t.getY2());
////        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);

        editTextA = (EditText) v.findViewById(R.id.editTextA);
        editTextB = (EditText) v.findViewById(R.id.editTextB);
        btnOk = (Button) v.findViewById(R.id.button);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.myRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterGCDe = new AdapterGCDe();
        algebraMod = new AlgebraMod();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((!editTextA.getText().toString().equals("")&&!editTextB.getText().toString().equals(""))
                    &&(!editTextA.getText().toString().equals("")||!editTextB.getText().toString().equals(""))){

                    int a = Integer.parseInt(editTextA.getText().toString());
                    int b = Integer.parseInt(editTextB.getText().toString());

                    if(a!=0||b!=0){
                        if(a!=0&&b!=0){
                            List<TableNumberGCDe> tempTableNumberGCDes = algebraMod.gcdGraph(a, b);
                            adapterGCDe.setTableNumbers(tempTableNumberGCDes);
                            mRecyclerView.setAdapter(adapterGCDe);

                        }else{
                            Toast.makeText(getContext(), "Zero is invalid!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "Zero is invalid!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Enter the number!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}