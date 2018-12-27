#include <stdio.h>



int main(){

        for(int i  = 1; i <= 100; i++){

                int output = 0;
                if(i%3 == 0){
                        printf("Fizz");
                        output=1;
                }

                if(i%5 == 0){
                        printf("Buzz");
                        output = 1;
                }

                if(output == 0){
                        printf("%i", i);
                }

                printf("\n");

        }

}


