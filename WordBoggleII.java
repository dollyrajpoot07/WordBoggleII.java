// Given a dictionary, a method to do a lookup in the dictionary and a M x N board where 
// every cell has one character. Find all possible words that can be formed by a sequence 
// of adjacent characters. Note that we can move to any of 8 adjacent characters, but a 
// word should not have multiple instances of the same cell.
// Input: dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO"};
//        boggle[][]   = {{'G', 'I', 'Z'},
//                        {'U', 'E', 'K'},
//                        {'Q', 'S', 'E'}};

// Output: Following words of the dictionary are present
//          GEEKS
//          QUIZ


public class WordBoggleII {

	static final int SIZE = 26;

	static final int M = 3;
	static final int N = 3;
	static class TrieNode {
		TrieNode[] Child = new TrieNode[SIZE];
		boolean leaf;
		public TrieNode() {
			leaf = false;
			for (int i = 0; i < SIZE; i++)
				Child[i] = null;
		}
	}
	static void insert(TrieNode root, String Key) {
		int n = Key.length();
		TrieNode pChild = root;

		for (int i = 0; i < n; i++) {
			int index = Key.charAt(i) - 'A';

			if (pChild.Child[index] == null)
				pChild.Child[index] = new TrieNode();

			pChild = pChild.Child[index];
		}
		pChild.leaf = true;
	}
	static boolean isSafe(int i, int j, boolean visited[][]) {
		return (i >= 0 && i < M && j >= 0
				&& j < N && !visited[i][j]);
	}

	static void searchWord(TrieNode root, char boggle[][], int i, int j, boolean visited[][], String str) {
		if (root.leaf == true)
			System.out.println(str);
		if (isSafe(i, j, visited)) {
			visited[i][j] = true;
			for (int K = 0; K < SIZE; K++) {
				if (root.Child[K] != null) {
					char ch = (char)(K + 'A');
					if (isSafe(i + 1, j + 1, visited)
						&& boggle[i + 1][j + 1] == ch)
						searchWord(root.Child[K], boggle,
								i + 1, j + 1,
								visited, str + ch);
					if (isSafe(i, j + 1, visited)
						&& boggle[i][j + 1] == ch)
						searchWord(root.Child[K], boggle,
								i, j + 1,
								visited, str + ch);
					if (isSafe(i - 1, j + 1, visited)
						&& boggle[i - 1][j + 1] == ch)
						searchWord(root.Child[K], boggle,
								i - 1, j + 1,
								visited, str + ch);
					if (isSafe(i + 1, j, visited)
						&& boggle[i + 1][j] == ch)
						searchWord(root.Child[K], boggle,
								i + 1, j,
								visited, str + ch);
					if (isSafe(i + 1, j - 1, visited)
						&& boggle[i + 1][j - 1] == ch)
						searchWord(root.Child[K], boggle,
								i + 1, j - 1,
								visited, str + ch);
					if (isSafe(i, j - 1, visited)
						&& boggle[i][j - 1] == ch)
						searchWord(root.Child[K], boggle,
								i, j - 1,
								visited, str + ch);
					if (isSafe(i - 1, j - 1, visited)
						&& boggle[i - 1][j - 1] == ch)
						searchWord(root.Child[K], boggle,
								i - 1, j - 1,
								visited, str + ch);
					if (isSafe(i - 1, j, visited)
						&& boggle[i - 1][j] == ch)
						searchWord(root.Child[K], boggle,
								i - 1, j,
								visited, str + ch);
				}
			}
			visited[i][j] = false;
		}
	}

	static void findWords(char boggle[][], TrieNode root) {
		boolean[][] visited = new boolean[M][N];
		TrieNode pChild = root;

		String str = "";
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (pChild.Child[(boggle[i][j]) - 'A'] != null) {
					str = str + boggle[i][j];
					searchWord(pChild.Child[(boggle[i][j]) - 'A'],
							boggle, i, j, visited, str);
					str = "";
				}
			}
		}
	}
	public static void main(String args[]) {
		String dictionary[] = { "GEEKS", "FOR", "QUIZ", "GEE" };
		TrieNode root = new TrieNode();
		int n = dictionary.length;
		for (int i = 0; i < n; i++)
			insert(root, dictionary[i]);

		char boggle[][] = { { 'G', 'I', 'Z' },
							{ 'U', 'E', 'K' },
							{ 'Q', 'S', 'E' } };

		findWords(boggle, root);
	}
}