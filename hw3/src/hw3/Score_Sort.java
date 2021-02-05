package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.lang.String;

public class Score_Sort{
    static String[][] data = new String [500][4];
    static node[] node = new node[500];

    public static void main(String[] args) throws IOException {
        rdFile();//讀txt檔
	CountTotal();//算總分
        //BubbleSort();//氣泡排序
	InsertionSort();//選擇排序 
        System.out.println("學號\t總分\t國文\t英文\t數學");
	for(node n:node) {
            System.out.println(n.name+"\t"+n.total+"\t"+n.chinese+"\t"+n.english+"\t"+n.math);
		}
    }
    private static void BubbleSort() {
	while(true) {
            boolean bool = false;
		for(int i = 0 ;i<node.length-1;i++) {
                    if(node[i].total<node[i+1].total) { //總分可比則互換
			swapp(i,i+1);
			bool=true;
                    }else if(node[i].total== node[i+1].total) {//總分相同比國文
			if(node[i].chinese<node[i+1].chinese) { //國文可比則互換
                            swapp(i,i+1);
                            bool=true;
			}else if (node[i].chinese == node[i+1].chinese) {//國文相同比英文
                            if(node[i].english<node[i+1].english) { //英文可比則互換
                            swapp(i,i+1);
                            bool=true;
                            }else if (node[i].english==node[i+1].english) {//英文相同比數學
				if(node[i].math<node[i+1].math) {//數學可比則互換
                                    swapp(i,i+1);
                                    bool=true;
				}
                            }
			}
                    }
		}
		if(!bool) {
                    break;
                }
	}		
    }
    
    private static void CountTotal() {
	int num=0;
	for(String[] data:data) {
            int chinese = Integer.parseInt(data[1]);
            int english = Integer.parseInt(data[2]);
            int math = Integer.parseInt(data[3]);
            
            node n = new node(data[0],chinese,english,math);
            node[num] = n;
            num++;
	}
    }    
    
    private static void InsertionSort(){
        for(int fu=1;fu<node.length;fu++){
            int curTotal=node[fu].total;
            int curChi=node[fu].chinese;
            int curEng=node[fu].english;
            int curMath=node[fu].math;
            int place;
            for(place=fu-1;place >= 0; place--){
                if(node[place].total>curTotal){   
                    break;//前總分大於後總分則結束
                } 
                else if(node[place].total==node[place+1].total){//前總分等於後總分則做下面的
                    if(node[place].chinese>curChi){
                        break;//前中文大於後中文則結束
                    }
                    else if(node[place].chinese==node[place+1].chinese){//前中文等於後中文則做下面的
                        if(node[place].english>curEng){
                            break;//前英文大於後英文則結束
                        }
                        else if(node[place].english==node[place+1].english){//前英文等於後英文則做下面的
                            if(node[place].math>curMath){
                                break;//前數學大於後數學則結束
                            }swapp(place,place+1);//前數學小於後數學則換
                        }
                        else{
                            swapp(place,place+1);//前英文小於後英文則換
                        }
                    }
                    else{
                        swapp(place,place+1);//前中文小於後中文則換
                    }
                }
                else {
                    swapp(place,place+1);//前總分小於後總分則換
                }       
            }
        }
    }

    private static void swapp(int i, int j) {
	node tem = node[i];
	node[i] = node[j];
	node[j] = tem;
    }
    
    public static void rdFile() {
        try {
            File f = new File("D:\\scores.txt");
            Scanner scn = new Scanner(f);
            int a=0;
            while(scn.hasNextLine()) {
		String scores = scn.nextLine();
		data[a] = scores.split(",");
		a++;
            }
            scn.close();
	}catch(FileNotFoundException e){
            System.out.println("Error occured");
            e.printStackTrace();
	}
    }    
}
