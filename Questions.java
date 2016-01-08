/**
*	@author Brendan Raimann
*	1/6/15
*	Version 1.1
*	A version of 20 (or more) Questions using a Binary Tree
*/

import java.io.*;
import java.util.Scanner;

public class Questions
{
	/** 
	*	For local storing of the text document 
	*/
	private static String data;
	/** 
	*	A helper for building the data from the text document
	*	Provides pointers for all of the questions 
	*/
	private static LinkedList<BinaryTree<String>> parents;
	
	
	/**
	*	Main method that runs the game
	*/
	public static void main(String[] args)
	{
		BinaryTree<String> root = new BinaryTree<String>();
		parents = new LinkedList<BinaryTree<String>>();
		
		data = read();
		buildData(root);

		BinaryTree<String> curr = root;	
		Scanner keyboard = new Scanner(System.in);
		System.out.println("\n\nWelcome to 20 (Or More) Questions!\n");
		while (curr.isLeaf() != true)
		{
			System.out.println("\n" + curr.value);
			String answer = keyboard.nextLine();
			if (answer.indexOf("n") >= 0 || answer.equalsIgnoreCase("no"))
				curr = curr.right();
			else
				curr = curr.left();
		}
		System.out.println("\nYou were thinking of:  " + curr + "\n\nWas I correct?");
		String correct = keyboard.nextLine();
		if (correct.indexOf("n") >= 0 || correct.equalsIgnoreCase("no"))
		{
			System.out.println("\nWhat were you thinking of?");
			String answer = keyboard.nextLine();
			while (answer.indexOf("(") >= 0 || answer.indexOf(",") >= 0 || answer.indexOf(")") >= 0)
			{
				System.out.println("\nThat input has illegal characters. Try again without using the following:  '('    ','    ')'    ");
				System.out.println("What were you thinking of?");
				answer = keyboard.nextLine();
			}
			System.out.println("\nGive me a question that would help me figure that out (Answering YES should get me to the answer).");
			String question = keyboard.nextLine();
			while (question.indexOf("(") >= 0 || question.indexOf(",") >= 0 || question.indexOf(")") >= 0)
			{
				System.out.println("\nThat input has illegal characters. Try again without using the following:  '('    ','    ')'    ");
				System.out.println("Give me a question that would help me figure that out (Answering YES should get me to the answer).");
				question = keyboard.nextLine();
			}
			System.out.println("\nThanks!\n\n");
			
			if (question.indexOf("?") < 0)
				question = question + "?";
			
			String temp = curr.value();
			curr.setValue(question);
			curr.setLeft(new BinaryTree<String>(answer));
			curr.setRight(new BinaryTree<String>(temp));
			
			rewrite(root);
		}
		else
			System.out.println("\nThat's what I thought.");
	}
	
	/**
	*	Used to call private method buildData() using only one parameter
	*	Builds the local binary tree with the stored data on a text file
	*	@param node The root BinaryTree for the data in the text document to be translated to
	*/
	public static void buildData(BinaryTree<String> node)
	{
		buildData(node, 0, 0, null);
	}
	
	/**
	*	Recursively translates data from text file into a binary tree
	*	@param node The BinaryTree node that is currently being edited
	*	@param start The beginning of each substring to be copied into the BinaryTrees
	*	@param i The current index of the String and the end of each substring to be copied into the Binary Trees
	*	@param parent The parent of the current node. This is necessary to go from a left node to a right node
	*/
	private static void buildData(BinaryTree<String> node, int start, int i, BinaryTree<String> parent)
	{
		while (i < data.length() - 1 && data.charAt(i) != '(' && data.charAt(i) != ',' && data.charAt(i) != ')')
		{
			i++;
		}
		char ch = data.charAt(i);
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

	/**
	*	Reads the text document and returns the text as a String
	*	@return Returns the text from the text file as a String
	*/
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
	
	/**
	*	Overwrites the text document with the BinaryTree's toString() method
	*	@param root The BinaryTree to be copied
	*/
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