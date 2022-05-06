package com.vad.modulchit.screens.sort.bubble;

import com.vad.modulchit.utils.sort.BubbleSort;

import java.util.List;

public class Render extends Thread{

    private CustomViewBubbleSort customViewBubbleSort;
    private BubbleSort bubbleSort;

    public Render(CustomViewBubbleSort customViewBubbleSort, BubbleSort bubbleSort) {
        this.customViewBubbleSort = customViewBubbleSort;
        this.bubbleSort = bubbleSort;
    }

    @Override
    public void run() {
        super.run();
        rendering();
    }

    public void rendering() {
        List<int[]> arr = bubbleSort.sort(customViewBubbleSort.getArr());

        for (int[] a : arr) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            customViewBubbleSort.setArr(a);
        }
    }

}
