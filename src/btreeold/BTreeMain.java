/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btreeold;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Saketh
 */
public class BTreeMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numVals = 10000000;
        BTree tree = new BTree((int) (Math.random()*numVals));
//        int[] vals = {23, 43, 65, 78, 100, 120, 150, 160, 170, 180, 190, 200, 210, 124, 543, 129, 2, 123, 665, 223, 12, 5, 43224, 98};//150, {170, 180},129
//        int[] vals = {4554,39,44,567,32,46,789,252,887,0,234,123,321,2334,4433,908,3,5,41};
//        int[] vals = {1652,1563,8033,503,6238,8402,9253,7122,3030,2392,7299,173,2218,6094,312,4192,6363,8239,743,5625,2582,8274,7874,837,6300,8289,1976,1567,8815,8942,8206,912,7858,6217,8384,9195,8236,5126,7339,2299,2380,7949,170,1078,886,6636,3423,6740};
//        int[] vals = {1652, 1563, 8033,  503, 6238, 8402, 9253, 7122, 3030, 2392, 7299,  173, 2218, 6094,  312, 4192, 6363, 8239,  743, 5625, 2582, 8274, 7874,  837, 6300, 8289, 1976, 1567, 8815, 8942, 8206,  912, 7858, 6217, 8384, 9195, 8236, 5126, 7339, 2299, 2380, 7949,  170, 1078,  886, 6636, 3423, 6740, 3533, 8147, 2566, 6831, 6774, 6559, 6060, 6804, 8679, 2284,  845, 7680, 9850, 5334, 7140, 3826,  113, 4648, 7909, 2992,  421, 1853, 2769, 5292, 1026, 9538, 2159, 2134, 4791, 2341, 7100, 5361, 3862, 2839, 1768, 7341, 4567, 3832, 5288, 1681, 6287, 7399,  615, 2038, 5973, 9433, 5444, 5990, 5490, 7624, 5025,  241, 9763, 4067, 2781, 7635, 2805, 3092, 6543, 1990, 7699, 4624, 9888, 8335, 5600, 3278,  319, 9389, 7189, 1582, 6105, 2395, 6380, 1385, 6312, 2188,  560, 9756, 8108, 6658, 4353, 2598, 5838, 5437, 5381, 9525, 4436, 4738, 1640, 5143, 6521, 3163, 2985, 4696, 7760, 3052, 8149, 8004, 1925, 9203, 1754, 1924,   64, 3169, 8659, 4694, 1214, 2961,  428, 2314, 7313, 3257, 3503, 7315, 1046, 1771,  294, 2104, 5780, 1882, 7697,  327, 2667, 1057, 5724, 4613, 6588,  459, 9051, 2953, 9335, 4638, 3207, 6946, 2897, 4039, 2306, 4722, 1710, 4460, 8987,  862, 4358, 6026, 8846, 8052, 3857, 2254, 9280, 2270,  187, 5367, 6552, 8056, 1944, 8250, 3712, 1013, 6333, 5454, 9081, 7535, 4070, 4092, 5586, 6665, 5084, 6144, 7045, 2118, 7476,  338,  849, 8214, 5095, 3078,  992, 6833, 7993, 5518, 3616,  657, 9744,  156, 5509, 7148, 2269, 8960, 4748, 5584, 9945, 9762, 7938, 9883, 2467, 8065, 3140, 8884, 1129, 4372, 9450, 2470, 6134, 2431, 3222, 9916, 4924, 7470, 5178, 8935, 9568, 6841, 9679, 4239, 1735, 2674, 6317, 8153, 7432, 5022, 7951, 1914, 2250, 8718, 1127, 6945, 5173,  389, 4645, 9920, 8880, 8690, 3402, 9109, 7665, 9814, 9878, 7976,  860, 1994, 3724,  477, 4386, 9964, 6587, 5024, 5207, 1263, 3466,  776, 7829, 1576, 4174, 8596, 2148, 5965, 3065, 8079, 5451, 6686, 5324,  931, 1473, 2483, 6555, 8164, 7116, 6218, 5664, 9906, 2292, 8132, 6827, 4784, 4754, 6469, 9597, 7038, 8311, 5902, 5399, 8044, 2425, 4825, 7281, 6720, 2500, 4860, 1624, 1861, 9868, 2852, 1333, 5959, 5844, 6911, 4385, 7082,  997, 9448, 3800, 8869, 2932, 1075, 6294, 4105, 6167, 8498, 3611, 4357, 8882, 8601, 6160, 2086, 6401, 4044, 7962, 6328, 4165, 2665, 8972, 2125, 8608, 5305, 1907,  626, 5238, 1449, 5068, 9841, 1461, 7780, 6871, 9289, 2221, 1646, 4462, 3318,  230, 2106, 8587, 8705, 8282, 5812, 7426, 4706, 4498, 5020, 6952, 2675, 3781, 2465, 4869, 1621,  528, 5680, 9567, 5198, 2249, 4779,  432, 5488, 9463, 4541, 7907, 5424, 5420, 1855, 3551, 8689, 7176, 3896, 2223, 3812, 4431, 6621, 3895, 4435, 1211, 5281, 4411, 9499, 3424, 8472, 3141, 3441, 7379,  846, 3459, 8410,  249, 7657, 6321, 9015, 3478,  365, 8834, 9134, 9212, 3887, 5177, 5762, 6789, 1819, 3190, 4758, 8548, 7996, 5318, 1594, 3347, 2501, 1960, 9154, 1539, 6835, 6396, 1575, 2508, 9521, 1592, 4087, 5772, 5803, 7929, 6541, 4002, 2505,  526, 8787,   29,  977, 9094, 1880, 7250, 1773, 8999, 3585, 7531, 2438, 9847,   81, 5540, 4148, 3138, 9819, 9385, 2487, 5302, 9137, 5847, 5601,  321, 7417, 5136, 8898, 8134, 1417, 2585, 3117, 8823, 8845, 7995, 9774, 1863, 2703, 3809, 3344, 5522, 9563, 5289, 3271, 6131, 4560, 4875, 4308, 7271, 2677, 5439, 7579, 6874, 7227, 3971, 2765, 3922, 2016, 9080, 1067, 3130, 2119, 3411, 6121, 6644, 1231, 9973, 9905, 2399, 5878, 6522, 9851, 3465, 4350, 9590, 2821, 8276, 2033, 4978, 1958, 3324, 4204, 4656, 2737, 2412, 9310, 5931, 2164, 2661, 3955, 8778, 2070, 3941,  673, 1021, 1113, 3213, 8026, 3814, 1756, 2169, 1093, 3569, 1928, 1443, 1795, 1577, 9703, 8020, 2684, 1962, 1845, 4666, 1618, 6506};
        int[] vals = new int[numVals];
        HashSet<Integer> newVals = new HashSet<>();
        ArrayList<Integer> existVals = new ArrayList<>();
        int insertNum = numVals;
        while(newVals.size() < numVals){
            int rerun = 0;
            int val = (int) (Math.random()*numVals*10);
//            vals[i] = val;
            newVals.add(val);
        }
        System.out.println("Hashset Created");
        System.out.println(newVals.size());
        
        int i=0; System.out.println("Size : "+newVals.size());
        for(Integer x:newVals){
            vals[i++] = x;
        }
        long time = 0;
        long tempT=0, tempT2=0;
        int counter = 0; boolean insert = false;
        for (int x : vals) {
//        for(int x=0;x<numVals;x++){
            ++counter;
//            if(counter%1000 == 0) System.out.println(counter+"th number = "+x);
            tempT = System.currentTimeMillis();
            tree.insert(x);
            tempT2 = System.currentTimeMillis();
            time+=(tempT2 - tempT);
//            if(!tree.insert(x)){
//                existVals.add(x);
//                --insertNum;
//            }else{
////                System.out.println("The value " + x + " is being inserted");
//            }
//            tree.printBTree();
        }
        int val;
        System.out.println("B-Tree created. Time taken = "+((float)time/1000));
        boolean temp = false; boolean noFalse = true;
        for (int x : vals) {
            temp = tree.searchVal(x);
            if(!temp){
                noFalse = false;
                System.out.println("The result for value " + x + " is " + temp);
            }
        }
        if(noFalse) System.out.println("No falses!!!");
        System.out.println("Highest Level : "+tree.getHighestLevel());
        System.out.println("Nums inserted : "+insertNum);
//        tree.printBTree();
        Scanner in = new Scanner(System.in);
//        while (true) {
//            System.out.print("Search for something : ");
//            val = in.nextInt();
//            System.out.println("The result for value " + val + " is " + tree.searchVal(val));
//        }
    }

}
