import ngordnet.WordLengthProcessor;
import ngordnet.YearlyRecord;

public class WordLengthProcessorDemo {
    public static void main(String[] args) {
        YearlyRecord yr = new YearlyRecord();
        yr.put("sheep", 100);
        yr.put("dog", 300);
        WordLengthProcessor wlp = new WordLengthProcessor();
        
        // Since sheep appears 100 times and has length 5
        // and dog appears 300 tiems and has length 3
        // the average length in this year was 3.5

        System.out.println(wlp.process(yr)); //prints 3.5
    }
} 
