package com.onbrid.test.springboot.springboottest.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onbrid.test.springboot.springboottest.exception.JsonParsingException;
import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@Slf4j
@NoArgsConstructor
public class JsonRequestDataReader {

    private ObjectMapper mapper = new ObjectMapper();

    private HttpServletRequest request;

    Map<String, Object> paramMap = new HashMap<String, Object>();

    private Map<String, Object> dataSetMap = new HashMap<String, Object>();

    public JsonRequestDataReader(HttpServletRequest request) {
        this.request = request;
        this.init(request);
    }

    public void init(HttpServletRequest request) {

        readParams(request);

        try {
            Reader reader = request.getReader();
            if (reader != null) {
                String jsonContent = FileCopyUtils.copyToString(reader);
                if (StringUtils.hasText(jsonContent)) {
                    dataSetMap = mapper.readValue(jsonContent, HashMap.class);
                }
            }
        } catch (Exception e) {
            throw new JsonParsingException("[RequestBodyDataReader] - " + e.getMessage(), e);
        }
    }


    public OnBridOnamsData getOnBridOnamsData() {
        OnBridOnamsData onBridOnamsData = new OnBridOnamsData();

        Iterator<String> iterator = dataSetMap.keySet().iterator();
        if (dataSetMap.size() > 0){
            while (iterator.hasNext()) {
                String id = (String)iterator.next();
                List<Map> rows = getDataSetRows(id);
                if (rows.size() > 0)
                    onBridOnamsData.setList(rows);
            }
        }

        onBridOnamsData.setParamMap(map2DataSetRow(paramMap));

        return onBridOnamsData;
    }

    private void readParams(HttpServletRequest request)	{
        Map<String, String[]> requestParams = request.getParameterMap();
        boolean urlDecodePass =  "Y".equals(request.getParameter("URL_DECODE_PASS"));

        Iterator<String> it = requestParams.keySet().iterator();

        //request parameter map 을 가져와서 따로 저장.
        while (it.hasNext()) {
            String paramName = it.next();
            String[] paramValue = requestParams.get(paramName);

            if (paramValue != null) {
                try {
                    if (paramValue != null) {
                        if(paramValue.length > 1)
                        {
                            paramMap.put(paramName, paramValue);
                        }
                        else if(urlDecodePass)
                        {
                            paramMap.put(paramName, paramValue[0]);
                        }
                        else
                        {
                            paramMap.put(paramName, URLDecoder.decode(paramValue[0],"UTF-8"));
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        String requestURI = request.getRequestURI();
        paramMap.put("REQUEST_URI", requestURI);


    }


    // @SuppressWarnings("unchecked")
    private List<Map> getDataSetRows(String dataSetId) {
        List<Map> rows = new ArrayList<Map>();

        Object root = dataSetMap.get(dataSetId);

        if (Collection.class.isAssignableFrom(root.getClass())) {
            List<Object> list = (List<Object>) root;

            for (Object node : list) {
                if (!Map.class.isAssignableFrom(node.getClass())) {
                    throw new JsonParsingException("[RequestDataReader] - Invalid DataSet Format");
                }

                LinkedHashMap stringToArrayMap = new LinkedHashMap();
                stringToArrayMap.putAll((Map<String, Object>) node);

                if("LIKEIN".equals(((Map<String, Object>) node).get("val4"))){

                    if ("IN".equals(stringToArrayMap.get("val2").toString())){
                        stringToArrayMap.put("val0", stringToArrayMap.get("val0").toString() + "_IN");
                        stringToArrayMap.put("val1", stringToArrayMap.get("val1").toString() + "_IN");
                        stringToArrayMap.put("val3", stringToArrayMap.get("val3").toString().split(","));
                    }else if ("LIKE".equals(stringToArrayMap.get("val2").toString())){
                        stringToArrayMap.put("val0", stringToArrayMap.get("val0").toString() + "_LIKE");
                        stringToArrayMap.put("val1", stringToArrayMap.get("val1").toString() + "_LIKE");
                        stringToArrayMap.put("val3", stringToArrayMap.get("val3"));
                    }
                }
                if (stringToArrayMap.size() > 0)
                    rows.add(map2DataSetRow(stringToArrayMap));
            }
        } else {
            log.debug("! Collection datasetid: {}, root: {}", dataSetId, root);
            paramMap.put(dataSetId, root);
            log.debug(paramMap.toString());
            //rows.add(map2DataSetRow(((Map<String, Object>) root)));
        }

        return rows;
    }


    // @SuppressWarnings({ "rawtypes", "unchecked" })
    private Map map2DataSetRow(Map<String, Object> map) {
        Map row = new HashMap();

        Iterator<String> it = ((Map) map).keySet().iterator();

        while (it.hasNext()) {
            String columnName = it.next();
            Object columnValue = ((Map) map).get(columnName);

            if(columnValue instanceof String){
                row.put(columnName, "".equals((String)columnValue) ? null : columnValue);
            }else{
                row.put(columnName, columnValue);
            }

            row.put("SES_USERID", paramMap.get("SES_USERID"));
            row.put("SES_LANG", paramMap.get("SES_LANG"));
            row.put("SES_WHSE", paramMap.get("SES_WHSE"));
            row.put("SES_DEVISION", paramMap.get("SES_DEVISION"));
            row.put("SES_MANAGE_LVL", paramMap.get("SES_MANAGE_LVL"));
            //row.put("SES_COMPANY", paramMap.get("SES_COMPANY"));
            //row.put("SES_OWNERROLE", paramMap.get("SES_OWNERROLE"));
            //row.put("SES_WCODEROLE", paramMap.get("SES_WCODEROLE"));
            //row.put("SES_USERGROUP", paramMap.get("SES_USERGROUP"));

            //현재 작업중인 메뉴 정보를 하위 리스트에서도 자동으로 추가되게 공통 추가
            row.put("CURRENT_MENUCODE", paramMap.get("CURRENT_MENUCODE"));
        }

        return row;
    }

}
