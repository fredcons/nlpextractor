package org.fc.nlpextractor.extractors;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Fred Cons
 * Date: 08/04/15
 * Time: 17:00
 */
public class BaseExtractor implements Extractor {

  protected AbstractSequenceClassifier<CoreLabel> nerClassifier;
  protected MaxentTagger tagger;
  protected List<String> posWhitelistedTypes = new ArrayList<>();

  public String extractEntities(String filePath) {
    StringBuilder sb = new StringBuilder();
    List<List<CoreLabel>> out = nerClassifier.classifyFile(filePath);
    for (List<CoreLabel> sentence : out) {
      for (CoreLabel word : sentence) {
        sb.append(word.word());
        String annotation = word.get(CoreAnnotations.AnswerAnnotation.class);
        if (!"O".equals(annotation)) {
          sb.append("[" + annotation + "]");
        }
        sb.append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public String tagText(String filePath) {
    StringBuilder sb = new StringBuilder();
    List<List<HasWord>> sentences = MaxentTagger.tokenizeText(read(filePath));
    for (List<HasWord> sentence : sentences) {
      List<TaggedWord> tSentence = tagger.tagSentence(sentence);
      for (TaggedWord taggedWord : tSentence) {
        sb.append(taggedWord.word());
        String tag = taggedWord.tag();
        if (posWhitelistedTypes.contains(tag)) {
          sb.append("[" + tag + "]");
        }
        sb.append(" ");
      }
      //String taggedString = Sentence.listToString(tSentence, false);
      //sb.append(taggedString);
      sb.append("\n");
    }
    return sb.toString();
  }



  protected Reader read(String filePath) {
    Reader reader = null;
    try {
      reader = new BufferedReader(new FileReader(filePath));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return reader;
  }

  protected String readAsString(String filePath) {
    String result = null;
    try {
      result = IOUtils.toString(new FileReader(filePath));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return result;
  }

}
