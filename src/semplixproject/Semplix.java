/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package semplixproject;



/**
 *
 * @author Hassan juniedi
 */
public class Semplix {
private    double[][] AllNums;
private    double[] constans;
public    boolean negative=false;
private  int   focalrow=0;
private int focalcolumn=0;
private double  focalNum=0;

private String[] header;
private String[][] data;


public Semplix(double[][] AllNums,double[] constans,String[][]data,String[]header){
    this.AllNums=AllNums;
    this.constans=constans;
    this.data=data;
    this.header=header;
}

  public double MinNum(double n1,double n2){
            if (n1<n2) return n1;    
             else return n2;
  }


  public double MaxNum(double n1,double n2){
      if(n1>n2) return n1;
      else
          return n2;

  }

  public int GetIndex(double[] focal){
      int n=0,p=0;
      double max =focal[0];
      double min=0;
      for(int i=0;i<focal.length;i++){
          if(focal[i]>0)
            min =focal[i];  
                 
      }

       int index=0;
      for(int i=0;i<focal.length;i++){
          if(focal[i]<0)
              n++;
          if(focal[i]>0)
              p++;
      }
      if(n==focal.length){
          for(int i=0;i<focal.length;i++){
              max=MaxNum(max,focal[i]);
      }
          for(int i=0;i<focal.length;i++){
          if(max==focal[i])
              index=i;
      }
      
      return index;
      }


      if(n==focal.length-1){
          for(int i=0;i<focal.length;i++){
              if(focal[i]>=0){
                  index= i;
              max=focal[i];}


          }
           return index;
      }
 else
     for(int i=0;i<focal.length;i++){
         if(focal[i]>=0)
             min=MinNum(min, focal[i]);}
      for(int i=0;i<focal.length;i++){
          if(min==focal[i])
              index=i;
      }
     // System.out.println(index+" "+min);
return index;

  }

   public void OblongRule2(){//قاعد المستطيل هنا يتم التغيرات على المصفوفة
      for(int j=0;j<AllNums.length;j++){
          if(j==focalrow) continue;

          constans[j]=((constans[j]*focalNum)-(AllNums[j][focalcolumn]*constans[focalrow]))/focalNum;
       }
           for(int j=0;j<AllNums.length;j++){
          if(j==focalrow) continue;
          for(int i=0;i<AllNums[1].length;i++){
          if(i==focalcolumn) continue;

          AllNums[j][i]=((AllNums[j][i]*focalNum)-(AllNums[j][focalcolumn]*AllNums[focalrow][i]))/focalNum;
                    }
      
      }

      constans[focalrow]=constans[focalrow]/focalNum;
         

          for(int i=0;i<AllNums[1].length;i++){
          if(i==focalcolumn) continue;
          AllNums[focalrow][i]=AllNums[focalrow][i]/focalNum;

      }
       for(int i=0;i<AllNums.length;i++){
          if(i==focalrow) continue;
          AllNums[i][focalcolumn]=-1*(AllNums[i][focalcolumn]/focalNum);
      }
      AllNums[focalrow][focalcolumn]=1/focalNum;

  }
  
  public boolean NegativeTest(){//يرد لنا قيمتين بوليان للدلالة على وجود سالب في مصفوفة الثوابت
     double min=constans[0];//ودليل السطر الي يحوي العنصر الاكثر سالبية في الثوابت
      for(int i=0;i<constans.length-1;i++){
            min=MinNum(min, constans[i]);
      }
     if(min<0){
         for(int i=0;i<constans.length-1;i++){
             if(min==constans[i]){
                focalrow=i;
                 break;}
         }
         
    return true;}

else

    return false;
    }


    
  public boolean CTest(){//يختبر اذا كان جميع عناصر تابع الهدف سالبة اما لا
      boolean s=true;
      int j=AllNums.length-1;//عناصر تابع الهدف (سطر تابع الهدف)
      double max=AllNums[j][0];
      
      for(int i=0;i<AllNums[1].length;i++){

              max=MaxNum(max, AllNums[j][i]);}
      if(max<0){
          s= true;
      return s;}
      else
          s= false;
      return s;
  }
  public int BiggestPositiveNum(){
      double max=0;//AllNums[AllNums.length-1][0];
      int index=0;
      for(int i=0 ;i<AllNums[1].length;i++){
          max=MaxNum(max , AllNums[AllNums.length-1][i] );
      }
      for(int i=0 ;i<AllNums[1].length;i++){
         if(max== AllNums[AllNums.length-1][i]){
             index= i;
             break;}
      }
     // System.out.println("Max= "+max);
      return index;
  }

  public void WithNegativeB (){
  //int column = AllNums[1].length;
  int row = AllNums.length;

  double focal[]=new double[row-1];
          //      while(stopcheck==false){
                    //System.out.println(stopcheck);
                    focalcolumn=BiggestPositiveNum();

                   for(int i=0;i<row-1;i++){
                        focal[i]=constans[i]/AllNums[i][focalcolumn];
                   }

                focalrow=GetIndex(focal);
                focalNum=AllNums[focalrow][focalcolumn];

String s;
                 s = header[focalcolumn+1];//"y"+(focalrow+1);
                  header[focalcolumn+1]=data[focalrow][0];
                   data[focalrow][0]=s;//"x"+(focalcolumn+1);

                   OblongRule2();

        //  stopcheck=StopCheck();
        //  t++;
          // stopcheck=true;
  //  }


  }

  public void WithOutNegativeB(){

      int row = AllNums.length;
       double focal[]=new double[row-1];
          //      while(stopcheck==false){
                    //System.out.println(stopcheck);
                    focalcolumn=BiggestPositiveNum();

                   for(int i=0;i<row-1;i++){
                        focal[i]=constans[i]/AllNums[i][focalcolumn];
                   }

                focalrow=GetIndex(focal);
                focalNum=AllNums[focalrow][focalcolumn];

String s;
                 s = header[focalcolumn+1];//"y"+(focalrow+1);
                  header[focalcolumn+1]=data[focalrow][0];
                   data[focalrow][0]=s;//"x"+(focalcolumn+1);

                   OblongRule2();

        //  stopcheck=StopCheck();
        //  t++;
          // stopcheck=true;
  //  }



  }

public void GetSolution(){


 boolean Negative_C=true;
  
          negative= NegativeTest();
          Negative_C=CTest();
                if(negative==true){
                    WithNegativeB();

                    negative= NegativeTest();
                    Negative_C=CTest();

                if(negative==false && Negative_C==false)
                    WithOutNegativeB();
                }

   if(negative==false && Negative_C==false){
     WithOutNegativeB();

       Negative_C=CTest();
       negative=NegativeTest();

       if(Negative_C==true && negative==true){
           WithNegativeB();
      

                }}

}

    
public double[][] GetAllnum(){
    for (int i=0 ; i< AllNums.length ; i++){
        for(int j=0;j<AllNums[0].length;j++){
            System.out.print(AllNums[i][j]+"  ");

        }
        System.out.println();
    }

    return AllNums;
}
public double[] GetConst(){
    for(int i=0;i<AllNums.length;i++){
       System.out.print(constans[i]+"  ");
    }
    return constans;
}

 

}














