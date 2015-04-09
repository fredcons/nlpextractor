package org.fc.nlpextractor;

import org.apache.commons.io.IOUtils;
import org.apache.tika.language.LanguageIdentifier;
import org.fc.nlpextractor.extractors.EnglishExtractor;
import org.fc.nlpextractor.extractors.Extractor;
import org.fc.nlpextractor.extractors.FrenchExtractor;
import org.fc.nlpextractor.extractors.GermanExtractor;
import org.fc.nlpextractor.extractors.SpanishExtractor;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.FileReader;

public class NlpExtractor {

  @Option(name = "--file", required = true, usage = "Path of the file to analyse")
  protected String targetFilePath;

  @Option(name = "--language", required = false, usage = "Language of the file to analyse, if known")
  protected String targetLanguage;

  @Option(name = "--analysis", required = true, usage = "The desired analysis, should be NER or ")
  protected Analysis analysis;

  protected Extractor extractor;

  public static void main(String[] args) throws Exception {
    NlpExtractor extractor = new NlpExtractor();
    extractor.doMain(args);
  }

  protected void doMain(String[] args) throws Exception {
    CmdLineParser parser = new CmdLineParser(this);
    try {
      parser.parseArgument(args);
    } catch (CmdLineException e) {
      System.out.println(e.getMessage());
      parser.printUsage(System.out);
      return;
    }

    if (targetLanguage == null) {
      LanguageIdentifier identifier = new LanguageIdentifier(IOUtils.toString(new FileReader(targetFilePath)));
      targetLanguage = identifier.getLanguage();
      System.out.println("Document language has been identified as " + targetLanguage);
    }

    switch (targetLanguage) {
      case "en" :
        extractor = new EnglishExtractor();
        break;
      case "fr" :
        extractor = new FrenchExtractor();
        break;
      case "de" :
        extractor = new GermanExtractor();
        break;
      case "es" :
        extractor = new SpanishExtractor();
        break;
      default:
        System.out.println("Sorry dude, no extractor for language " + targetLanguage);
        System.exit(1);
    }

    switch (analysis) {
      case NER :
        System.out.println(extractor.extractEntities(targetFilePath));
        break;
      case POS :
        System.out.println(extractor.tagText(targetFilePath));
        break;
      default:
        System.out.println("What");
        break;
    }

  }

}
