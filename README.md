# nlpextractor

A facade to Stanford NLP POS and NER tagging
Can be used with the following languages : en, fr (POS only), de, es
Language detection is performed by Tika, if desired language is not provided
NER will tag all detected names in the text : names types vary with the language, and can be person, location, organization
POS will tag all detected proper nouns in the text  (without giving their type) : the nomenclature vary with the target language

Usage : 

```
# get and build code
git clone https://github.com/fredcons/nlpextractor
cd nlpextractor
mvn clean install

# profit
sh ./target/appassembler/bin/Extractor --file /path/to/target/file --analysis (POS|NER) [ --language xx ] 
# dumps the annotated text in the console

```

A few notes : 
- We use stanford-nlp 3.4.1 and not the latest 3.5.1 because the Spanish POS model seems to be broken for the last version
- We embed in the source various specific stanford-nlp models we're interested in, because the global models jar (edu.stanford.nlp:stanford-corenlp:3.4.1:models) weights 300Mo
- stanford-nlp does not provide a French NER model. TODO: use opennlp and the models provided here : http://www.nuxeo.com/blog/mining-wikipedia-with-hadoop-and-pig-for-natural-language-processing/ (research only license) 
