package com.vad.modulchit.screens.gcde;

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

public class FragmentGCDe extends Fragment implements ListGCDEView{

    private EditText editTextA;
    private EditText editTextB;
    private Button btnOk;

    private RecyclerView mRecyclerView;
    private AdapterGCDe adapterGCDe;

    private AlgebraMod algebraMod;
    private View includeTitle;
    private ListGCDEpresenter presenter;


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
        View v = inflater.inflate(R.layout.fragment_gcde, container, false);

        presenter = new ListGCDEpresenter(this);
        editTextA = (EditText) v.findViewById(R.id.editTextA);
        editTextB = (EditText) v.findViewById(R.id.editTextB);
        btnOk = (Button) v.findViewById(R.id.button);
        includeTitle = (View) v.findViewById(R.id.includeGCDE);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.myRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterGCDe = new AdapterGCDe();
        algebraMod = new AlgebraMod();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String aStr = editTextA.getText().toString();
                String bStr = editTextB.getText().toString();

                presenter.showResult(aStr, bStr);
            }
        });

        return v;
    }

    @Override
    public void showData(List<TableNumberGCDe> tableNumberGCDeList) {
        adapterGCDe.setTableNumbers(tableNumberGCDeList);
        mRecyclerView.setAdapter(adapterGCDe);
    }

    @Override
    public void showError(int resource) {
        Toast.makeText(getContext(), ""+getString(resource), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTitle() {
        includeTitle.setVisibility(View.VISIBLE);
    }
}