package spring3.system;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class Container<T> {
    private List<T> objs = new ArrayList<T>();

    @Resource(name = "bmiMeasure")
    private Measure measure;
    @Resource(name = "bmiFilter")
    private ContainerFilter filter;

    private T max;
    private T min;
    private double avg;
    private double sum;

    /**
     * 添加对象的方法
     */
    public void add(T t){
        if (filter!=null){
            if (filter.doFilter(t) == false){
                return;
            }
        }
        //添加到objs
        objs.add(t);
        //判断大小，记录max,min, 计算avg
        if (objs.size()==1){
            max = t;
            min = t;
        }else {
            double val = this.measure.doMeasure(t);
            double maxval = this.measure.doMeasure(max);
            double minval = this.measure.doMeasure(min);
            if (val>maxval){
                max = t;
            }
            if (val<minval){
                min = t;
            }
        }
        sum += measure.doMeasure(t);
        avg = sum/objs.size();
    }

    public T getMax(){
        return max;
    }

    public T getMin(){
        return min;
    }

    public double getAvg(){
        return avg;
    }

    /**
     * 有效的测量，对象有多少个
     */
    public int size(){
        return objs.size();
    }

    /**
     * 系统复位
     */
    public void clearAll(){
        objs = new ArrayList<T>();
        measure = null;
        filter = null;
        max = null;
        min = null;
        avg = 0;
    }
}
