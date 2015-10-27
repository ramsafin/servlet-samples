import java.util.Scanner;
public class Test{

    static double result;

    static double firstNumber;

    static double secondNumber;

    static String operation;

    static Scanner sc;

    public static void main(String[] args){

        sc = new Scanner(System.in);

        System.out.println("Введите число");

        firstNumber = sc.nextDouble();

        while (!doOperaton());

    }

    public static boolean doOperaton(){

        switch (operation){

            case "+":
                System.out.println("Операция + :\nВведите второе число");
                secondNumber = sc.nextDouble();
                sum(firstNumber, secondNumber);
                break;

            case "-":
                System.out.println("Операция - :\nВведите вычитаемое ");
                secondNumber = sc.nextDouble();
                subtraction(firstNumber, secondNumber);
                break;

            case "*":
                System.out.println("Операция * :\nВведите множитель");
                secondNumber = sc.nextDouble();
                multipication(firstNumber, secondNumber);
                break;

            case "/":
                System.out.println("Операция / :\nВведите делитель");
                secondNumber = sc.nextDouble();

                if (secondNumber == 0) {
                    System.out.println("Error: division by zero");
                }else {
                    division(firstNumber, secondNumber);
                }
                break;

            case "sqr":
                System.out.println("Операция sqr - (возведение во 2 степень)");
                square(firstNumber);
                break;

            case "sin":
                System.out.println("Операция sin");
                result =  Math.sin(firstNumber);
                break;

            case "cos":
                System.out.println("Операция cos");
                result = Math.cos(firstNumber);
                break;

            case "percent":
                System.out.println("Операция percent(взятие процента) :\nВведите процент");
                secondNumber = sc.nextDouble();
                percent(firstNumber,secondNumber);
                break;

            case "log":
                System.out.println("Операция log");
                log(result);
                break;

            case "sqrt":
                System.out.println("Операция sqrt");
                sqrt(result);
                break;

            case "exit":
                System.out.println("exit...\nGood Bye!");
                return true; // конец

            //на случай, если кто-то ввел что-то не то
            default:
                System.out.println("No such operation!");
                break;
        }
        return false;

    }


    public static void sum (double firstNumber, double secondNumber){
        result = firstNumber+secondNumber;
    }


    public static void subtraction(double firstNumber, double secondNumber){
        result = firstNumber-secondNumber;
    }


    public static void multipication(double firstNumber,double secondNumber){
        result = firstNumber *secondNumber;
    }


    public static void  division(double firstNumber, double secondNumber){
        result = firstNumber/secondNumber + firstNumber%secondNumber;
    }

    public static void square(double a){
        result = a*a;
    }


    public static void pow(double a, int power){
        double res = 1;
        for (int i = 1; i <= power; i++) {
            res *= a;
        }
        result = res;
    }


    public static void log(double x){
        //натуральный логарифм
        result = Math.log(x);
    }

    public static void sqrt(double a){
        result = Math.sqrt(a);
    }


    public static void mod(double a, double b){
        result = a%b;
    }


    public static void percent(double firstNumber, double percents){
        //умножение на 0.01 выполняется быстрее, чем деление на 100
        result = secondNumber * 0.01 * firstNumber;
    }
}