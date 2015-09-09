package ngordnet;

import java.util.*;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Map<Integer, Synset> synsets;
    private Set<String> words;
    private Digraph wordsGraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        synsets = new HashMap<>();
        words = new HashSet<>();
        // init the synsets
        while (!synsetIn.isEmpty()) {
            String[] row = synsetIn.readLine().split(",");
            Set<String> nouns = new HashSet<>(Arrays.asList(row[1].split(" ")));
            Synset synset = new Synset(Integer.parseInt(row[0]), nouns, row[2]);
            synsets.put(synset.id, synset);
            words.addAll(nouns);
        }
        // init the graph of words.
        wordsGraph = new Digraph(synsets.size());
        while (!hyponymIn.isEmpty()) {
            String[] row = hyponymIn.readLine().split(",");
            int id = Integer.parseInt(row[0]);
            for (int i = 1; i < row.length; ++i) {
                wordsGraph.addEdge(id, Integer.parseInt(row[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return words.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return words;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> ret = new HashSet<>();
        Set<Integer> synsetId = new HashSet<>();
        ret.add(word);
        for (Integer id : synsets.keySet()) {
            Set<String> synonymSet = synsets.get(id).synonym;
            if (synonymSet.contains(word)) {
                ret.addAll(synonymSet);
                synsetId.add(id);
            }
        }
        Set<Integer> hyponymId = GraphHelper.descendants(wordsGraph, synsetId);
        for (Integer id : hyponymId) {
            Synset cur = synsets.get(id);
            ret.addAll(cur.synonym);
        }
        return ret;
    }

    private class Synset {
        public int id;
        public Set<String> synonym;
        public String definition;

        public Synset() {}

        public Synset(int id, Set<String> synonym, String definition) {
            this.id = id;
            this.synonym = synonym;
            this.definition = definition;
        }
    }
}

