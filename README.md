# clj-corenlp-client

Unofficial Stanford CoreNLP client written in Clojure

## Usage

````Clojure
(require '[clj-corenlp-client.core :as corenlp])

(corenlp/tag-pos "I saw her.")

;; Result: {:sentences [{:index 0, :parse SENTENCE_SKIPPED_OR_UNPARSABLE, :tokens [{:index 1, :word I, :originalText I, :characterOffsetBegin 0, :characterOffsetEnd 1, :pos PRP, :before , :after  } {:index 2, :word saw, :originalText saw, :characterOffsetBegin 2, :characterOffsetEnd 5, :pos VBD, :before  , :after  } {:index 3, :word her, :originalText her, :characterOffsetBegin 6, :characterOffsetEnd 9, :pos PRP, :before  , :after } {:index 4, :word ., :originalText ., :characterOffsetBegin 9, :characterOffsetEnd 10, :pos ., :before , :after }]}]}

(corenlp/tokenize "I saw her.")

(corenlp/dep-parse "I saw her.")
````