import java.util.*; 

import java.util.Scanner;
public class BinaryTreeMagicParents {

	static class TreeNode{
		int val;
		TreeNode left = null, right = null;
		TreeNode(int val){
			this.val = val;
		}
	}
	static int sumParents = 0;
	static void buildTree(TreeNode root, int val, String s)
	{
		int n = s.length();
		TreeNode node = root;
		for(int i=0; i<n-1; i++)
		{
			if(s.charAt(i) == 'L')
				node = node.left;
			else node = node.right;
		}
		TreeNode child = new TreeNode(val);
		if(s.charAt(n-1) == 'L')
			node.left = child;
		else node.right = child;
	}
	
	static int checkMagicParent(TreeNode node)
	{
		if(node == null)
			return 0;
		int lsum = checkMagicParent(node.left);
		int rsum = checkMagicParent(node.right);
		if(lsum == 0)
			return node.val + rsum;
		if(rsum == 0)
			return node.val + lsum;
		if((lsum%2 == 0 && rsum%2 != 0) || (lsum%2 != 0 && rsum%2 == 0))
			sumParents += node.val;
		return node.val + lsum + rsum;
	}
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		TreeNode root = new TreeNode(x);
		String[] s = new String[n-1];
	    int[] val =new int[n-1];
		for(int i=0; i<n-1; i++)
		{
			
			s[i] =sc.next();
			val[i] =sc.nextInt();
			buildTree(root, y, s[0]);
		}
		checkMagicParent(root);
		System.out.print(sumParents);
	}
	}