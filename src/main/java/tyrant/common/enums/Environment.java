package tyrant.common.enums;

/**
 * Created by zhangli on 18/7/2017.
 */
public enum Environment {

    GET{
        public String getEnvironment(String testcaseName){
            if (testcaseName.toLowerCase().contains("test")){
                return "test";
            }else if(testcaseName.toLowerCase().contains("dev")){
                return "dev";
            }else if(testcaseName.toLowerCase().contains("stage")){
                return "stage";
            }else if(testcaseName.toLowerCase().contains("product")){
                return "product";
            }else {
                return testcaseName;
            }
        }
    };
//    ,DEV,STAGE,PRODUCT;



    abstract public String getEnvironment(String testcaseName);

}
