/**
*	@author Brendan Raimann
*	1/6/15
*	Version 0.7
*	A version of 20 (or more) Questions using a Binary Tree
*/

import java.io.*;
import java.util.Scanner;

public class Questions
{
	/** For local storing of the text document */
	private static String data;
	/** A helper for building the data from the text document
	*	Provides pointers for all of the questions */
	private static LinkedList<BinaryTree<String>> parents;
	
	/**
	*	Main method that runs the game
	*/
	public static void main(String[] args)
	{
		BinaryTree<String> root = new BinaryTree<String>();
		parents = new LinkedList<BinaryTree<String>>();
		
		//root.setValue("Is it alive?");
		//root.setLeft(new BinaryTree<String>("Billy Long")); //LEFT == YES
		//root.setRight(new BinaryTree<String>("Chair"));     //RIGHT == NO
		
		data = read();
		buildData(root);
/*
		System.out.println("1 " + data);
		System.out.println("2 " + root);
		System.out.println("3 " + root);
		System.out.println("4");
		for (String x: root)
			System.out.println(x);
	

Is it alive?(Second Question?(A,Third Question?(A,B)),chair) 

		Is it alive?
	     /      \
	Second Q	chair
	  /  \
	A	Third Q
		  /  \
		A	  B
*/
		
		BinaryTree<String> curr = root;					//Game starts here
		Scanner keyboard = new Scanner(System.in);
		while (curr.isLeaf() != true)
		{
			System.out.println("\n" + curr.value);
			String answer = keyboard.nextLine();
			if (answer.equalsIgnoreCase("yes"))
			{
				curr = curr.left();
			}
			else
			{
				curr = curr.right();
			}
		}
		System.out.println("\nYou were thinking of:  " + curr + "\n\n Was I correct?");
		String correct = keyboard.nextLine();
		if (correct.equalsIgnoreCase("no"))
		{
			System.out.println("\nWhat were you thinking of?");
			String answer = keyboard.nextLine();
			System.out.println("\nGive me a question that would help me figure that out (Answering YES should get me to the answer).");
			String question = keyboard.nextLine();
			System.out.println("\nThanks!\n\n");
			
			String temp = curr.value();
			curr.setValue(question);
			curr.setLeft(new BinaryTree<String>(answer));
			curr.setRight(new BinaryTree<String>(temp));
			
			rewrite(root);
		}
		else
			System.out.println("\nThat's what I thought.");
	}
	
	
	public static void buildData(BinaryTree<String> node)
	{
		buildData(node, 0, 0, null);
	}
	
	
	public static void buildData(BinaryTree<String> node, int start, int i, BinaryTree<String> parent)
	{
		while (i < data.length() - 1 && data.charAt(i) != '(' && data.charAt(i) != ',' && data.charAt(i) != ')')
		{
			i++;
		}
		char ch = data.charAt(i);						//Format of data:	Question(Answer,Answer)  or Question(Question(Answer,Answer),Answer) 
		if (ch == '(')		//For non-leaf's
		{
			node.setValue(data.substring(start, i));
			parents.addFirst(node);
			i++;
			start = i;
			node.setLeft(new BinaryTree<String>());
			buildData(node.left(), start, i, node);
		}
		if (ch == ',')		//For all left nodes
		{
			node.setValue(data.substring(start, i));
			i++;
			start = i;
			parent.setRight(new BinaryTree<String>());
			buildData(parent.right(), start, i, parent);
		}
		if (ch == ')')      //For all right nodes
		{											
			node.setValue(data.substring(start, i));
			while(i < data.length() - 1 && (data.charAt(i) == ')' || data.charAt(i) == ','))
				i++;
			start = i;
			for (int x = 0; x < parents.size() - 1; x++)
			{
				if (parents.get(x).right() == null)
				{
					parents.get(x).setRight(new BinaryTree<String>());
					buildData(parents.get(x).right(), start, i, parents.get(x));
					break;
				}
			}
		}
	}

	
	public static String read()
	{
		String s = "";
		File file = new File("Data.txt");
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open file");
			System.exit(1);
		}
		s = input.nextLine();
		return s;
	}

	public static void rewrite(BinaryTree<String> root)
	{
		String pathname = "Data.txt";
		Writer writer = null;
		try
		{
			writer = new FileWriter(pathname, false); //2nd parameter is false so the new data replaces the old, not appends
		}
			catch (IOException ex)
		{
			System.out.println(" Cannot create/open " + pathname );
			System.exit(1);
		}
		PrintWriter output = new PrintWriter(writer);
		output.print(root + " ");
		output.close();
	}
	
}