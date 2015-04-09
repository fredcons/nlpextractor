package org.fc.nlpextractor.extractors;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * User: Fred Cons
 * Date: 08/04/15
 * Time: 15:54
 */
public class FrenchExtractor extends BaseExtractor {

  private static final String POS_MODEL = "models/pos/french.tagger";
  //private static final String NER_MODEL = "models/ner/english.all.3class.distsim.crf.ser.gz";
  private static final List<String> POS_WHITELIST = Arrays.asList("NPP");

  private NameFinderME personFinder;
  private NameFinderME locationFinder;
  private NameFinderME organizationFinder;
  private SentenceDetectorME sentenceDetector;
  private Tokenizer tokenizer;


  public FrenchExtractor() {
    try {
      this.tagger = new MaxentTagger(POS_MODEL);
      this.personFinder = initFinder("/models/ner/fr-ner-person.bin");
      this.locationFinder = initFinder("/models/ner/fr-ner-location.bin");
      this.organizationFinder = initFinder("/models/ner/fr-ner-organization.bin");
      this.tokenizer = initTokenizer("/models/ner/fr-token.bin");
      this.sentenceDetector = initSentenceDetector("/models/ner/fr-sent.bin");
      this.posWhitelistedTypes = POS_WHITELIST;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override public String extractEntities(String filePath) {
    throw new IllegalStateException("Sorry, not implemented");
  }

  private NameFinderME initFinder(String modelFile) {
    NameFinderME finder = null;
    InputStream modelIn = null;
    try {
      modelIn = this.getClass().getResourceAsStream(modelFile);
      TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
      finder = new NameFinderME(model);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (modelIn != null) {
        try {
          modelIn.close();
        } catch (IOException e) {}
      }
    }
    return finder;
  }

  private Tokenizer initTokenizer(String modelFile) {
    Tokenizer tokenizer = null;
    InputStream modelIn = null;
    try {
      modelIn = this.getClass().getResourceAsStream(modelFile);
      TokenizerModel model = new TokenizerModel(modelIn);
      tokenizer = new TokenizerME(model);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (modelIn != null) {
        try {
          modelIn.close();
        } catch (IOException e) {}
      }
    }
    return tokenizer;
  }

  private SentenceDetectorME initSentenceDetector(String modelFile) {
    SentenceDetectorME sentenceDetector = null;
    InputStream modelIn = null;
    try {
      modelIn = this.getClass().getResourceAsStream(modelFile);
      SentenceModel model = new SentenceModel(modelIn);
      sentenceDetector = new SentenceDetectorME(model);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (modelIn != null) {
        try {
          modelIn.close();
        } catch (IOException e) {}
      }
    }
    return sentenceDetector ;
  }


}
