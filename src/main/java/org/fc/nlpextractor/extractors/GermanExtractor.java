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
public class GermanExtractor extends BaseExtractor {

  private static final String NER_MODEL = "models/ner/dewac_175m_600.crf.ser.gz";
  private static final String POS_MODEL = "models/pos/german-dewac.tagger";
  private static final List<String> POS_WHITELIST = Arrays.asList("NE");

  public GermanExtractor() {
    try {
      this.tagger = new MaxentTagger(POS_MODEL);
      this.nerClassifier = CRFClassifier.getClassifier(NER_MODEL);
      this.posWhitelistedTypes = POS_WHITELIST;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
