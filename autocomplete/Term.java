import java.util.*;

public class Term implements Comparable<Term> {

    private final String query;
    private final long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null) {
            throw new IllegalArgumentException("Query cannot be empty!");
        } else if (weight < 0) {
            throw new IllegalArgumentException("Weight must be positive!");
        }
        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term a, Term b){
            if (a.weight < b.weight) return +1;
            else if (a.weight > b.weight) return -1;
            else return 0;
        }
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        return new PrefixOrder(r);
    }

    private static class PrefixOrder implements Comparator<Term> {
        private final int r;

        public PrefixOrder(int r) {
            if (r < 0) {
                throw new IllegalArgumentException("r cannot be negative");
            }
            this.r = r;
        }

        public int compare(Term a, Term b) {
            int min = Math.min(r, b.query.length());
            return a.query.substring(0, min).compareTo(b.query.substring(0, min));
        }
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        if (this.query.length() < that.query.length()) return -1;
        else if (this.query.length() > that.query.length()) return +1;
        else {
            for (int i = 0; i < this.query.length(); i++){
                if (this.query.charAt(i) < that.query.charAt(i)) return -1;
                else if (this.query.charAt(i) > that.query.charAt(i)) return +1;
            }
            return 0;
        }
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return this.weight + "  " + this.query;
    }

    // unit testing
    public static void main(String[] args) {
       Comparator<Term> rwo = new ReverseWeightOrder();
       Comparator<Term> po = new PrefixOrder(1);

        Term a = new Term("cat", 2);
        Term b = new Term("dog", 4);
        Term c = new Term("dogcatcher", 5);
        Term d = new Term("cat", 2);

        System.out.println("Prefix order: "); // r = 1
        System.out.println(po.compare(a, b)); // return -1
        System.out.println(po.compare(c, d)); // return 1
        System.out.println(po.compare(c, b)); // return 0


        System.out.println("Reverse weight order: ");
        System.out.println(rwo.compare(a, b)); // return 1
        System.out.println(rwo.compare(c, b)); // return -1
        System.out.println(rwo.compare(a, d)); // return 0


        System.out.println("Natural order: ");
        System.out.println(a.compareTo(b)); // return -1
        System.out.println(b.compareTo(c)); // return -1
        System.out.println(b.compareTo(a)); // return 1
        System.out.println(a.compareTo(d)); // return 0



    }

}
