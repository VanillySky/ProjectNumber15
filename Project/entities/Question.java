package entities;

public class Question {
	
	private static long QuestionNumber;
	private static long QuestionCode;
	private static String Question;
	private static String Course;
	private static String QuestionInstruction;
	private static String Answer1;
	private static String Answer2;
	private static String Answer3;
	private static String Answer4;
	private static String RightAnswer;
	private static String Author;
	
	
	//////////////////// its should be the subject not the course !!! like math 
	//Algebra,//01
	//Geometry,//02
	//Logica,//03
	//Physics1,//04
	//Physics2,//05
	//Geography,//06
	//Chemistry;//07
	
	
	public void Exam( long QuestionNumber,String Course,
			String Question,
	 String QuestionInstruction,String Answer1,
	 String Answer2,String Answer3, String Answer4,String RightAnswer ) {
		String Temp;
		QuestionNumber=QuestionNumber;
		Course=Course;
		Question=Question;
		QuestionInstruction=QuestionInstruction;
		Answer1=Answer1;
		Answer2=Answer2;
		Answer3=Answer3;
		Answer4=Answer4;
		RightAnswer=RightAnswer;
		switch (Course) {
		case("Algebra"):{QuestionNumber=Long.parseLong("01" + QuestionNumber);}
		case("Geometry"):{QuestionNumber=Long.parseLong("02" + QuestionNumber);}
		case("Logica"):{QuestionNumber=Long.parseLong("03" + QuestionNumber);}
		case("Physics1"):{QuestionNumber=Long.parseLong("04" + QuestionNumber);}
		case("Physics2"):{QuestionNumber=Long.parseLong("05" + QuestionNumber);}
		case("Geography"):{QuestionNumber=Long.parseLong("06" + QuestionNumber);}
		case("Chemistry"):{QuestionNumber=Long.parseLong("07" + QuestionNumber);}
		}
		//Author=;
			
	}
	

}
