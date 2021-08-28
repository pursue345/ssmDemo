package com.ssm.test;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Arrange1 {

    public HashMap<Integer,String> getLexicographicPermute(int n){
        HashMap<Integer,String> hashMap = new HashMap<Integer,String>();
        int count = 0;         //用于计算生成排列的总个数，初始化为0
        int[] arrayN = new int[n];
        for(int i = 0;i < n;i++)
            arrayN[i] = i+1;
        String result = getArrayString(arrayN);
        hashMap.put(count, result);        //将原始排列添加到哈希表中
        while(riseTogetherArray(arrayN)){     //数组中存在两个连续的升序元素
            int i = getMaxI(arrayN);     //找出使得ai<ai+1的最大i: ai+1>ai+2>...>an
            int j = getMaxJ(arrayN);     //找到使得ai<aj的最大索引j: j>=i,因为ai<ai+1
            swap(arrayN,i,j);
            reverseArray(arrayN,i+1,arrayN.length-1);
            result = getArrayString(arrayN);
            count++;
            hashMap.put(count, result);        //将新得到的排列添加到哈希表中
        }
        System.out.println("排列总个数count = "+(count+1));
        return hashMap;
    }
    //判断数组中是否 包含两个连续的升序元素
    public boolean riseTogetherArray(int[] arrayN){
        boolean result = false;
        for(int i = 1;i < arrayN.length;i++){
            if(arrayN[i-1] < arrayN[i])
                return true;
        }
        return result;
    }
    //返回i:满足ai<ai+1，ai+1>ai+2>...>an(PS:an为数组中最后一个元素)
    public int getMaxI(int[] arrayN){
        int result = 0;
        for(int i = arrayN.length-1;i > 0;){
            if(arrayN[i-1] > arrayN[i])
                i--;
            else
                return i-1;
        }
        return result;
    }
    //返回j:ai<aj的最大索引，j>=i+1，因为ai<ai+1(此处i值为上面函数getMaxI得到值)
    public int getMaxJ(int[] arrayN){
        int result = 0;
        int tempI = getMaxI(arrayN);
        for(int j = tempI+1;j < arrayN.length;){
            if(arrayN[tempI] < arrayN[j]){
                if(j == arrayN.length-1)
                    return j;
                j++;
            }
            else
                return j-1;
        }
        return result;
    }
    //交换数组中两个位置上的数值
    public void swap(int[] array,int m,int n){
        int temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }
    //将数组中a[m]到a[n]一段元素反序排列
    public void reverseArray(int[] arrayN,int m,int n){
        while(m < n){
            int temp = arrayN[m];
            arrayN[m++] = arrayN[n];
            arrayN[n--] = temp;
        }
    }
    //把数组array中所有元素按照顺序以字符串结果返回
    public String getArrayString(int[] array){
        String result = "";
        for(int i = 0;i < array.length;i++)
            result = result + array[i];
        return result;
    }

    public static void main(String[] args){
        Arrange1 test = new  Arrange1();
        HashMap<Integer,String> hashMap = test.getLexicographicPermute(3);
        Collection<String> c1 = hashMap.values();
        Iterator<String> ite = c1.iterator();
        while(ite.hasNext())
            System.out.println(ite.next());
        System.out.println(hashMap);
    }
}