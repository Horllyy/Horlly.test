package Recursive;

/**
 * 这个文件夹里就都是讲递归
 */
public class BuildTowers {
    public int floor=0;

    public BuildTowers(){ }

    public BuildTowers(int floor){
        this.floor=floor;
    }

//    public void buildTower(){
//        int floorCopy=floor;//每次方法调用在会方法内部int方法自己的内部独有变量 在递归过程中 递归几次 就新int几层变量
//        //int到方法外部 类中 结果就完全不一样 是个类变量 每层递归方法共有的了
//        if(floorCopy>1) {
//            floor--;
//            this.buildTower();
//        }
//        System.out.println("姐妹!这是第"+floorCopy+"层宝塔!");
//    }

    public void buildTower(int floor){
        if(floor>1) {
            this.buildTower(floor-1);
        }
        System.out.println("姐妹!这是第"+floor+++"层宝塔!");
    }


    public static void main(String[] args) {
//                BuildTowers bt=new BuildTowers(5);
//                bt.buildTower();

        BuildTowers bt=new BuildTowers();
        bt.buildTower(5);
    }
}
//加上我注释掉的一部分 两种方法都可以