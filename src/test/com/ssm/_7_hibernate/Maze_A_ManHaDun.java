package com.ssm._7_hibernate;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * A*算法---迷宫中寻找最短路径
 *
 * @author fh
 */
public class Maze_A_ManHaDun {
    private int[][] map = null;
    private List<Node> openList;// 开启列表
    private List<Node> closeList;// 关闭列表
    private List<Integer> resList;//返回路经list

    private static int[][] boomArieas;
    private static int time;//传入轰炸区倒计时  2s一回合



    public static void main(String[] args) {
        Maze_A_ManHaDun maze_a_manHaDun = new Maze_A_ManHaDun();
        long l = System.currentTimeMillis();
        int[] ints = maze_a_manHaDun.queryNextNode(0, 0, 128, 101);
        long r = System.currentTimeMillis();
        System.out.println("耗时："+(r - l));
    }

    public Maze_A_ManHaDun() {
        initMap();
    }

    //将障碍物设置到二维数组中
//    public Maze_A_ManHaDun(List<MazeGridPoint> roadblock) {
//        initMap();
//        if (null != roadblock) {
//            for (int i = 0; i < roadblock.size(); i++) {
//                map[roadblock.get(i).getPos_x()][roadblock.get(i).getPos_y()] = 0;
//            }
//        }
//    }

    //初始化地图，0表示障碍，1代表通路
    private void initMap() {
        time = 1;
        boomArieas = new int[1][2];
        boomArieas[0][0] = 2;
        boomArieas[0][1] = 2;
        resList = new ArrayList<Integer>();
        openList = new ArrayList<Node>();
        closeList = new ArrayList<Node>();
        map = new int[192][108];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
    }

    public int[] queryNextNode(int x1, int y1, int x2, int y2) {
        int Max_row = 192;
        int MAX_col = 108;
        Node startPoint = new Maze_A_ManHaDun().new Node(x1, y1, null);
        Node endPoint = new Maze_A_ManHaDun().new Node(x2, y2, null);
        seachWay(map, startPoint, endPoint, Max_row, MAX_col);
        int[] resDp = new int[2];//返回路经list
        if (null != resList && resList.size() > 1) {
            resDp[0] = resList.get(resList.size() - 2);
            resDp[1] = resList.get(resList.size() - 1);
        } else {
            resDp[0] = x1;
            resDp[1] = y1;
        }
        return resDp;
    }


    /**
     * 搜寻最短路径
     *
     * @param arr
     * @param startPoint
     * @param endPoint
     */
    private boolean seachWay(int[][] arr, Node startPoint,
                             Node endPoint, int row, int col) {
//        final int CONST_HENG = 10;// 垂直方向或水平方向移动的路径评分
//        final int CONST_XIE = 14;// 斜方向移动的路径评分移动的路径评分
        final int CONST_HENG = 1;// 垂直方向或水平方向移动的路径评分
        final int CONST_XIE = 1;// 斜方向移动的路径评分
        Node curNode = startPoint;
        if (startPoint.x < 0 || startPoint.y > col || endPoint.x < 0
                || endPoint.y > col || arr[startPoint.x][startPoint.y] == 0
                || arr[endPoint.x][endPoint.y] == 0) {
            throw new IllegalArgumentException("坐标参数错误！！");
        }

        openList.add(startPoint);
        while (!openList.isEmpty() && !openList.contains(endPoint)) {
            curNode = minList(openList);
            if (curNode.x == endPoint.x && curNode.y == endPoint.y
                    || openList.contains(endPoint)) {
                System.out.println("找到最短路径："+openList.size());

                while (!(curNode.x == startPoint.x && curNode.y == startPoint.y)) {
                    resList.add(curNode.x);
                    resList.add(curNode.y);
                    map[curNode.x][curNode.y] = 2;
                    if (curNode.parentNode != null) {
                        System.out.println(curNode.toString());
                        curNode = curNode.parentNode;
                    }
                }
                System.out.print("最短路经起点:(" + startPoint.x + "," + startPoint.y + ")\n ");
                map[startPoint.x][startPoint.y] = 2;
                return true;
            }
// 上
            if (curNode.y - 1 >= 0) {
                checkPath(curNode.x, curNode.y - 1, curNode, endPoint,
                        CONST_HENG);
            }
// 下
            if (curNode.y + 1 < col) {
                checkPath(curNode.x, curNode.y + 1, curNode, endPoint,
                        CONST_HENG);
            }
// 左
            if (curNode.x - 1 >= 0) {
                checkPath(curNode.x - 1, curNode.y, curNode, endPoint,
                        CONST_HENG);
            }
// 右
            if (curNode.x + 1 < row) {
                checkPath(curNode.x + 1, curNode.y, curNode, endPoint,
                        CONST_HENG);
            }
// 左上
            if (curNode.x - 1 >= 0 && curNode.y - 1 >= 0) {
                checkPath(curNode.x - 1, curNode.y - 1, curNode, endPoint,
                        CONST_XIE);
            }
// 左下
            if (curNode.x - 1 >= 0 && curNode.y + 1 < col) {
                checkPath(curNode.x - 1, curNode.y + 1, curNode, endPoint,
                        CONST_XIE);
            }
// 右上
            if (curNode.x + 1 < row && curNode.y - 1 >= 0) {
                checkPath(curNode.x + 1, curNode.y - 1, curNode, endPoint,
                        CONST_XIE);
            }
// 右下
            if (curNode.x + 1 < row && curNode.y + 1 < col) {
                checkPath(curNode.x + 1, curNode.y + 1, curNode, endPoint,
                        CONST_XIE);
            }

            openList.remove(curNode);
            closeList.add(curNode);
        }
// if (!openList.contains(endPoint)) {
// System.out.println("一条路径都未找到！！！");
// return false;
// }

        return false;

    }

    // 核心算法---检测节点是否通路
    private boolean checkPath(int x, int y, Node preNode, Node endPoint,
                              int c) {
        Node node = new Maze_A_ManHaDun().new Node(x, y, preNode);
// 查找地图中是否能通过
        if (map[x][y] == 0) {
            closeList.add(node);
            return false;
        }
// 查找关闭列表中是否存在
        if (isListContains(closeList, x, y) != -1) {// 存在
            return false;
        }
// 查找开启列表中是否存在
        int index = -1;
        if ((index = isListContains(openList, x, y)) != -1) {// 存在
// G值是否更小，即是否更新G，F值
            if ((preNode.g + c) < openList.get(index).g) {
                countG(x,y,node, endPoint, c);
                countF(node);
                openList.set(index, node);
            }
        } else {
// 不存在，添加到开启列表中
            node.setParentNode(preNode);
            count(x,y,node, endPoint, c);
            openList.add(node);
        }
        return true;
    }

    // 计算G,H,F值
    private static void count(int x,int y,Node node, Node eNode, int cost) {
        countG(x,y,node, eNode, cost);
        countH(x,y,node, eNode);
        countF(node);
    }

    // 计算G值
    private static void countG(int x,int y,Node node, Node eNode, int cost) {
        int x1 = node.getX();
        int y1 = node.getY();
        int g = node.getG();

        if (node.getParentNode() == null) {
            node.setG(cost);
        } else {
            //计算G值，若父节点未计算过雷区，则需要计算在雷区停留的时间节点
            if (g + cost >= time / 2 && !node.parentNode.isFirstDown) {
                for (int i = 0; i < boomArieas.length; i++) {
                    if (node.getX() == boomArieas[i][0] && node.getY() == boomArieas[i][1]) {
                        cost = 30;
                    }
                }
                //若满足进入雷区条件，则将当前节点的状态设置为已经进入过雷区
                node.setFirstDown(true);
            }
            //若父节点是已经计算过雷区了，则将该状态传给子节点
            if (node.parentNode.isFirstDown) {
                node.setFirstDown(true);
            }
            node.setG(node.getParentNode().getG() + cost);
        }
    }

    // 计算H值
    private static void countH(int x,int y,Node node, Node eNode) {
        node.setF((Math.abs(node.getX() - eNode.getX()) + Math.abs(node.getY()
                - eNode.getY())) * 10);
    }

    // 计算F值
    private static void countF(Node node) {
        node.setF(node.getG() + node.getH());
    }

    // 集合中是否包含某个元素(-1：没有找到，否则返回所在的索引)
    private static int isListContains(List<Node> list, int x, int y) {
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            if (node.getX() == x && node.getY() == y) {
                return i;
            }
        }
        return -1;
    }

    // 找最小值
    private static Node minList(List<Node> list) {
        Iterator<Node> i = list.iterator();
        Node candidate = i.next();

        while (i.hasNext()) {
            Node next = i.next();
            if (next.compareTo(candidate) < 0)
                candidate = next;
        }
        return candidate;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public List<Node> getOpenList() {
        return openList;
    }

    public void setOpenList(List<Node> openList) {
        this.openList = openList;
    }

    public List<Node> getCloseList() {
        return closeList;
    }

    public void setCloseList(List<Node> closeList) {
        this.closeList = closeList;
    }

// public int[] getResDp() {
// return resDp;
// }
//
// public void setResDp(int[] resDp) {
// this.resDp = resDp;
// }

    public List<Integer> getResList() {
        return resList;
    }

    public void setResList(List<Integer> resList) {
        this.resList = resList;
    }

    // 节点类
    private class Node {
        public boolean isFirstDown;
        private int x;// X坐标
        private int y;// Y坐标
        private Node parentNode;// 父类节点
        private int g;// 当前点到起点的移动耗费
        private int h;// 当前点到终点的移动耗费，即曼哈顿距离|x1-x2|+|y1-y2|(忽略障碍物)
        private int f;// f=g+h

        public Node(int x, int y, Node parentNode) {
            this.x = x;
            this.y = y;
            this.parentNode = parentNode;
        }

        public int compareTo(Node candidate) {
            return this.getF() - candidate.getF();
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Node getParentNode() {
            return parentNode;
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }

        public int getG() {
            return g;
        }

        public void setG(int g) {
            this.g = g;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        public int getF() {
            return f;
        }

        public void setF(int f) {
            this.f = f;
        }

        public String toString() {
            return "(" + x + "," + y + "," + f + ")";
        }

        public boolean isFirstDown() {
            return isFirstDown;
        }

        public void setFirstDown(boolean firstDown) {
            isFirstDown = firstDown;
        }
    }

    // 节点比较类
    class NodeFComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.getF() - o2.getF();
        }

    }

}
