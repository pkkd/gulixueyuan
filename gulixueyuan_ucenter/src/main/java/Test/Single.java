package Test;

public class Single {

        private Single(){};
        private static Single single = new Single() ;

        public static Single getSingle (){
            return single;
        }

}

