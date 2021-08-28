package com.ssm.test;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Arrange {
    //使用JohnsonTrotter算法获取1~n的全排列
    public HashMap<Integer , String> getJohnsonTrotter(int n){
        HashMap<Integer , String> hashMap = new HashMap<Integer , String>();
        int count = 0;                //用于计算生成排列的总个数，初始化为0
        int[] arrayN = new int[n];
        int[] directionN = new int[n+1];      //directionN[i]用于标记1~n中数字i上的箭头方向，初始化值为0，表示箭头方向向左；值为1 表示箭头方向向右
        for(int i = 0;i < n;i++)
            arrayN[i] = i+1;
        String result = getArrayString(arrayN);
        hashMap.put(count, result);        //将原始排列添加到哈希表中
        while(judgeMove(arrayN,directionN)){      //存在一个移动元素
            int maxI = getMaxMove(arrayN,directionN);
            if(directionN[arrayN[maxI]] == 0)      //箭头指向左方
                swap(arrayN,maxI,--maxI);
            if(directionN[arrayN[maxI]] == 1)       //箭头指向右方
                swap(arrayN,maxI,++maxI);
            for(int i = 0;i < n;i++){               //调转所有大于arrayN[maxI]的数的箭头方向
                if(arrayN[i] > arrayN[maxI]){
                    if(directionN[arrayN[i]] == 0)
                        directionN[arrayN[i]] = 1;
                    else
                        directionN[arrayN[i]] = 0;
                }
            }
            count++;
            result = getArrayString(arrayN);
            hashMap.put(count, result);        //将得到的新排列添加到哈希表中
        }
        return hashMap;
    }
    //判断数组arrayN中是否存在可移动元素
    public boolean judgeMove(int[] arrayN,int[] directionN){
        boolean judge = false;
        for(int i = arrayN.length - 1;i >= 0;i--){
            if(directionN[arrayN[i]] == 0 && i != 0){     //当arrayN[i]数字上的箭头方向指向左边时
                if(arrayN[i] > arrayN[i-1])
                    return true;
            }
            if(directionN[arrayN[i]] == 1 && i != (arrayN.length-1)){    //当arrayN[i]数字上的箭头方向指向右边时
                if(arrayN[i] > arrayN[i+1])
                    return true;
            }
        }
        return judge;
    }
    //获取数组arrayN中最大的可移动元素的数组下标
    public int getMaxMove(int[] arrayN,int[] directionN){
        int result = 0;
        int temp = 0;
        for(int i = 0;i < arrayN.length;i++){
            if(directionN[arrayN[i]] == 0 && i != 0){     //当arrayN[i]数字上的箭头方向指向左边时
                if(arrayN[i] > arrayN[i-1]){
                    int max = arrayN[i];
                    if(max > temp)
                        temp = max;
                }
            }
            if(directionN[arrayN[i]] == 1 && i != (arrayN.length-1)){    //当arrayN[i]数字上的箭头方向指向右边时
                if(arrayN[i] > arrayN[i+1]){
                    int max = arrayN[i];
                    if(max > temp)
                        temp = max;
                }
            }
        }
        for(int i = 0;i < arrayN.length;i++){
            if(arrayN[i] == temp)
                return i;
        }
        return result;
    }
    //交换数组中两个位置上的数值
    public void swap(int[] array,int m,int n){
        int temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }
    //把数组array中所有元素按照顺序以字符串结果返回
    public String getArrayString(int[] array){
        String result = "";
        for(int i = 0;i < array.length;i++)
            result = result + array[i];
        return result;
    }

    public static void main(String[] args){
        Arrange test = new Arrange();
        HashMap<Integer , String> hashMap = test.getJohnsonTrotter(3);
        Collection<String> c1 = hashMap.values();
        Iterator<String> ite = c1.iterator();
        while(ite.hasNext())
            System.out.println(ite.next());
        System.out.println(hashMap);

    }
}