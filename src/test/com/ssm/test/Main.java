package com.ssm.test;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
//        String[] epsName= new String[]{"当前点","血战湘江","五次反围剿","四渡赤水","过松潘草地","瓦窑堡会师"};
        String[] epsName= new String[]{"当前点","血战湘江","五次反围剿","四渡赤水"};
        NextByAllNodes nextByAllNodes = new NextByAllNodes(epsName,null);
        System.out.println("下一节点："+nextByAllNodes.getNextNode());

        for (int i = 0; i < nextByAllNodes.getMs().length; i++) {
            System.out.print(epsName[nextByAllNodes.getMs()[i]]+" -> ");
        }

    }


    public static void test01(){
        //当前点
        int x = 0,y=0;
        //使用一个list来存储未打卡的点,除开四渡赤水的点
        List<Integer> withOutArrivedList = new ArrayList<>();
        //将当前点加入到list对象中
        withOutArrivedList.add(x);
        withOutArrivedList.add(y);

        //使用一个list来存储未打卡四渡赤水的点
        List<Integer> fourNotArrivedList = new ArrayList<>();

        //使用一个list来存储还需要打卡的点的中文名
        List<String> epsNameList = new ArrayList<>();
        epsNameList.add("当前点");

        //初始化未打卡的二维矩阵
        int[][] distanceArray = null;

        //从报文中解析的打卡点list
        ArrayList<Episode> episodeList = new ArrayList<>();

        for (int i = 0; i < episodeList.size(); i++) {
            //筛选没有到达的坐标
            if(!episodeList.get(i).isArrived()){
                if(episodeList.get(i).getEpisodeName().equals("四渡赤水")){
                    fourNotArrivedList.add(episodeList.get(i).getPos_x());
                    fourNotArrivedList.add(episodeList.get(i).getPos_y());
                }else{
                    withOutArrivedList.add(episodeList.get(i).getPos_x());
                    withOutArrivedList.add(episodeList.get(i).getPos_y());
                    //将打卡点名称赋值进去，四渡赤水若存在未打卡点，增加在该list的最后
                    epsNameList.add(episodeList.get(i).getEpisodeName());
                }
            }
        }


        //求出四渡赤水的中心点
        if(fourNotArrivedList.size()>0){
            //当存在四渡赤水未打卡时，将该店加入到epsNameList，便于后续全图计算下一个最优节点
            epsNameList.add("四渡赤水");
            //四渡赤水的中心坐标点
            int fourX = 0;
            int fourY = 0;
            for (int i = 0; i < fourNotArrivedList.size()/2; i++) {
                fourX+=fourNotArrivedList.get(2*i);
                fourY+=fourNotArrivedList.get(2*i+1);
            }
            fourX /= fourNotArrivedList.size();
            fourY /= fourNotArrivedList.size();

            //默认当前加入首个四渡赤水未打卡的节点,后续再考虑如何处理，保证该点一定不是不可达的点
            withOutArrivedList.add(fourNotArrivedList.get(0));
            withOutArrivedList.add(fourNotArrivedList.get(1));

            //将四渡赤水没有打卡的点加入到
//            withOutArrivedList.add(fourX);
//            withOutArrivedList.add(fourY);
        }

        //若长度大于2则表示还有没有打卡的点，将各点的距离转化为矩阵
        if(withOutArrivedList.size()>2){
            distanceArray = new int[epsNameList.size()][epsNameList.size()];
            //求出各个点之间的距离，四渡赤水有点特殊，暂定使用中心点，若中心点是障碍，则使用四渡赤水右上方的点
            for (int i = 0; i < withOutArrivedList.size()/2; i++) {
                int x1 = withOutArrivedList.get(2 * i);
                int y1 = withOutArrivedList.get(2 * i+1);
                for (int j = i+1; j < withOutArrivedList.size()/2; j++) {

                    int x2 = withOutArrivedList.get(2 * i);
                    int y2 = withOutArrivedList.get(2 * i);

                    //调度两者坐标之间的距离
                    int sum = 0;
                    distanceArray[i][j] = sum;
                    distanceArray[j][i] = sum;
                }
            }
        }

        //根据矩阵查询最优的下一步打卡点
        if(null!=distanceArray && distanceArray.length>0){
            String[] epsNameArray = (String[]) epsNameList.toArray();
            NextByAllNodes nextByAllNodes = new NextByAllNodes(epsNameArray,distanceArray);
            System.out.println("下一节点："+nextByAllNodes.getNextNode());

            for (int i = 0; i < nextByAllNodes.getMs().length; i++) {
                System.out.print(epsNameArray[nextByAllNodes.getMs()[i]]+" -> ");
            }
        }

        //根据返回的打卡点，查询下一步应该走的方向


    }




}

class NextByAllNodes {

    private int count;     //定义全局变量，用于计算当前已行走方案次数，初始化为0
    private int MinDistance;    //定义完成一个行走方案的最短距离，初始化为100（PS：100此处表示比实际要大很多的距离）
    private String nextNode;    //定义完成一个行走方案的最短距离，初始化为100（PS：100此处表示比实际要大很多的距离）
    private String[] epsName; //传入的还需要打卡的点 + 起始点
    private int[] ms; //遍历所有节点的最短路径
    private int[][] distance;//二维矩阵

//    private int[][] distance = {
//            {0,2,5,7},
//            {2,0,8,3},
//            {5,8,0,1},
//            {7,3,1,0}};   //使用二维数组的那个音图的路径相关距离长度

    //无参构造
    public NextByAllNodes(){}

    //有参构造
    public NextByAllNodes(String[] epsNameArray, int[][] distanceArray) {
        this.epsName = epsNameArray;
        this.distance = distanceArray;
        count = 0;
        MinDistance = Integer.MAX_VALUE;
        queryMaxPath(epsName);
    }

    //一次性遍历所有节点，返回下一个应该前往的打卡点
    //作为参数传入，传入剩余还没有打卡的地方
    private int queryMaxPath(String[] epsName){
        //还需要打卡的个数+1（自身起点）
        int n = epsName.length;
        //传入数组长度为1，则表示没有节点需要走了
        if(n == 1)
            return 0;
        ms = new int[n];
        //用int值对应打卡点：0-起点  1-打卡点  2-打卡点。。。
        int[] valueArray = new int[n];
        for (int i = 0; i < valueArray.length; i++)
            valueArray[i] = i;

        dfs(valueArray,0,0,n,queryHasMaxPath(n));
        System.out.println("已完成全部行走方案！！！,最短路径距离为:"+MinDistance);
        return 1;
    }

    //根据传入值，查询有多少条不同的路径
    public int queryHasMaxPath(int n){
        if(n <= 0)
            return 0;
        if(n == 1)
            return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = i*dp[i-1];
        }
        return dp[n-1];
    }

    /*
     * start为开始进行排序的位置
     * step为当前正在行走的位置
     * n为需要排序的总位置数
     * Max为n!值
     */
    public void dfs(int[] A, int start, int step, int n, int Max){
        if(step == n){   // 当正在行走的位置等于城市总个数时
            ++count;           //每完成一次行走方案，count自增1
            printArray(A);     //输出行走路线方案及其总距离
        }
//        if(count == Max){
//            System.out.println("已完成全部行走方案！！！,最短路径距离为:"+MinDistance);
//        }else{
            for(int i = start;i < n && count < Max;i++){
                /*第i个数分别与它后面的数字交换就能得到新的排列,从而能够得到n!次不同排序方案
                 * （PS：此处代码中递归的执行顺序有点抽象，具体解释详见本人另一篇博客：）
                 *算法笔记_017:递归执行顺序的探讨（Java）*/
                swapArray(A,start,i);
                dfs(A,start+1,step+1,n,Max);
                swapArray(A,i,start);
            }
//        }
    }

    //交换数组中两个位置上的数值
    private void swapArray(int[] A, int p, int q){
        int temp = A[p];
        A[p] = A[q];
        A[q] = temp;
    }

    //输出数组A的序列，并输出当前行走序列所花距离，并得到已完成的行走方案中最短距离
    private void printArray(int[] A){
        for(int i = 0;i < A.length;i++)   //输出当前行走方案的序列
            System.out.print(epsName[A[i]]+" -> ");

        int tempDistance = 0;     //此处是因为，最终要返回出发地城市，所以总距离要加上最后到达的城市到出发点城市的距离
        for(int i = 0;i < A.length-1;i++)   //输出当前行走方案所花距离
            tempDistance += distance[A[i]][A[i+1]];

        if(MinDistance > tempDistance){
            //返回当前已完成方案的最短行走距离
            MinDistance = tempDistance;
            //赋予默认下一步的值,返回数组中第二个值，第一个是起始点
            nextNode = epsName[A[1]];
            for (int i = 0; i < A.length; i++) {
                ms[i] = A[i];
            }
        }


        System.out.println("  行走路程总和："+tempDistance);
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMinDistance() {
        return MinDistance;
    }

    public void setMinDistance(int minDistance) {
        MinDistance = minDistance;
    }

    public String getNextNode() {
        return nextNode;
    }

    public void setNextNode(String nextNode) {
        this.nextNode = nextNode;
    }

    public String[] getEpsName() {
        return epsName;
    }

    public void setEpsName(String[] epsName) {
        this.epsName = epsName;
    }

    public int[] getMs() {
        return ms;
    }

    public void setMs(int[] ms) {
        this.ms = ms;
    }

    public int[][] getDistance() {
        return distance;
    }

    public void setDistance(int[][] distance) {
        this.distance = distance;
    }
}


class Episode {
    private int pos_x;
    private int pos_y;
    private String episodeName;
    private boolean arrived;


    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }
}
