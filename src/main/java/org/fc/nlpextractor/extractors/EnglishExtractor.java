package org.fc.nlpextractor.extractors;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.util.Arrays;
import java.util.List;

/**
 * User: Fred Cons
 * Date: 08/04/15
 * Time: 15:54
 */
public class EnglishExtractor extends BaseExtractor {

  private static final String NER_MODEL = "models/ner/english.all.3class.distsim.crf.ser.gz";
  private static final String POS_MODEL = "models/pos/english-left3words-distsim.tagger";
  private static final List<String> POS_WHITELIST = Arrays.asList("NNP", "NNPS");

  public EnglishExtractor() {
    try {
      this.tagger = new MaxentTagger(POS_MODEL);
      this.nerClassifier = CRFClassifier.getClassifier(NER_MODEL);
      this.posWhitelistedTypes = POS_WHITELIST;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
