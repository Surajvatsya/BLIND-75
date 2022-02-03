https://leetcode.com/problems/word-search-ii/
// Algo 1 : Trie + Backtracking : O(r*c*4*3^(l-1)) , O(n)
class Solution {
    private char[][] board;
    private String[] words;
    private ArrayList<String> result;
    private int[][] DIRECTIONS = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
    
    class TrieNode {
        private HashMap<Character , TrieNode> children = new HashMap<>();
        private String word;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board; this.words = words;this.result = new ArrayList<String>();
        
        TrieNode root = new TrieNode();
        constructTrie(root);    // Step 1) Construct Trie : Prefix Tree

        int r = board.length , c = board[0].length;
        for(int i = 0 ; i < r ; i++) {  // Step 2). Backtracking starting for each cell in the board
            for(int j = 0 ; j < c ; j++) {
                char c1 = board[i][j];
                if(root.children.containsKey(c1)) {
                    backtrack(i , j , root.children.get(c1));
                }
            }
        }
        return result;
    }
    
    private void constructTrie(TrieNode root) {
        for(String word : words) {
            TrieNode node = root;
            for(int i = 0 ; i < word.length() ; i++) {
                char c = word.charAt(i);
                node.children.putIfAbsent(c , new TrieNode());
                node = node.children.get(c);
            }
            node.word = word;
        }
    }

    private void backtrack(int row , int col , TrieNode node) {
        if(node.word != null) {
            result.add(node.word);
            // return;// Mistake so that foo and foooo both can be added. if returned only foo will be added.
            node.word = null;   // node.word = null to avoid duplicates in result list.    
        }
        char letter = board[row][col];  // store letter
        board[row][col] = '#';          // Mark visited

        for(int i = 0 ; i < 4 ; i++) {  // visit neighbours of cell and see if their value is children of current node.
            int newRow = row + DIRECTIONS[i][0];
            int newCol = col + DIRECTIONS[i][1];
            if(isValid(newRow , newCol)) {
                char c = board[newRow][newCol];
                if(node.children.containsKey(c)) {
                    backtrack(newRow , newCol , node.children.get(c));
                }
            }
        }

        board[row][col] = letter; // restore original letter instead of #
    }

    private boolean isValid(int i , int j) {
        return i >= 0 && j >= 0 && i < board.length && j < board[0].length;
    }
}