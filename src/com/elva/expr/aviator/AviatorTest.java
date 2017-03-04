package com.elva.expr.aviator;

import java.util.HashMap;
import java.util.Map;

import com.elva.util.U;
import com.googlecode.aviator.AviatorEvaluator;

public class AviatorTest {
    public static void main(String args[]) throws Exception{
        Map<String,Object> env = new HashMap<String,Object>();
        env.put("praise", 10);
        env.put("cardNo", "");
        env.put("hour", 5);
        U.print(AviatorEvaluator.execute("praise >= 10 && hour == 5", env));
        U.print(AviatorEvaluator.execute("praise > 5 ", env).getClass());
        
        U.print(AviatorEvaluator.execute("cardNo != '' ", env));
        U.print(AviatorEvaluator.execute(" hour>0 && hour<10 ", env));
    }
}
