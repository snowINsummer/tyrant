package tyrant.common.enums;

/**
 * Created by zhangli on 18/7/2017.
 */
public enum TestcaseType {

    UT(2),
    WS(0),
    UI(1);

    private final int type;

    TestcaseType(int type){
        this.type = type;
    }

    public int type(){
        return type;
    }

    /*
    UT{
        public int getTestcaseType(){
            return 2;
        }
    },
    WS{
        public int getTestcaseType(){
            return 0;
        }
    },
    UI{
        public int getTestcaseType(){
            return 1;
        }
    };

    abstract public int getTestcaseType();
    */

}
