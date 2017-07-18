package tyrant.common.enums;

/**
 * Created by zhangli on 18/7/2017.
 */
public enum TestcaseType {

    UT(2),
    WS(0),
    UI(1);

    private final Integer type;

    TestcaseType(Integer type){
        this.type = type;
    }

    public Integer type(){
        return type;
    }

    /*
    UT{
        public Integer getTestcaseType(){
            return 2;
        }
    },
    WS{
        public Integer getTestcaseType(){
            return 0;
        }
    },
    UI{
        public Integer getTestcaseType(){
            return 1;
        }
    };

    abstract public Integer getTestcaseType();
    */

}
