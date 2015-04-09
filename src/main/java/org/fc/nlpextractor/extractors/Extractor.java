package org.fc.nlpextractor.extractors;

/**
 * User: Fred Cons
 * Date: 08/04/15
 * Time: 15:56
 */
public interface Extractor {

  String extractEntities(String filePath);
  String tagText(String filePath);

}
