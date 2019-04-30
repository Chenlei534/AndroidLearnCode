package com.example.kotlinlearn

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    //    可变变量
    var a:String="string";
//    不可变变量
    val c:Int = 0
    val d=2;
//    $表示一个变量
    val s1="this is $c";
//    ?表示可以为空
    var s2:String?="23";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        tv.text="123";
    }

    fun test1(){
        var a=100;
        var b=100;
//        == 比较两个对象值是否相等
        if(a==b){
            println("a=b");
        }
//        === 比较两个对象地址是否相等
        if(a===b){
            println("是同一个对象")
        }
    }

    fun test(){
//        is判断是否是某个类型
//        无类型的会被转换为is后面的类型
        if(s1 is String){
            println("s1 is String")
        }
//        区间，可用来循环
        for(i in 1..4){
            println(i);
        }
//        downTo
        for(i in 4 downTo 1){
            println(i);//输出4 3 2 1
        }
//        step指定步长
        for(i in 1..4 step 2){
            println(i);//输出1 3
        }
//        util排除最后一个值
        for(i in 1 until 10){
            println(i);//输出[1,10)
        }
    }
    //返回Int型
    fun sum(a:Int,b:Int):Int{
        return a+b;
    }
    //    public需要声明返回类型，无返回类型
    public fun printSum(a:Int,b:Int):Unit{
        println(a+b);
    }

    fun vars(vararg v:Int){
        for(vv in v){
            println(vv);
        }
    }
    //  lambda表达式
    fun lambdaTest(){
        val sumLambda:(Int,Int)->Int={x,y->x+y};
        println(sumLambda(1,2));
    }


}

