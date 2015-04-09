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
public class SpanishExtractor extends BaseExtractor {

  private static final String NER_MODEL = "models/ner/spanish.ancora.distsim.s512.crf.ser.gz";
  private static final String POS_MODEL = "models/pos/spanish-distsim.tagger";
  private static final List<String> POS_WHITELIST = Arrays.asList("np00000");

  public SpanishExtractor() {
    try {
      this.nerClassifier = CRFClassifier.getClassifier(NER_MODEL);
      this.tagger = new MaxentTagger(POS_MODEL);
      this.posWhitelistedTypes = POS_WHITELIST;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
