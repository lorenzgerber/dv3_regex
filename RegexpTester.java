import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/*
 * Class RegexpTester
 * Author: henrikb
 * Usage: java RegexpTester <regexp file> <input file>
 * Functionality: Compiles the union of the regexps found in the regexp file and
 *   prints all matches in the input file.
 */

public class RegexpTester {

    public static void main(String[] args) {
	// Check that regexp and input file names are there
	if(args.length < 2){
	    System.out.println("Usage: java RegexpTester <regexp file> <input file>");
	    System.exit(0);
	}
	// Get the file names
	String regexpFileName = args[0];
	String inputFileName = args[1];

	String line = null;
	String inputText = "";
	String regexp = "";
	try {
	    // open the files
	    FileReader inputFileReader = new FileReader(inputFileName);
	    BufferedReader inputBufferedReader = new BufferedReader(inputFileReader);

	    FileReader regexpFileReader = new FileReader(regexpFileName);
	    BufferedReader regexpBufferedReader = new BufferedReader(regexpFileReader);

	    // get the input text as a single string
	    while((line = inputBufferedReader.readLine()) != null) {
		inputText += line;
		inputText += '\n';
	    }

	    // get all the regexps and put them into a single string, separated by |
	    line = regexpBufferedReader.readLine();
	    if(line != null){
		regexp = line;
	    }
	    while((line = regexpBufferedReader.readLine()) != null) {
		regexp += "|";
		regexp += line;
	    }

	    // compile the regexp
	    Pattern pattern = Pattern.compile(regexp);

	    // construct a matcher for the input text
	    Matcher matcher = pattern.matcher(inputText);

	    // find all matches and output them
	    while(matcher.find()){
		System.out.println(matcher.group());
	    }
	    // close files.
	    inputBufferedReader.close();
	    regexpBufferedReader.close();
	}
	catch(FileNotFoundException e) {
	    System.out.println("Unable to open file");
	}
	catch(IOException e) {
	    System.out.println("Error reading file");
	}
    }
}
