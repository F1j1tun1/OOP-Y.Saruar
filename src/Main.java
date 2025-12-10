import static java.lang.IO.println;

void main() {
    println(String.format("Type any number!"));
    Scanner sc = new Scanner(System.in);
    int x = sc.nextInt();

    for (int i = 1; i <= x; i++) {
        println("x = " + i);
    }
}
