public class Test {


    public static void main(String[] args) {

        a();

    }

    private static void a() {
        try{
            System.out.println();
        }catch (Exception e){
            System.out.println(22);
        }

    }

    private static void e()  {
        try{
            int a = 1/0;
        }catch (Exception e){
            System.out.println(33);
            //throw new RuntimeException();
        }finally {
            System.out.println(55);
        }



    }
}
