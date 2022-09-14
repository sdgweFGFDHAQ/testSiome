package testDesign;

import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.Map;

public class TransformFactory {
    public String parse(String data) {
        return data.replaceAll("!@#$%^&*", "").trim();
    }

    public String parse(Map<Object, Object> data) {
        for (Object key : data.keySet()){
            if(ObjectUtils.isEmpty(key)){
                data.remove(key);
            }
        }
        return data.toString();
    }

    public String parse(List<Object> data) {
        for (int i = 0; i < data.size(); i++) {
            if(ObjectUtils.isEmpty(data.get(i))){
                data.remove(data.get(i));
            }
        }
        return data.toString();
    }

}
