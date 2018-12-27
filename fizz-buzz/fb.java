
public class fb{



        public static void main(String[] args){


                for(int i = 1; i < 101; i++){

                        int temp = 0;

                        if(i % 3 == 0){
                                System.out.print("Fizz");
                                temp = 1;
                        }

                        if(i % 5 == 0){
                                System.out.print("Buzz");
                                temp = 1;
                        }

                        if(temp == 0){
                                System.out.print(i);
                        }


                        System.out.println();

                }




        }


}
